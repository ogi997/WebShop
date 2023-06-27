import {Component, OnInit} from '@angular/core';
import {CategoryService} from "../../services/category/category.service";
import {Category} from "../../customTypes/Category";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Observable} from "rxjs";
import {Attribute} from "../../customTypes/Attribute";
import {Product} from "../../customTypes/Product";
import {User} from "../../customTypes/User";
import {StateService} from "../../globals/globals";
import {MatSnackBar} from "@angular/material/snack-bar";
import {ProductService} from "../../services/product/product.service";
import {AttributeValues} from "../../customTypes/AttributeValues";
import {Router} from "@angular/router";
import {SupplyService} from "../../services/supply/supply.service";
import {Supply} from "../../customTypes/Supply";
// import {Observable} from "rxjs";

@Component({
  selector: 'app-novi-oglas',
  templateUrl: './novi-oglas.component.html',
  styleUrls: ['./novi-oglas.component.css']
})
export class NoviOglasComponent implements OnInit {
  // @ts-ignore
  public categoriesArray: Category[];
  // @ts-ignore
  public attributes: Attribute[];

  // @ts-ignore
  public images: FileList | null;
  // @ts-ignore
  public product: Product = {};

  // @ts-ignore
  userData: User | undefined = {};


  public form: FormGroup = new FormGroup({});

  public constructor(private categoryService: CategoryService,
                     private formBuilder: FormBuilder,
                     private stateService: StateService,
                     private snackBar: MatSnackBar,
                     private productService: ProductService,
                     private router: Router,
                     private supplyService: SupplyService
  ) {
  }

  ngOnInit() {
    this.form = this.formBuilder.group({
      title: [null, Validators.required],
      price: [null, Validators.required],
      categoryId: [null, Validators.required],
      conditionProduct: [null, Validators.required],
      location: [null, Validators.required],
      description: [null, Validators.required],
      contact: [null, Validators.required],
    });

    this.getCategories();

    this.userData = this.stateService.getUserData()
    this.stateService.userDataChanged.subscribe((value) => {
      this.userData = value;
    });

    // console.log(this.userData);
    // console.log(this.categoriesArray);
  }

  public getCategories() {
    this.categoryService.getAllCategory().subscribe(res => {
      // console.log(res);
      this.categoriesArray = res;
      // console.log("Cat: ", res);
    })
  }
  public dodajOglas(form: FormGroup) {
    console.log(this.images);
    if (this.images === undefined) {
      this.snackBar.open("Dodajte slike za proizvod", undefined, {
        duration: 3000
      })
      return;
    }
    console.log("Pogledaj vrijednost");
    // let allVariable = form.value;
    // console.log(form.value);
    this.product.title = form.value.title;
    delete form.value.title;
    this.product.description = form.value.description;
    delete form.value.description;
    this.product.price = form.value.price;
    delete form.value.price;
    this.product.conditionProduct = form.value.conditionProduct;
    delete form.value.conditionProduct;
    this.product.fkCategory = form.value.categoryId;
    delete form.value.categoryId;
    this.product.contact = form.value.contact;
    delete form.value.contact;
    this.product.location = form.value.location;
    delete form.value.location;
    this.product.fkUser = this.userData?.id;




    //dodavanje slika, vrijednostima za posebne atribute i da je dostupno za prodaju
    this.productService.addProduct(this.product).subscribe(res => {
      console.log("Kreiraj ne product", res);
      //dodavanje vrijednosti atributa
      Object.entries(form.value).forEach(item => {
        let attrValue: AttributeValues = {value: '', fkAttributeId: 0, fkProizvod: 0};
        console.log("ID:", item);
        // console.log("VALUE", item[1]);
        attrValue.value = item[1];
        attrValue.fkAttributeId = item[0];
        attrValue.fkProizvod = res.id;

        this.categoryService.addAttributeValues(attrValue).subscribe( res => {
          console.log("dodani attributes value", res);
        });
      });

      //dodavanje slika
      // let checkCover: boolean = true;

      // console.log(this.images?.length);

      // @ts-ignore
      for (let i = 0; i < this.images?.length; i++) {
        let formData: FormData = new FormData();
        // @ts-ignore
        formData.append('image', this.images?.item(i));
        // @ts-ignore
        formData.append('productId', res.id);
        // @ts-ignore
        formData.append('cover', (i === 0) ? 1 : 0);

        this.productService.addProductImages(formData).subscribe();
      }

      //dodavanje da je dostupno za prodaju
      // let supply: Supply = {
      //   // id: 0,
      //   fkProd: res.id,
      //   active: 1
      // }
      this.supplyService.addSupply({fkProd: res.id, active: 1}).subscribe(res => {
        console.log("Dodan novi supply", res);
      })
    });


    this.snackBar.open("Uspjesno ste dodali proizvod", undefined, {
      duration: 3000
    });

    this.router.navigate(['/']);

    // form.reset();

  }


  public handleChangeCategory() {

    let categoryId: number = this.form.value.categoryId;
    this.categoryService.getAttributesForCategoryId(categoryId).subscribe(res => {
      this.attributes = res;

      //dodaj dinamicki kontrole za formu
        res.forEach(r => {
         this.form.addControl(r.id.toString(), new FormControl(null, Validators.required))
        })
      });
      // console.log(object);
    }

  public handleImageChange($event: Event) {
    const inputElement = $event.target as HTMLInputElement;
    // @ts-ignore
    const fileList: FileList = inputElement.files;

    if (fileList.length > 0) {
      this.images = fileList;
    } else {
      this.images = null;
    }
  }
}
