import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { CardComponent } from './pages/card/card.component';
import { AdminPageComponent } from './pages/admin-page/admin-page.component';
import { ManageClientComponent } from './pages/manage-client/manage-client.component';
import { ManageCardsComponent } from './pages/manage-cards/manage-cards.component';
import { ManageSubscriptionsComponent } from './pages/manage-subscriptions/manage-subscriptions.component';
import { AddSubscriptionComponent } from './pages/add-subscription/add-subscription.component';

const routes: Routes = [
  {
    path: '',
    component: LoginComponent
  },
  {
    path: 'admin-page',
    component: AdminPageComponent

  },
  {
    path: 'manage-client',
    component: ManageClientComponent

  },
  {
    path: 'add-subscription',
    component: AddSubscriptionComponent

  },
  {
    path: 'manage-subscriptions',
    component: ManageSubscriptionsComponent

  },
  {
    path: 'manage-cards',
    component: ManageCardsComponent

  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'card',
    component: CardComponent
  },
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
