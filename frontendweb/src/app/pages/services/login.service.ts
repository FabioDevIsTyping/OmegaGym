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

  showNavbar: boolean = false

  public authenticate(username:string,password:string){
    return this.http.post<any>(this.url+"user/login",{username,password}).pipe(
      map(
        result=>{
          sessionStorage.setItem('username',username)
          sessionStorage.setItem('id',result.id)
          sessionStorage.setItem('token',result.token)
          sessionStorage.setItem('role',result.role)
          return result;
        }
      ))

  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem('token');
    let username = sessionStorage.getItem('username');
    return !(user && username === null)
  }


}
