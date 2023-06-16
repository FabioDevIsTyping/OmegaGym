import { Component, OnInit } from '@angular/core';
import { ClientService } from '../services/client.service';
import { User } from '../class/user';

@Component({
  selector: 'app-manage-client',
  templateUrl: './manage-client.component.html',
  styleUrls: ['./manage-client.component.css']
})
export class ManageClientComponent implements OnInit {
  isAdmin: boolean = false;
  clientList: User[] = [];
  isDeleted: boolean = false;
  filteredUserList: User[] = [];
  searchText: string = '';
  currentSortColumn: string = '';
  sortDirection: string = '';

  constructor(private clientService: ClientService) { }

  ngOnInit() {
    if (sessionStorage.getItem("role") === 'ADMIN') {
      this.isAdmin = true;
      this.clientService.getAllUsers().subscribe((result: User[]) => {
        this.clientList = result;
        this.filteredUserList = result;
        console.log(this.clientList);
      });
    }
  }

  deleteUser(id: number) {
    console.log(id);
    this.clientService.deleteUser(id).subscribe(result => {
      this.isDeleted = result;
      console.log(this.isDeleted);
      this.clientService.getAllUsers().subscribe((result: User[]) => {
        this.clientList = result;
        this.filteredUserList = result;
        console.log(this.clientList);
      });
    });
  }

  applyFilter() {
    if (this.searchText.trim() === '') {
      this.filteredUserList = this.clientList;
    } else {
      this.filteredUserList = this.clientList.filter(user => {
        if (user.username && user.username.toLowerCase().startsWith(this.searchText.trim().toLowerCase())) {
          return true;
        }
        return false;
      });
    }
  }

  confirmDeleteUser(user: User) {
    const confirmationMessage = `Are you sure you want to delete ${user.username}?`;
    if (confirm(confirmationMessage)) {
      this.deleteUser(user.id);
    }
  }

  sortColumn(column: string) {
    if (column === this.currentSortColumn) {
      this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
    } else {
      this.currentSortColumn = column;
      this.sortDirection = 'asc';
    }

    this.filteredUserList.sort((a, b) => {
      const valueA = this.getPropertyValue(a, column);
      const valueB = this.getPropertyValue(b, column);

      if (valueA < valueB) {
        return this.sortDirection === 'asc' ? -1 : 1;
      } else if (valueA > valueB) {
        return this.sortDirection === 'asc' ? 1 : -1;
      } else {
        return 0;
      }
    });
  }

  getPropertyValue(obj: any, column: string): any {
    const keys = column.split('.');
    let value = obj;

    for (const key of keys) {
      if (value.hasOwnProperty(key)) {
        value = value[key];
      } else {
        value = undefined;
        break;
      }
    }

    return value;
  }
}
