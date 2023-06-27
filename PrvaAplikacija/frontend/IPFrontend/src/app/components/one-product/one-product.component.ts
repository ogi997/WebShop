import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ProductService} from "../../services/product/product.service";
import {UserService} from "../../services/user/user.service";
import {User} from "../../customTypes/User";
import {Product} from "../../customTypes/Product";
import {CategoryService} from "../../services/category/category.service";
import {ProductsComponent} from '../products/products.component';
import {StateService} from "../../globals/globals";
import {MatSnackBar} from "@angular/material/snack-bar";
import {QuestionService} from "../../services/question/question.service";
import {AnswerService} from "../../services/answer/answer.service";
@Component({
  selector: 'app-one-product',
  templateUrl: './one-product.component.html',
  styleUrls: ['./one-product.component.css']
})
export class OneProductComponent implements OnInit {
  // @ts-ignore
  public productId: string | null;
  selectedPaymentMethod: any;
  // @ts-ignore
  cardNumber: string;
  user: any;
  // @ts-ignore
  product: Product | null;
  imageToShowAvatar: any | null;
  // @ts-ignore
  categoryName: string | null;

  // @ts-ignore
  selectedQuestion: string | null;

  osobine: any[] = [];

  productImageSlider: any[] = [];
  // @ts-ignore
  coverImage: any | null;

  commentsToShow: any[] | null = [];

  userData: User | undefined;

  @ViewChild('closebuttonKupovina') closebuttonKupovina:any;
  @ViewChild('closebuttonPitanje') closebuttonPitanje: any;


  constructor(private route: ActivatedRoute,
              private productService: ProductService,
              private userService: UserService,
              private categoryService: CategoryService,
              private stateService: StateService,
              private snackBar: MatSnackBar,
              private questionService: QuestionService,
              private answerService: AnswerService,
              // private avatarServi

  ) {
  }

  ngOnInit() {
    this.route.paramMap
      .subscribe(params => {
        this.productId = params.get("id");
      });

    //global variable
    this.userData = this.stateService.getUserData();
    this.stateService.userDataChanged.subscribe((value) => {
      this.userData = value;
    });
    // console.log("SUPPLYID", this.productComponent.getSupplyID());

    //get product
    // number.
    // @ts-ignore
    this.productService.getProductById(+this.productId).subscribe(res => {
      console.log("Proizvod", res);
      this.product = res;
      //get cover photo for active in slider
      this.productService.getCoverPhotoForProductId(res.id).subscribe(a => {
        this.coverImage = URL.createObjectURL(a);
      });
      this.productService.getProductImageNonCover(res.id).subscribe((images: any) => {
        console.log("Images", images);
        Array.from(images).forEach((image: any) => {
          // console.log(image)
          this.productService.getImageByNameAndProductId(res.id, image).subscribe(r => {
            console.log("r", r);
            // let dodaj = URL.createObjectURL(r);
            this.productImageSlider.push(URL.createObjectURL(r));
          });
        });
      });

      this.categoryService.getCategoryById(res.fkCategory).subscribe((cat: any) => {
        console.log("category", cat);

        this.categoryName = cat.name;
      })

      this.categoryService.getAttributeValueForProductId(res.id).subscribe((res5: any) => {
        console.log("attrValue", res5);
        Array.from(res5).forEach((item: any) => {
          console.log(item);

          this.categoryService.getAttributeById(item.fkAttributeId).subscribe((res6: any) => {
            console.log(res6);

            this.osobine.push({value: item.value, name: res6.name})
          })
        })
        // this.categoryService.getAttributeById(res5.fkAttributeId);
      });



      //get user
      this.userService.getUserById(res.fkUser).subscribe((res1: any) => {
        console.log("Korisnik", res1);
        this.user = res1;
      });

      this.userService.getAvatarByUserId(res.fkUser).subscribe(res3 => {

        this.imageToShowAvatar = URL.createObjectURL(res3);
      });

    });


    //getting question and answers
    this.getQuestionAndAnswers();
  }

