import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  private apiUrl = 'http://localhost:8081'
  constructor(private http: HttpClient) { }
  public createSession(): any {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }) 
    return this.http.post<any>(`${this.apiUrl}/session/create`,{}, { headers: headers })


  }

  public connectSession(uuid:string): any {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }) 
    return this.http.get<any>(`${this.apiUrl}/session/connect/${uuid}`)


  }
  public end(body:any): any {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }) 
    return this.http.post<any>(`${this.apiUrl}/session/end`,body, { headers: headers })
     

  }
}
