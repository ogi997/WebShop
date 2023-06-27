import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './components/login/login.component';
import { CentralComponent } from './components/central/central.component';
import { RegisterComponent } from './components/register/register.component';
import { FooterComponent } from './components/footer/footer.component';
import { HeaderComponent } from './components/header/header.component';
import {MatGridListModule} from "@angular/material/grid-list";
import { ProductsComponent } from './components/products/products.component';
import { AllProductsComponent } from './components/all-products/all-products.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { OneProductComponent } from './components/one-product/one-product.component';
import {CommonModule, NgOptimizedImage} from "@angular/common";
import { ProfileComponent } from './components/profile/profile.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { PurchaseComponent } from './components/purchase/purchase.component';
import { OglasiComponent } from './components/oglasi/oglasi.component';
import { NoviOglasComponent } from './components/novi-oglas/novi-oglas.component';
import {HttpClientModule} from "@angular/common/http";
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {CdkFixedSizeVirtualScroll, CdkVirtualForOf, ScrollingModule} from "@angular/cdk/scrolling";
// import {MatCardModule} from "@angular/material/card";
import { QuestionsComponent } from './components/questions/questions.component';
import { SupportComponent } from './components/support/support.component';
import {MatPaginatorModule} from "@angular/material/paginator";
import {NgxPaginationModule} from "ngx-pagination";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import { ActivateAccountComponent } from './components/activate-account/activate-account.component';
// import {AgVirtualScrollModule} from "ag-virtual-scroll";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    CentralComponent,
    RegisterComponent,
    FooterComponent,
    HeaderComponent,
    ProductsComponent,
    AllProductsComponent,
    NotFoundComponent,
    OneProductComponent,
    ProfileComponent,
    PurchaseComponent,
    OglasiComponent,
    NoviOglasComponent,
    QuestionsComponent,
    SupportComponent,
    ActivateAccountComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatGridListModule,
    NgOptimizedImage,
    FormsModule,
    CommonModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatSnackBarModule,
    // CdkFixedSizeVirtualScroll,
    // CdkVirtualForOf,
    ScrollingModule,
    NgxPaginationModule,
    MatPaginatorModule,
    MatProgressSpinnerModule,
    // MatCardModule,
    // AgVirtualScrollModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