  public getQuestionAndAnswers() {
    this.questionService.getQuestionByProductId(this.productId).subscribe((result: any) => {
      // console.log("question", result);
      Array.from(result).forEach((item: any) => {

        console.log("question", item);
        this.answerService.getAnswerForQuestionId(item.id).subscribe((res1:any) => {
          console.log("answer", res1);

          if (res1 !== null) {
            this.dodajPitanjeiOdgovor(item, res1);
          } else {
            this.dodajPitanje(item);
          }

        });
      });
    });
  }

  private dodajPitanjeiOdgovor(item: any, res1: any) {

    this.userService.getUserById(item.fkUserKo).subscribe((res: any) => {
      console.log("userKO", res);
      this.userService.getAvatarByUserId(res.id).subscribe((res2) => {
        console.log("avatar", res2);

        //get user ko je odgovorio
        this.userService.getUserById(res1.fkUserAnsKo).subscribe((x: any) => {
          this.userService.getAvatarByUserId(x.id).subscribe((y: any) => {
            const data = {
              question: item.value,
              answer: res1.value,
              firstNameQuestion: res.firstName,
              lastNameQuestion: res.lastName,
              usernameQuestion: res.username,
              avatarQuestion: URL.createObjectURL(res2),
              firstNameAnswer: x.firstName,
              lastNameAnswer: x.lastName,
              usernameAnswer: x.username,
              avatarAnswer: URL.createObjectURL(y)
            }
            this.commentsToShow?.push(data);
          });
        });
      })
    });

  }

  private dodajPitanje(item: any) {

    this.userService.getUserById(item.fkUserKo).subscribe((res: any) => {
      console.log("userKO", res);
      this.userService.getAvatarByUserId(res.id).subscribe((res2) => {
        console.log("avatar", res2);
            const data = {
              question: item.value,
              answer: null,
              firstNameQuestion: res.firstName,
              lastNameQuestion: res.lastName,
              usernameQuestion: res.username,
              avatarQuestion: URL.createObjectURL(res2),
              firstNameAnswer: null,
              lastNameAnswer: null,
              usernameAnswer: null,
              avatarAnswer: null
            }
            this.commentsToShow?.push(data);
      })
    });

  }

  public kupiProizvod() {
    console.log("opa");
    switch (this.selectedPaymentMethod) {
      case '1': {
        let data = {
          fkUse: this.userData?.id,
          fkProductPurchase: this.productId,
          fkPayment: 1
        };
        console.log("data", data);
        this.productService.purchaseProduct(data).subscribe(res => {
          // console.log("kupljeno");
          this.closebuttonKupovina.nativeElement.click();
          this.snackBar.open("Uspjesno ste kupili proizvod", undefined, {
            duration: 3000
          });
        });
      } break;
      case '2': {
        if (this.cardNumber === undefined) {
          this.snackBar.open("Unesite broj kartice", undefined, {
            duration: 3000
          });
          return;
        }
        let data = {
          fkUse: this.userData?.id,
          fkProductPurchase: this.productId,
          fkPayment: 2,
          cardNumber: this.cardNumber
        }
        console.log("data", data);
        this.productService.purchaseProduct(data).subscribe(res => {
          this.closebuttonKupovina.nativeElement.click();
          this.snackBar.open("Uspjesno ste kupili proizvod", undefined, {
            duration: 3000
          });
        });
        // console.log("kartica br", this.cardNumber);
      } break;
      default: return;
    }
  }

  public posaljiPitanje() {
    // @ts-ignore
    this.productService.getProductById(this.productId).subscribe((res: any) => {

      let data = {
        fkProduct: this.productId,
        fkUserKo: this.userData?.id,
        fkUserKome: res.fkUser,
        value: this.selectedQuestion
      };

      this.questionService.addQuestion(data).subscribe((res1) => {
        this.snackBar.open("Uspjesno ste poslali pitanje", undefined, {
          duration: 3000
        });
        this.closebuttonPitanje.nativeElement.click();

        this.getQuestionAndAnswers();
      });

      // console.log("ko salje", this.userData);
      // console.log("pitanje",this.selectedQuestion);
      // console.log("kome se salje", res.fkUser);
    });

  }


}
