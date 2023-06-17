import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Subscription } from '../class/subscription';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SubscriptionService {

  private url: string

  constructor(private http: HttpClient) {
    this.url = 'http://localhost:8080/';
  }

  public getAllSubscriptions(): Observable<Subscription[]>{
    var reqHeader = new HttpHeaders({ 
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + sessionStorage.getItem("token")
   });
   return this.http.get<Subscription[]>(this.url + "getSubscriptions",{ headers: reqHeader } )
  }

  public getSubscription(id:number): Observable<Subscription>{
    var reqHeader = new HttpHeaders({ 
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + sessionStorage.getItem("token")
   });
   return this.http.get<Subscription>(this.url + "getSingleSubscription/" + id,{ headers: reqHeader } )
  }

  public deleteSubscription(id:number){
    var reqHeader = new HttpHeaders({ 
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + sessionStorage.getItem("token")
   });
   return this.http.delete<boolean>(this.url + "deleteSubscription" + "/" + id,{ headers: reqHeader } )

  }

  public insertSubscription(subscription:Subscription){
    var reqHeader = new HttpHeaders({ 
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + sessionStorage.getItem("token")
  });
  return this.http.post<Subscription>(this.url + "insertSubscription", subscription,{ headers: reqHeader })
  }

  
}
