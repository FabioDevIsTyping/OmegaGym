import { Component, OnInit } from '@angular/core';
import { CardService } from '../services/card.service';
import { Subscription } from '../class/subscription';
import { SubscriptionService } from '../services/subscription.service';
import { ClientService } from '../services/client.service';
import { User } from '../class/user';
import { Card } from '../class/card';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit {

  constructor(private cardService: CardService, private subscriptionService: SubscriptionService,
    private clientService: ClientService) { }

  isAdmin:boolean=false
  subscriptionId:number=0
  startDate:Date | undefined
  endDate:Date | undefined
  userId:number=0
  subscriptionList:Subscription[]=[]
  clientList:User[]=[]
  username:string=""
  user:User = new User
  card:Card = new Card
  selectedSubscription: number = 0
  subscription:Subscription = new Subscription

  changeObject(obj:any){
    console.log(obj)
  }


  ngOnInit()  {
    if(sessionStorage.getItem("role")=='ADMIN'){
      this.isAdmin=true
      this.subscriptionService.getAllSubscriptions().subscribe(result=>{
        this.subscriptionList=result
        console.log(this.subscriptionList)
      })
      this.clientService.getAllUsers().subscribe(result=>{
        this.clientList=result
        console.log(this.clientList)
      })
    }
  }

  submitData() {
    this.clientService.getUser(this.username).subscribe(result => {
      this.user = result;
      console.log(this.user);
      this.subscriptionService.getSubscription(this.selectedSubscription).subscribe(result => {
        this.subscription = result;
        console.log(this.subscription);
        console.log(this.selectedSubscription);
        this.card.user = new User();
        this.card.user.id = this.user.id;
        this.card.subscription = this.subscription;
        console.log(this.card);
        this.cardService.insertCard(this.card).subscribe(
          response => {
            if (response instanceof HttpErrorResponse && response.status === 200){

              console.log(response)

            }

          }
        )
      });
    });
  
  }
  
  
  




    
  
  
}
