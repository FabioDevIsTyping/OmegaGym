import { Component, OnInit } from '@angular/core';
import { ClientService } from '../services/client.service';
import { User } from '../class/user';

@Component({
  selector: 'app-manage-client',
  templateUrl: './manage-client.component.html',
  styleUrls: ['./manage-client.component.css']
})
export class ManageClientComponent implements OnInit {

  constructor(private clientService: ClientService) { }

  isAdmin:boolean=false
  clientList:User[]=[]
  isDeleted:boolean=false


  ngOnInit() {
    if(sessionStorage.getItem("role")=='ADMIN'){
      this.isAdmin=true
      this.clientService.getAllUsers().subscribe(result=>{
        this.clientList=result
        console.log(this.clientList)
      })
  }}

  deleteUser(id:number){
    console.log(id)
    this.clientService.deleteUser(id).subscribe(result=>{
      this.isDeleted=result
      console.log(this.isDeleted)
      this.clientService.getAllUsers().subscribe(result=>{
        this.clientList=result
        console.log(this.clientList)
      })
  
    })

   
    
  }

}
