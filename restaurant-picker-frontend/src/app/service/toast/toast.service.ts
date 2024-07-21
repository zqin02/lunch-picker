import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ToastService {

  toasts$ = new Subject<any>();
  show(message: string,type:string='Error') { 
    this.toasts$.next({type,message})
  }
 
}
