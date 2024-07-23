import { AfterViewInit, Component, EventEmitter, Output, TemplateRef, ViewChild, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbDatepickerModule, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { SessionService } from '../service/session/session.service';
import { UserModalService } from '../service/modal/user-modal.service';

@Component({
  selector: 'app-user-modal',
  standalone: true,
  imports: [NgbDatepickerModule,FormsModule],
  templateUrl: './user-modal.component.html',
  styleUrl: './user-modal.component.css'
})
export class UserModalComponent implements AfterViewInit{
  private modalService = inject(NgbModal)
  name = '' 
  @ViewChild('content', { static: true }) content!: TemplateRef<any> 

  constructor(private userModalService:UserModalService,
    private sessionService:SessionService
  ){
  
  }
  ngAfterViewInit(): void {
    this.userModalService.registerOpenModalFunction(this.open.bind(this));
  }
  open(): Promise<string> {
    return new Promise((resolve, reject) => {
      this.modalService.open(this.content, {
        ariaLabelledBy: 'modal-basic-title',
        backdrop: 'static'
      }).result.then(
        (result) => {
          this.updateAlias()
          resolve(this.name)
        },
        (reason) => {
          this.updateAlias()
          resolve(this.name)
        }
      ).catch((error) => { 
        reject(error);
      })
    })
  }

  private updateAlias(): void {
    this.sessionService.updateAlias(this.name)
  }
   
  
}
