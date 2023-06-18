import { HttpClient, HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, tap } from 'rxjs';
import { Card } from '../class/card';
import { Subscription } from '../class/subscription';

@Injectable({
  providedIn: 'root'
})
export class CardService {

  private url: string

  constructor(private http: HttpClient) {
    this.url = 'http://localhost:8080/';
  }

  public getAllCards(): Observable<Card[]>{
    var reqHeader = new HttpHeaders({ 
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + sessionStorage.getItem("token")
   });
   return this.http.get<Card[]>(this.url + "getCards",{ headers: reqHeader } )

  }

  public getCardFromClient(id:number): Observable<Card> {
    var reqHeader = new HttpHeaders({ 
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + sessionStorage.getItem("token")
   });
    return this.http.get<Card>(this.url + "getCard/" + id,{ headers: reqHeader });
  }

    public insertCard(card:Card){
      var reqHeader = new HttpHeaders({ 
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + sessionStorage.getItem("token")
    });
    return this.http.post<HttpErrorResponse>(this.url + "insertCard", card,{ headers: reqHeader})
    }

    public deleteCard(id:number){
      var reqHeader = new HttpHeaders({ 
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + sessionStorage.getItem("token")
     });
     return this.http.delete<boolean>(this.url + "deleteCard" + "/" + id,{ headers: reqHeader } )

    }

}
