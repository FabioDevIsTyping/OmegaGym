import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../pages/services/login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  
  constructor(private router: Router, private loginService: LoginService) { }
  
  showNavbar: boolean = true;

  logout(){
    sessionStorage.removeItem("token")
    sessionStorage.removeItem("role")
    sessionStorage.removeItem("username")
    sessionStorage.removeItem("id")
    this.router.navigate(['/login']).then(() => {
          window.location.reload();
        });
  }

  ngOnInit(): void {
  }

}
