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
  username:string = ''
  password:string = ''


  
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
    this.loginService.authenticate(this.username,this.password).subscribe().add(
      ()=>{
        this.router.navigate(['/quizApp']).then(() => {
          window.location.reload();
        });

      }
    )


  }

}
