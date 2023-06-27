import {Component, OnInit} from '@angular/core';
import {ProductService} from "../../services/product/product.service";
import {StateService} from "../../globals/globals";
import {User} from "../../customTypes/User";
import {PaymentMethodService} from "../../services/paymentMethod/payment-method.service";

@Component({
  selector: 'app-purchase',
  templateUrl: './purchase.component.html',
  styleUrls: ['./purchase.component.css']
})
export class PurchaseComponent implements OnInit{
  purchaseItems: any[] = [];
  coverImageItem: any;
  items: any[] = [];
  userData: User | undefined;
  // items: any[] = [
  //   {id:1, title: 'ime1', price: 9.99},
  //   {id:1, title: 'ime1', price: 9.99},
  //   {id:1, title: 'ime1', price: 9.99},
  //   {id:1, title: 'ime1', price: 9.99},
  //   {id:1, title: 'ime1', price: 9.99}
  // ];
  loading: boolean = false;
  public constructor(private productService: ProductService,
                     private stateService: StateService,
                     private paymentMethodService: PaymentMethodService
  ) {
  }
  ngOnInit(): void {
    this.loading = true;
    //global variable
    this.userData = this.stateService.getUserData();
    // this.stateService.userDataChanged.subscribe((value) => {
    //   this.userData = value;
    // });

    this.productService.getPurchaseForUserId(this.userData?.id).subscribe((result: any) => {
      // console.log(result);

      Array.from(result).forEach((item: any) => {
        console.log("item", item);
        this.productService.getProductById(item.fkProductPurchase).subscribe((res:any) => {
          console.log("res", res);
          this.paymentMethodService.getPaymenthMethodById(item.fkPayment).subscribe((r:any) => {
            console.log("r", r);
            this.productService.getCoverPhotoForProductId(item.fkProductPurchase).subscribe((a: any) => {
              console.log("a", a);
              this.purchaseItems.push({title: res.title, price: res.price, payment: r.name, coverImage: URL.createObjectURL(a)});
            });


          })
        });
        // console.log(item);
      });
      this.loading = false;
    });

    // console.log(this.userData);
  }

}
