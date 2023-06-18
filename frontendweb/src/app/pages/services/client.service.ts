import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../class/user';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private url: string;

  constructor(private http: HttpClient) {
    this.url = 'http://localhost:8080/';
  }

  public getAllUsers(): Observable<User[]> {
    const reqHeader = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: 'Bearer ' + sessionStorage.getItem('token')
    });

    return this.http.get<User[]>(this.url + 'user/getAllUsers', {
      headers: reqHeader
    });
  }

  public getUser(username: string): Observable<User> {
    const reqHeader = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: 'Bearer ' + sessionStorage.getItem('token')
    });

    return this.http.get<User>(
      this.url + 'user/getSingleUser' + '/' + username,
      { headers: reqHeader }
    );
  }

  public deleteUser(id: number): Observable<boolean> {
    const reqHeader = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: 'Bearer ' + sessionStorage.getItem('token')
    });

    return this.http.delete<boolean>(this.url + 'user/deleteUser' + '/' + id, {
      headers: reqHeader
    });
  }

  public getUsersCount(): Observable<number> {
    const reqHeader = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: 'Bearer ' + sessionStorage.getItem('token')
    });

    return this.http.get<number>(this.url + 'user/getUsersCount', {
      headers: reqHeader
    });
  }


  public getClientsWithoutCard(): Observable<User[]> {
    var reqHeader = new HttpHeaders({ 
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + sessionStorage.getItem("token")
   });
    return this.http.get<User[]>(this.url + "user/getAllUsersWithoutCard",{ headers: reqHeader });
  }

}
