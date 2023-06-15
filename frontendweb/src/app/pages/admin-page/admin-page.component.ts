import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit {

  constructor() { }

  isAdmin:boolean=false

  ngOnInit()  {
    if(sessionStorage.getItem("role")=='ADMIN'){
      this.isAdmin=true
      

    }
  }

}
