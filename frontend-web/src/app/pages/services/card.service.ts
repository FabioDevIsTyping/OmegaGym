import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Card } from '../class/card';

@Injectable({
  providedIn: 'root'
})
export class CardService {

  private url: string

  constructor(private http: HttpClient) {
    this.url = 'http://localhost:8080/';
  }

  public getCardFromClient(id:number): Observable<Card> {
    var reqHeader = new HttpHeaders({ 
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + sessionStorage.getItem("token")
   });
    return this.http.get<Card>(this.url + "getCard/" + id,{ headers: reqHeader });
  }



}
