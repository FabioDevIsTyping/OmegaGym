import { Component, OnInit } from '@angular/core';
import { SubscriptionService } from '../services/subscription.service';
import { Subscription } from '../class/subscription';

@Component({
  selector: 'app-manage-subscriptions',
  templateUrl: './manage-subscriptions.component.html',
  styleUrls: ['./manage-subscriptions.component.css']
})
export class ManageSubscriptionsComponent implements OnInit {

  constructor(private subscriptionService: SubscriptionService) { }

  isAdmin: boolean = false;
  subscriptionList: Subscription[] = [];

  ngOnInit() {
    if (sessionStorage.getItem("role") === 'ADMIN') {
      this.isAdmin = true;
      this.subscriptionService.getAllSubscriptions().subscribe((result: Subscription[]) => {
        this.subscriptionList = result;
      });
    }
  }

}
