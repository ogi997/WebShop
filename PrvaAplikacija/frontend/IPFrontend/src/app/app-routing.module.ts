import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {CentralComponent} from "./components/central/central.component";
import {RegisterComponent} from "./components/register/register.component";
import {AllProductsComponent} from "./components/all-products/all-products.component";
import {NotFoundComponent} from "./components/not-found/not-found.component";
import {OneProductComponent} from "./components/one-product/one-product.component";
import {ProfileComponent} from "./components/profile/profile.component";
import {PurchaseComponent} from "./components/purchase/purchase.component";
import {OglasiComponent} from "./components/oglasi/oglasi.component";
import {NoviOglasComponent} from "./components/novi-oglas/novi-oglas.component";
import {GuardService} from "./services/guard/guard.service";
import {GuardRegisterService} from "./services/guardRegister/guard-register.service";
import {QuestionsComponent} from "./components/questions/questions.component";
import {SupportComponent} from "./components/support/support.component";
import {ActivateAccountComponent} from "./components/activate-account/activate-account.component";
import {GuardActivateAccountService} from "./services/guardActivateAccount/guard-activate-account.service";
const routes: Routes = [
  {
    path: '',
    component: CentralComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent,
    canActivate: [GuardRegisterService]
  },
  {
    path: 'products',
    component: AllProductsComponent
  },
  {
    path: 'products/:id',
    component: OneProductComponent
  },
  {
    path: 'profile',
    component: ProfileComponent, //samo za prijavljenje korisnike
    canActivate: [GuardService]
  },
  {
    path: 'purchase',
    component: PurchaseComponent, //samo za prijavljene korisnike
    canActivate: [GuardService]
  },
  {
    path: 'oglasi',
    component: OglasiComponent, //samo za prijavljene korisnike
    canActivate: [GuardService]
  },
  {
    path: 'novi-oglas',
    component: NoviOglasComponent, //samo za prijavljenje korisnike
    canActivate: [GuardService]
  },
  {
    path: 'question',
    component: QuestionsComponent, //samo za prijavljenje korisnike
    canActivate: [GuardService]
  },
  {
    path: 'support',
    component: SupportComponent, //samo za prijavljenje korisnike
    canActivate: [GuardService]
  },
  {
    path: 'activate-account',
    component: ActivateAccountComponent, //samo za prijavljenje korisnike kojima nalog nije aktiviran
    canActivate: [GuardActivateAccountService]
  },
  {
    path: '**',
    component: NotFoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
