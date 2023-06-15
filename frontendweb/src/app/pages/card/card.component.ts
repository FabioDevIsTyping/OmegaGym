import { Component, OnInit } from '@angular/core';
import { CardService } from '../services/card.service';
import { Card } from '../class/card';
import { Subscription } from '../class/subscription';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  constructor(private cardService:CardService) {
   }

   card:Card=new Card
   subscription:Subscription=new Subscription

   isUser:boolean=false

  ngOnInit() {
    if(sessionStorage.getItem("role")=='USER'){
      this.isUser=true;
    }
    console.log(sessionStorage)
    this.cardService.getCardFromClient(Number(sessionStorage.getItem("id"))).subscribe(result=>{
      console.log(result)
      this.card=result
      this.subscription.name=result.subscription?.name
    })

  }

}
