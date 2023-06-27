import {Component, OnInit} from '@angular/core';
import {CategoryService} from "../../services/category/category.service";
import {Category} from "../../customTypes/Category";

@Component({
  selector: 'app-all-products',
  templateUrl: './all-products.component.html',
  styleUrls: ['./all-products.component.css']
})
export class AllProductsComponent implements OnInit{

  public constructor() {
  }
  ngOnInit(): void {

  }

}
