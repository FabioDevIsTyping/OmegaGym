import { Component, OnInit } from '@angular/core';
import { CardService } from '../services/card.service';
import { Card } from '../class/card';
import { Subscription } from '../class/subscription';
import { ClientService } from '../services/client.service';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {
  card: Card = new Card();
  subscription: Subscription = new Subscription();
  isUser: boolean = false;
  usersCount: number = 1;

  constructor(
    private cardService: CardService,
    private clientService: ClientService
  ) {}

  ngOnInit() {
    if (sessionStorage.getItem('role') === 'USER') {
      this.isUser = true;
    }

    console.log(sessionStorage);
    
    this.cardService
      .getCardFromClient(Number(sessionStorage.getItem('id')))
      .subscribe((result) => {
        console.log(result);
        this.card = result;
        this.subscription.name = result.subscription?.name;
      });

    this.clientService.getUsersCount().subscribe((count) => {
      this.usersCount = count;
    });
  }
}
