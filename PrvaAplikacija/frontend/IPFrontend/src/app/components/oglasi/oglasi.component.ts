import {Component, OnInit} from '@angular/core';
import {StateService} from "../../globals/globals";
import {ProductService} from "../../services/product/product.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-oglasi',
  templateUrl: './oglasi.component.html',
  styleUrls: ['./oglasi.component.css']
})
export class OglasiComponent implements OnInit{

  oglasiShow: any[] = [];
  userData: any;

  public constructor(private stateService: StateService,
                     private productService: ProductService,
                     private snackBar: MatSnackBar
                     ) {
  }
  ngOnInit(): void {
    this.userData = this.stateService.getUserData();

  this.getData();
  }
 loading: boolean = false;
  private getData() {
    this.oglasiShow = [];
    this.loading = true;
    this.productService.getAllProductsByUserId(this.userData?.id).subscribe((result: any) => {
      Array.from(result).forEach((item: any) => {
        this.productService.getCoverPhotoForProductId(item.id).subscribe(res => {
          this.productService.getSupplyByProductId(item.id).subscribe((res1: any) => {
            this.oglasiShow.push({productId: item.id, productTitle: item.title, productPrice: item.price, cover: URL.createObjectURL(res), productActive: res1.active})
          })
        });
      });
      this.loading = false;
    });
  }
  public changeActiveZavrsi(productId: any) {
    const data = {
      fkProd: productId,
      active: 0
    };

    this.productService.changeActiveByProductId(productId, data).subscribe((result: any) => {
      this.snackBar.open("Uspjesno ste zavrsili oglas.", undefined, {
        duration: 3000
      });
      this.getData();
    });
  }
  public changeActiveVrati(productId: any) {
    const data = {
      fkProd: productId,
      active: 1
    };
    this.productService.changeActiveByProductId(productId, data).subscribe((result) => {
      this.snackBar.open("Uspjesno ste zavrsili oglas.", undefined, {
        duration: 3000
      });
      this.getData();
    });
  }

  public softDelete(productId: any) {
    this.productService.deleteProduct(productId).subscribe((result: any) => {
      this.snackBar.open("Uspjeno ste obrisali proizvod.", undefined, {
        duration: 3000
      });
      this.getData();
    });
  }

  // items: any[] = [
  //   {id:1, title: 'ime1', price: 22, aktivna: 0},
  //   {id:1, title: 'ime1', price: 22, aktivna: 1},
  //   {id:1, title: 'ime1', price: 22, aktivna: 1},
  //   {id:1, title: 'ime1', price: 22, aktivna: 1},
  //   {id:1, title: 'ime1', price: 22, aktivna: 0},
  //   {id:1, title: 'ime1', price: 22, aktivna: 0},
  //   {id:1, title: 'ime1', price: 22, aktivna: 0},
  //   {id:1, title: 'ime1', price: 22, aktivna: 1},
  // ];

}
