import { AfterViewInit, Component, OnDestroy, OnInit, TemplateRef } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { WebsocketService } from '../service/websocket/websocket.service';
import { Subscription } from 'rxjs';
import { SessionService } from '../service/session/session.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastService } from '../service/toast/toast.service';
import { ErrorCode } from '../../utils/errorCode';
import { UserModalComponent } from '../user-modal/user-modal.component';
import { UserModalService } from '../service/modal/user-modal.service';

@Component({
  selector: 'app-session',
  standalone: true,
  imports: [FormsModule, UserModalComponent],
  templateUrl: './session.component.html',
  styleUrl: './session.component.css',
  providers: [WebsocketService]
})
export class SessionComponent implements OnInit, OnDestroy , AfterViewInit{
  origin = `${window.location.origin}?code=`;
  sessionName: string = ''
  restaurant: string = ''
  user: string = ''
  isHost: boolean = false
  isConnected: boolean = false
  restaurantList: string[] = []
  private messageSubscription: Subscription
  private errorSubscription: Subscription
  private toastSubscription: Subscription

  constructor(
    private webSocketService: WebsocketService,
    private sessionService: SessionService,
    private toastService: ToastService,
    private userModalService: UserModalService,
    private router: Router,
    private route: ActivatedRoute) { }
  ngOnInit(): void {
   
    this.messageSubscription = this.webSocketService.getMessages()
      .subscribe({
        next: this.handleUpdateResponse.bind(this),
        error: this.handleError.bind(this)
      })
    this.errorSubscription = this.webSocketService.getErrorMessages()
      .subscribe((msg: any) => {
        if (msg.action === 'ERROR') {
          this.toastService.show(msg.message)
        }
      })


  }
  ngAfterViewInit(): void {
    this.route.queryParams.subscribe(params => {
      const code = params['code'];
      if (code) {
        this.sessionName = code;
        this.connectSession();
      }
    })
  }
  ngOnDestroy(): void {
    this.messageSubscription?.unsubscribe()
    this.toastSubscription?.unsubscribe()
    this.errorSubscription?.unsubscribe()
    this.webSocketService.disconnect()
  }
  resetValue(): void {
    this.sessionName = ''
    this.restaurant = ''
    this.user = ''
    this.isHost = false
    this.isConnected = false
    this.restaurantList = []
  }
  createNewSession(): void {
    this.userModalService.triggerOpenModal()?.then(
      (name) => {
        this.sessionService.createSession()
          .subscribe(
            (data: any) => {
              this.isHost = true;
              this.sessionName = data?.uuid;
              this.user = data?.user;
              this.webSocketService.connect(this.sessionName, this.user);
              this.checkConnected();
            },
            (error: any) => {
              console.error(error);
            }
          );
      },
      (reason) => {
        console.log('Modal dismissed:', reason);
      }
    ).catch((error) => {
      console.error('Error opening modal:', error);
    });
  }
  connectSession(): void {
    console.log('start connect session')
    this.userModalService.triggerOpenModal()?.then(
      (name) => {
        this.sessionService.connectSession(this.sessionName)
          .subscribe(
            (data: any) => {
              if (data.errorCode !== ErrorCode.invalidSession) {
                this.restaurantList = data?.restaurantInfos
                this.user = data?.user
                this.webSocketService.connect(this.sessionName, this.user)
                this.checkConnected()
                return
              }
              this.toastService.show(data.message)
              this.router.navigate([], {
                queryParams: {},
                replaceUrl: true
              })
            },

            (error: any) => { console.error(error) }
          )
      },
      (reason) => {
        console.log('Modal dismissed:', reason);
      }
    ).catch((error) => {
      console.error('Error opening modal:', error);
    });

  }
  endSession(): void {
    this.sessionService.end({
      user: this.user,
      uuid: this.sessionName
    })
      .subscribe(() => {
        this.webSocketService.disconnect()
        this.resetValue()
      })
  }
  checkConnected(): boolean {
    this.isConnected = this.webSocketService.isConnected()
    return this.isConnected
  }
  handleUpdateResponse(message: any): void {
    if (message.action === 'PUBLISH') {
      this.handleEndResult(message.resultSelection)
    }
    else if (message.action === 'UPDATE') {
      this.restaurantList.push(message.restaurantName)
    }
    else if(message.userAlias)
    {
      this.toastService.show(`${message.userAlias} entered session`, 'Info')
    }
  }
  handleEndResult(result?: string): void {
    this.router.navigate(['/result'], { state: { data: result } })
  }
  handleError(error: any) {
    this.toastService.show(error)
  }
  sendRestaurant(): void {
    let body = JSON.stringify({
      restaurantName: this.restaurant,
      user: this.user,
      uuid: this.sessionName,
    })
    this.webSocketService.sendMessage(body)
  }

  copyToClipboard(): void {
    const url = `${this.origin}${this.sessionName}`
    navigator.clipboard.writeText(url).then(() => {
      this.toastService.show(`${url}\nSession URL copied to clipboard!`, 'Info')
    }).catch(err => {
      console.error('Could not copy text: ', err);
    });
  }
  private handleOpenModal(fn: () => {}) {
    this.userModalService.triggerOpenModal()?.then(
      (name) => {
        if (fn)
          fn()
      },
      (reason) => {
        console.log('Modal dismissed:', reason);
      }
    ).catch((error) => {
      console.error('Error opening modal:', error);
    });
  }
}