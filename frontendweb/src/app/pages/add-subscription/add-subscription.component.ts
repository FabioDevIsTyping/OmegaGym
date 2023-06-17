import { Component, OnInit } from '@angular/core';
import { SubscriptionService } from '../services/subscription.service';
import { HttpErrorResponse } from '@angular/common/http';

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
  showErrorNotification: boolean = false;

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
        // Execute other actions or reset the form fields
      },
      (error) => {
        if (error instanceof HttpErrorResponse && error.status === 401) {
          // Show the notification for 401 error
          this.showErrorNotification = true;

          // Hide the error notification after 1 second
          setTimeout(() => {
            this.showErrorNotification = false;
          }, 1000);
        }
      }
    );
  }
}
