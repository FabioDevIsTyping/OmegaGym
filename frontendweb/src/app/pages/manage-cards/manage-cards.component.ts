import { Component, OnInit } from '@angular/core';
import { CardService } from '../services/card.service';
import { Card } from '../class/card';

@Component({
  selector: 'app-manage-cards',
  templateUrl: './manage-cards.component.html',
  styleUrls: ['./manage-cards.component.css']
})
export class ManageCardsComponent implements OnInit {

  isAdmin: boolean = false;
  cardList: Card[] = [];
  isDeleted: boolean = false;

  constructor(private cardService: CardService) { }

  ngOnInit() {
    if (sessionStorage.getItem("role") === 'ADMIN') {
      this.isAdmin = true;
      this.cardService.getAllCards().subscribe((result: Card[]) => {
        this.cardList = result;
        console.log(this.cardList);
      });
    }
  }

  deleteCard(id: number | undefined) {
    if (id !== undefined) {
      console.log(id);
      this.cardService.deleteCard(id).subscribe(result => {
        this.isDeleted = result;
        console.log(this.isDeleted);
        this.cardService.getAllCards().subscribe((result: Card[]) => {
          this.cardList = result;
          console.log(this.cardList);
        });
      });
    }
  }
  
}
