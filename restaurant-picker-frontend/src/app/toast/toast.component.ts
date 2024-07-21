import { Component, OnDestroy, OnInit } from '@angular/core';
import { ToastService } from '../service/toast/toast.service';
import { Subscription } from 'rxjs'; 
import { NgbToastModule } from '@ng-bootstrap/ng-bootstrap';
@Component({
  selector: 'app-toast',
  standalone: true,
  imports: [NgbToastModule],
  templateUrl: './toast.component.html',
  styleUrl: './toast.component.css'
})
export class ToastComponent implements OnInit, OnDestroy {
  toasts: any[] = []
  private toastSubs: Subscription
  constructor(private toastService: ToastService
  ) {}

  ngOnInit(): void { 
  
    this.toastSubs = this.toastService.toasts$.subscribe((msg) => {  
      this.toasts.push(msg)  
    })
  }
  ngOnDestroy(): void { 
    this.toastSubs.unsubscribe()
  }
  removeToast(){
    this.toasts.shift()
  }
}
