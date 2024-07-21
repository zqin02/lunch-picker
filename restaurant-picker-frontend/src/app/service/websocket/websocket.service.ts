import { Injectable } from '@angular/core';
import { Client } from '@stomp/stompjs';
import { Observable, Subject } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class WebsocketService {
  private stompClient: any 
  private messageSubject: Subject<any> = new Subject<any>() 
  private errorSubject: Subject<any> = new Subject<any>() 

  constructor() {
  }

  public connect(uuid: string, user:string) {
    const serverUrl = 'http://localhost:8081/ws';
    this.stompClient = new Client({
      brokerURL: serverUrl,
    });

    this.stompClient.onConnect = (frame: any) => { 
      console.log('Connected for : ' + uuid);
      this.stompClient.subscribe(`/topic/restaurant.${uuid}`, (message: any) => { 
        this.messageSubject.next(JSON.parse(message.body))
      }) 
      this.stompClient.subscribe(`/queue/error-${user}`, (message: any) => { 
        this.errorSubject.next(JSON.parse(message.body))
      }) 
    }
    
    this.stompClient.activate();

  }
  public disconnect() {
    this.stompClient.deactivate(); 
  }
  public isConnected(): boolean {
    return this.stompClient && this.stompClient.connected;
  }
  public getMessages(): Observable<string> {
    return this.messageSubject.asObservable();
  }
  public getErrorMessages(): Observable<string> {
    return this.errorSubject.asObservable();
  }
  //submit restaurant
  public sendMessage(body: any): void {
    this.stompClient.publish({
      destination: "/app/submit",
      body: body
    });
  }
}
