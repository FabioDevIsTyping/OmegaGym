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
    if (this.searchText.trim() === '') {
      this.filteredSubscriptionList = this.subscriptionList;
      console.log(this.filteredSubscriptionList);
    } else {
      this.filteredSubscriptionList = this.subscriptionList.filter((subscription: Subscription) => {
        return subscription.name?.toLowerCase().startsWith(this.searchText.trim().toLowerCase());
      });
      console.log(this.filteredSubscriptionList);
    }
  }
  
    
  

  sortColumn(column: string) {
    if (this.currentSortColumn === column) {
      this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
    } else {
      this.currentSortColumn = column;
      this.sortDirection = 'asc';
    }
  
    if (this.currentSortColumn === 'name') {
      this.filteredSubscriptionList.sort((a: Subscription, b: Subscription) => {
        const nameA = a.name?.toLowerCase();
        const nameB = b.name?.toLowerCase();
        if (nameA && nameB) {
          if (nameA < nameB) {
            return this.sortDirection === 'asc' ? -1 : 1;
          } else if (nameA > nameB) {
            return this.sortDirection === 'asc' ? 1 : -1;
          }
        }
        return 0;
      });
    } else if (this.currentSortColumn === 'id') {
      this.filteredSubscriptionList.sort((a: Subscription, b: Subscription) => {
        const idA = a.id;
        const idB = b.id;
        if (idA && idB) {
          return this.sortDirection === 'asc' ? idA - idB : idB - idA;
        }
        return 0;
      });
    }
  }
  
}
