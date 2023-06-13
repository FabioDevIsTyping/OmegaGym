import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../class/user';
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private url: string

  constructor(private http: HttpClient) {
    this.url = 'http://localhost:8080/';
  }

  public register(user: User) {
    return this.http.post<User>(this.url+"user/signup", user);
  }

  public authenticate(username:string,password:string){
    return this.http.post<any>(this.url+"user/login",{username,password}).pipe(
      map(
        result=>{
          sessionStorage.setItem('username',username)
          let token = result.token;
          sessionStorage.setItem('token',token)
          sessionStorage.setItem('role',result.role)
          return result;
        }
      ))

  }
}
