import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {RegisterComponent} from "./pages/register/register.component";
import {ChatroomComponent} from "./pages/chatroom/chatroom.component";
import {LoginComponent} from "./pages/login/login.component";

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'chatroom',
    component: ChatroomComponent,
  },
  {
    path: '',
    redirectTo: 'chatroom',
    pathMatch: "full"
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
