import { Component, OnInit } from '@angular/core';
import { SubscriptionService } from '../services/subscription.service';

@Component({
  selector: 'app-add-subscription',
  templateUrl: './add-subscription.component.html',
  styleUrls: ['./add-subscription.component.css']
})
export class AddSubscriptionComponent implements OnInit {

  constructor(private subscriptionService: SubscriptionService) { }

  isAdmin: boolean = false;
  name: string | undefined;
  price: number = 0;
  duration: number = 1;
  description: string | undefined;

  ngOnInit(): void {
    if (sessionStorage.getItem("role") === 'ADMIN') {
      this.isAdmin = true;
    }
  }

  addSubscription(): void {
    const subscription = {
      name: this.name,
      duration: this.duration,
      price: this.price,
      descrizione: this.description
    };
    this.subscriptionService.insertSubscription(subscription).subscribe(
      (response) => {
        console.log('Abbonamento inserito con successo:', response);
        // Esegui altre azioni o resetta i campi del modulo
      },
      (error) => {
        console.error('Errore durante l\'inserimento dell\'abbonamento:', error);
        // Gestisci l'errore in modo appropriato
      }
    );
  }
}
