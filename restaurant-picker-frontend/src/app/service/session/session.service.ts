import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  private apiUrl = 'http://localhost:8081'
  alias$ = new BehaviorSubject<string>('');
  constructor(private http: HttpClient) { }

  public updateAlias(alias: string):void { 
    this.alias$.next(alias)
  }

  public createSession(): any {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }) 
    return this.http.post<any>(`${this.apiUrl}/session/create`,{userAlias:this.alias$.getValue()}, { headers: headers })


  }

  public connectSession(uuid:string): any {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }) 
    return this.http.post<any>(`${this.apiUrl}/session/connect/${uuid}`,{userAlias:this.alias$.getValue()})


  }
  public end(body:any): any {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }) 
    return this.http.post<any>(`${this.apiUrl}/session/end`,body, { headers: headers })
     

  }
}
