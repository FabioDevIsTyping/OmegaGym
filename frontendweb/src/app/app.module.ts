import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './pages/login/login.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CardComponent } from './pages/card/card.component';
import { NavbarComponent } from './navbar/navbar.component';
import { AdminPageComponent } from './pages/admin-page/admin-page.component';
import { ManageClientComponent } from './pages/manage-client/manage-client.component';
import { ManageCardsComponent } from './pages/manage-cards/manage-cards.component';
import { ManageSubscriptionsComponent } from './pages/manage-subscriptions/manage-subscriptions.component';
import { AddSubscriptionComponent } from './pages/add-subscription/add-subscription.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    CardComponent,
    NavbarComponent,
    AdminPageComponent,
    ManageClientComponent,
    ManageCardsComponent,
    ManageSubscriptionsComponent,
    AddSubscriptionComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
