import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddUserComponent } from './pages/add-user/add-user.component';
import { EmployeeComponent } from './pages/employee/employee.component';
import { HomeLayoutComponent } from './pages/home-layout/home-layout.component';
import { LoginComponent } from './pages/login/login.component';
import { RolesComponent } from './pages/roles/roles.component';
import { UserDashboardComponent } from './pages/user-dashboard/user-dashboard.component';
import { UserLayoutComponent } from './pages/user-layout/user-layout.component';
import { UserListComponent } from './pages/user-list/user-list.component';
import { QuizAppComponent } from './pages/quiz-app/quiz-app.component';
import { StepperCrudComponent } from './pages/stepper-crud.component/stepper-crud.component';
import { CardComponent } from './pages/card/card.component';
import { AdminPageComponent } from './pages/admin-page/admin-page.component';

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
    path: 'quizApp',
    component: QuizAppComponent
  },
  {
    path: 'newEmp',
    component: EmployeeComponent
  },
  {
    path: 'stepperCrud',
    component: StepperCrudComponent
  },
  {
    path: 'Courses',
    component: RolesComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'card',
    component: CardComponent
  },
  {
    path: 'courses',
    component: RolesComponent
  },
  
  {
    path: '',
    component: UserLayoutComponent,
    children: [
      {
        path: 'addUser',
        component: AddUserComponent
      },
      {
        path: 'user-dashboard',
        component: UserDashboardComponent
      },
      {
        path: 'user-list',
        component: UserListComponent
      }
    ]
  },
  {
    path: '',
    component: HomeLayoutComponent,
    children: [
      {
        path: 'way2addUser',
        component: AddUserComponent
      },
      {
        path: 'way2user-dashboard',
        component: UserDashboardComponent
      },
      {
        path: 'way2user-list',
        component: UserListComponent
      },
      {
        path: 'way2employee',
        component: EmployeeComponent
      },
      {
        path: 'way2roles',
        component: RolesComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
