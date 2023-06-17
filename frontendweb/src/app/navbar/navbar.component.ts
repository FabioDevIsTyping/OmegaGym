import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../pages/services/login.service';
import { Subscription } from 'rxjs/internal/Subscription';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  
  constructor(private router: Router, private loginService: LoginService) { }
  
  showNavbar: boolean = true;
  observableBoolean!: boolean;
  private subscription!: Subscription;
  isAdmin: boolean = false
  isUser: boolean = false



  logout(){
    sessionStorage.removeItem("token")
    sessionStorage.removeItem("role")
    sessionStorage.removeItem("username")
    sessionStorage.removeItem("id")
    this.router.navigate(['/login']).then(() => {
          window.location.reload();
        });
  }

  confirmLogout() {
    if (confirm("Are you sure you want to log out?")) {
      this.logout();
    }
  }

  ngOnInit() {
    if(sessionStorage.getItem("role") ==='ADMIN'){
      this.isAdmin=true
      this.isUser=false
    }
    else if(sessionStorage.getItem("role")==='USER')
    {
      this.isUser=true
      this.isAdmin=false
    }
  }



}
