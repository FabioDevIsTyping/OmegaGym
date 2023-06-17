import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddUserComponent } from './pages/add-user/add-user.component';
import { UserListComponent } from './pages/user-list/user-list.component';
import { UserDashboardComponent } from './pages/user-dashboard/user-dashboard.component';
import { RolesComponent } from './pages/roles/roles.component';
import { EmployeeComponent } from './pages/employee/employee.component';
import { LoginComponent } from './pages/login/login.component';
import { UserLayoutComponent } from './pages/user-layout/user-layout.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { HomeLayoutComponent } from './pages/home-layout/home-layout.component';
import { VoidTableComponent } from './reusable/void-table/void-table.component';
import { TabsComponent } from './reusable/tabs/tabs.component';
import { QuizAppComponent } from './pages/quiz-app/quiz-app.component';
import { StepperCrudComponent } from './pages/stepper-crud.component/stepper-crud.component';
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
    AddUserComponent,
    UserListComponent,
    UserDashboardComponent,
    RolesComponent,
    EmployeeComponent,
    LoginComponent,
    UserLayoutComponent,
    HomeLayoutComponent,
    VoidTableComponent,
    TabsComponent,
    QuizAppComponent,
    StepperCrudComponent,
    CardComponent,
    NavbarComponent,
    AdminPageComponent,
    ManageClientComponent,
    ManageCardsComponent,
    ManageSubscriptionsComponent,
    AddSubscriptionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
