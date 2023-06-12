import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../services/login.service';
import { User } from '../class/user';
import { Role } from '../class/role';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: User = new User
  role: Role = new Role

  loginObj: any = {
    UserId: 0,
    UserName: '',
    Password: '',
    Result: false,
    Message: ''
  };
  registerObj: any = {
    UserId: 0,
    UserName: '',
    Password: ''

  };
  isRegister: boolean = false;
  constructor(private loginService: LoginService, private router: Router, private http: HttpClient) { }

  ngOnInit(): void {
  }

  onRegister() {
    this.role.role="ADMIN"
    this.user.role=this.role.role
    console.log(this.user)
    this.loginService.register(this.user).subscribe()
  }

  onLogin() {
    this.http.post("http://localhost:61334/api/Registration/Login", this.loginObj).subscribe((response: any) => {
      debugger;
      if (response.result) {
        alert(response.message)
        this.router.navigateByUrl('way2user-dashboard');
      } else {
        alert(response.message)
      }
    })

  }

}
