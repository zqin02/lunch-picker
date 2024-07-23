import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserModalService {
 
  private openModalFn!: () => Promise<any>;
  result$ = new Subject<string>();
  constructor() { } 
  registerOpenModalFunction(fn: () => Promise<any>):void {
    this.openModalFn = fn
  }

  triggerOpenModal():Promise<any>|null {
    if (this.openModalFn) {
      return this.openModalFn()
    } 
    return null
  } 

}
