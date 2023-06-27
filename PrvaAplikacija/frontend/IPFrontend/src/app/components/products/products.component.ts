import {ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit, ViewChild} from '@angular/core';
import {Router} from "@angular/router";
import {SupplyService} from "../../services/supply/supply.service";
import {ProductService} from "../../services/product/product.service";
import {Observable, of} from "rxjs";
import {Product} from "../../customTypes/Product";
import {Page} from "../../customTypes/Page";
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {StateService} from "../../globals/globals";
import {Category} from "../../customTypes/Category";
import {CategoryService} from "../../services/category/category.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
// import {ScrollingModule} from "@angular/cdk/scrolling";
// import {AgVirtualScrollModule} from "ag-virtual-scroll";
// import {BehaviorSubject} from "rxjs";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
  // changeDetection: ChangeDetectionStrategy.OnPush
  // changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ProductsComponent implements OnInit{

  items: any[] = [];

  allCategory: Category[] = [];

  public form: FormGroup = new FormGroup({});

  selectedSearchTitle: string | undefined;
  // @ts-ignore
  @ViewChild('paginator') paginator: MatPaginator;
  // @ts-ignore
  // data$: Observable<{ added: Product; coverImage: string }>;
  // items: any[]; // Vaši podaci koje želite prikazati

  constructor(private router: Router,
              private supplyService: SupplyService,
              private productService: ProductService,
              private stateService: StateService,
              private categoryService: CategoryService,
              private snackBar: MatSnackBar,
              private formBuilder: FormBuilder

  ) {

    // this.items = [{cover: 'aa', added: {title: 'a', price: 2}}];
  }
  loading: boolean = false;
  ngOnInit(): void {
    this.categoryService.getAllCategory().subscribe((result) => {
      this.allCategory = result;
    });

    this.form = this.formBuilder.group({
      condProduct: [null],
      priceOd: [null],
      priceDo: [null],
      location: [null]
    })

    this.getData();

  }

  // @ts-ignore
  page: Page;
  currentPage: number = 0; // Trenutna stranica
  sortBy: string = 'id'; // Ukupan broj stavki
  totalItems = 0;
  pageSize = 12; //koliko se prikazuje proizvoda na jednoj stranici


  public getData() {
    this.items = [];
    // this.currentPage = 0;//sada
    this.totalItems = 0;
    // this.paginator.pageIndex = 0;
    // this.stateService.setLoadingSupplyProduct(true);
    this.loading = true;
    console.log("currentPage: ", this.currentPage + " pageSize: ", this.pageSize);
    this.supplyService.getProducts(this.currentPage, this.pageSize, this.sortBy)
      .subscribe((page1:any) => {
        console.log("page", page1);
        page1.content.forEach( (item:any) => {
        //   console.log("item", item);
          this.productService.getProductById(item.fkProd).subscribe(res1 => {
            this.productService.getCoverPhotoForProductId(item.fkProd).subscribe(res2 => {
              this.items.push({coverImage: URL.createObjectURL(res2), added: res1});
            });
          });
        });
        // this.page = page1;

        // this.page.content = this.items;
        this.totalItems = page1.totalPages;
        this.loading = false;
        // this.loading = false;
        // this.stateService.setLoadingSupplyProduct(false);
        // this.length = page.totalPages;
      });
  }

  onPageChange(page: number): void {
    console.log(page);
    // this.loading = true;
    this.currentPage = page;
    console.log("onPageChange", this.selectedCategory);
    if(this.selectedCategory === '-1' && (this.selectedSearchTitle === undefined || this.selectedSearchTitle === '') ) {
      console.log("opa");
      // this.paginator.pageIndex = 0;
      this.getData();
      return;
    }
    this.paginator.pageIndex = 0;

    if (this.selectedCategory !== '-1' && (this.selectedSearchTitle === undefined || this.selectedSearchTitle === ''))
      this.filtrirajPodatke(this.form, true);

    if (this.selectedCategory === '-1' && (this.selectedSearchTitle !== undefined || this.selectedSearchTitle !== ''))
    this.searchByTitle(true);
    // this.loading = false;
  }

  public openItem(itemId: number): void {
    // @ts-ignore
    this.router.navigateByUrl('/products/'+itemId);
    // this.supplyID = supplyId;
    // console.log("click:" + this.supplyID);
  }
  selectedCategory: any = '-1';
  reset: number = 0;
  attributes: any = [];


  public filterData(pageChange: boolean) {
    if (this.selectedCategory === '-1') {
      this.getData();
      this.attributes = [];
      return;
    }

    this.categoryService.getAttributesForCategoryId(this.selectedCategory).subscribe((result: any) => {
      console.log("att: ", result);
      this.attributes = result;
      //dodaj dinamicki kontrole za formu
      result.forEach( (r: any) => {
        this.form.addControl(r.id.toString(), new FormControl(null));
      });
    });

  }

  public filtrirajPodatke(form: FormGroup, pageChange: boolean) {
    console.log("Filtriranje: ", form.value);


    this.items = [];
    if (!pageChange) {
      this.currentPage = 0;
      this.paginator.pageIndex = 0;
    }

    const condProduct = form.value.condProduct;
    delete form.value.condProduct;
    const location = form.value.location;
    delete form.value.location;
    const priceOd = form.value.priceOd;
    delete form.value.priceOd;
    const priceDo = form.value.priceDo;
    delete form.value.priceDo;

    console.log("Filter after deleting: ", form.value);

    this.items = [];
    // this.currentPage = 0;//sada
    this.totalItems = 0;


    const filterArray:any[] = [];
    Object.entries(form.value).forEach((x) => {
      if (x[1] != null && x[1] != '')
        filterArray.push({ attributeId: x[0], attributeValue: x[1]});
      // console.log("ID: " + x[0] + " value: " + x[1]);
    });

    // if (this.selectedCategory === '-1') {
    //   console.log("Niste izabrali kategoriju");
    //   return;
    // }

    console.log(filterArray);
    if (filterArray.length === 0) {
      this.productService.filterByCategoryId(this.selectedCategory === '-1' ? '' : this.selectedCategory, condProduct == undefined ? '' : condProduct, priceOd == undefined ? '' : priceOd, priceDo == undefined ? '' : priceDo, location == undefined ? '' : location, this.currentPage, this.pageSize).subscribe((result: any) => {
        console.log("Samo kategorija: ", result);
        result.content.forEach( (item:any) => {
          //   console.log("item", item);
          this.productService.getProductById(item.id).subscribe(res1 => {
            this.productService.getCoverPhotoForProductId(item.id).subscribe(res2 => {
              this.items.push({coverImage: URL.createObjectURL(res2), added: res1});
            });
          });
        });
        this.totalItems = result.totalPages;
      });
    } else if (filterArray.length !== 0) {
      //getfiltered data
      this.productService.getFilteredData(this.selectedCategory, filterArray, condProduct == undefined ? '' : condProduct, priceOd == undefined ? '' : priceOd, priceDo == undefined ? '' : priceDo, location == undefined ? '' : location, this.currentPage, this.pageSize).subscribe((result: any) => {
        console.log("result filtered data: ", result);
        result.content.forEach( (item:any) => {
          //   console.log("item", item);
          this.productService.getProductById(item.id).subscribe(res1 => {
            this.productService.getCoverPhotoForProductId(item.id).subscribe(res2 => {
              this.items.push({coverImage: URL.createObjectURL(res2), added: res1});
            });
          });
        });

        this.totalItems = result.totalPages;

      });
    }


  }


  public filterByCategory(pageChange: boolean) {

    if (this.selectedCategory === '-1') {
      this.getData();
      // this.paginator.pageIndex = 0;
      return;
    }

    this.items = [];

    if(!pageChange){
      this.currentPage = 0;
      this.paginator.pageIndex = 0;
    }
    // this.totalItems = 0;
    // this.reset = 0;
    // console.log("filter", this.selectedCategory);

    // this.productService.filterByCategoryId(this.selectedCategory, this.currentPage, this.pageSize).subscribe((result:any) => {
    //   console.log(result);
    //   console.log("category", result.totalElements);
    //   this.totalItems = result.totalElements;
    //   result.content.forEach( (item:any) => {
    //     console.log("item", item);
    //       this.productService.getCoverPhotoForProductId(item.id).subscribe(res2 => {
    //         this.items.push({coverImage: URL.createObjectURL(res2), added: item});
    //       });
    //   });
    //
    // });

  }

  public searchByTitle(pageChange: boolean) {
    if (this.selectedSearchTitle === undefined) {
      this.snackBar.open("Morate unijeti neko ime za pretrazivanje.", undefined, {
        duration: 3000
      });
      return;
    }

    this.items = [];
    if (!pageChange) {
      this.currentPage = 0;
      this.paginator.pageIndex = 0;
    }

    // this.totalItems = 0;
    // this.reset = 0;
    // console.log("filter", this.selectedCategory);
    this.productService.searchByTitle(this.selectedSearchTitle, this.currentPage, this.pageSize).subscribe((result: any) => {
      console.log(result);
      this.selectedSearchTitle = undefined;
      console.log("category", result.totalElements);
      this.totalItems = result.totalPages;
      result.content.forEach( (item: any) => {
        console.log("item", item);
        this.productService.getCoverPhotoForProductId(item.id).subscribe(res2 => {
          this.items.push({coverImage: URL.createObjectURL(res2), added: item});
        });
      });
    });
    // console.log("Search", this.selectedSearchTitle);
  }

}
