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
  filteredCardList: Card[] = [];
  isDeleted: boolean = false;
  searchText: string = '';

  constructor(private cardService: CardService) { }

  ngOnInit() {
    if (sessionStorage.getItem("role") === 'ADMIN') {
      this.isAdmin = true;
      this.cardService.getAllCards().subscribe((result: Card[]) => {
        this.cardList = result;
        this.filteredCardList = result;
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
          this.applyFilter();
        });
      });
    }
  }

  applyFilter() {
    if (this.searchText.trim() === '') {
      this.filteredCardList = this.cardList;
    } else {
      this.filteredCardList = this.cardList.filter(card => {
        if (card.user && card.user.username && card.user.username.toLowerCase().startsWith(this.searchText.trim().toLowerCase())) {
          return true;
        }
        return false;
      });
    }
  }

  confirmDeleteCard(card: Card) {
  const confirmationMessage = `Are you sure you want to eliminate ${card.user.username}'s card?`;
  if (confirm(confirmationMessage)) {
    this.deleteCard(card.id);
  }
}
}
