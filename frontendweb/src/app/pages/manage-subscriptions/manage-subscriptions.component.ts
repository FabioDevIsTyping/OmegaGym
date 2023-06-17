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
  isDeleted: boolean = false;
  searchText: string = '';
  currentSortColumn: string = '';
  sortDirection: string = 'asc';
  filteredSubscriptionList: Subscription[] = [];

  ngOnInit() {
    if (sessionStorage.getItem("role") === 'ADMIN') {
      this.isAdmin = true;
      this.subscriptionService.getAllSubscriptions().subscribe((result: Subscription[]) => {
        this.subscriptionList = result;
        this.filteredSubscriptionList = result; // Initialize filtered list with all subscriptions
      });
    }
  }

  deleteSubscription(id: number | undefined) {
    if (id !== undefined) {
      console.log(id)
      this.subscriptionService.deleteSubscription(id).subscribe(result => {
        this.isDeleted = result
        console.log(this.isDeleted)
        this.subscriptionService.getAllSubscriptions().subscribe((result: Subscription[]) => {
          this.subscriptionList = result;
          this.filteredSubscriptionList = result; // Update filtered list after deletion
        })
      })
    }
  }

  confirmDeleteSubscription(subscription: Subscription) {
    const confirmationMessage = `Are you sure you want to eliminate ${subscription.name}?`;
    if (confirm(confirmationMessage)) {
      this.deleteSubscription(subscription.id);
    }
  }

  applyFilter() {
    this.filteredSubscriptionList = this.subscriptionList.filter((subscription: Subscription) => {
      return subscription.name?.toLowerCase().includes(this.searchText.toLowerCase());
    });
  }
  

  sortColumn(column: string) {
    if (this.currentSortColumn === column) {
      this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
    } else {
      this.currentSortColumn = column;
      this.sortDirection = 'asc';
    }

    // Implement sorting logic based on this.currentSortColumn and this.sortDirection
    // Update this.filteredSubscriptionList with sorted data
  }
}
