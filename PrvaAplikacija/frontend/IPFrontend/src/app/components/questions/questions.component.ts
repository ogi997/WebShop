import {Component, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {QuestionService} from "../../services/question/question.service";
import {User} from "../../customTypes/User";
import {StateService} from "../../globals/globals";
import {UserService} from "../../services/user/user.service";
import {ProductService} from "../../services/product/product.service";
import {Question} from "../../customTypes/Question";
import {MatSnackBar} from "@angular/material/snack-bar";
import {AnswerService} from "../../services/answer/answer.service";

@Component({
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.css']
})
export class QuestionsComponent implements OnInit{

  userData: User | undefined;

  questionShow: any[] = [];
  loading: boolean = false;

  selectedQuestion: Question | undefined;
  selectedAnswer: any;

  @ViewChild('closebuttonOdgovor') closebuttonOdgovor: any;

  public constructor(private http: HttpClient,
                     private questionService: QuestionService,
                     private stateService: StateService,
                     private userService: UserService,
                     private productService: ProductService,
                     private snackBar: MatSnackBar,
                     private answerService: AnswerService
                     ) {
  }
  ngOnInit(): void {
    this.userData = this.stateService.getUserData();

    this.getData();

  }

  private getData() {
    this.loading = true;
    // this.questionShow = [];
    this.questionService.getQuestionsForUserId(this.userData?.id).subscribe((res: any) => {
      // console.log("Pitanja", res);
      Array.from(res).forEach((item: any) => {
        this.userService.getUserById(item.fkUserKo).subscribe((res1:any) => {
          // console.log("userService:", res1);

          this.productService.getProductById(item.fkProduct).subscribe((res2: any) => {
            this.answerService.existAnswerForQuestionId(item.id).subscribe((res3) => {
              this.questionShow.push({idPitanja: item.id, username: res1.username, productTitle: res2.title, answered: res3});
            });

            // console.log("res2", res2);
          })
        });
      });
      this.loading = false;
    });
  }

  public getIdPitanja(questionId: any) {
    this.questionService.getQuestionById(questionId).subscribe((res: Question) => {
      // console.log("res", res);
      this.selectedQuestion = res;
    });
  }

  public odgovori() {
    if (this.selectedAnswer === undefined) {
      this.snackBar.open("Unesite odgovor na pitanje!", undefined, {
        duration: 3000
      });
      return;
    }
    // console.log("selektovano pitanje", this.selectedQuestion);
    // console.log("selektovan odgovor", this.selectedAnswer);
    const data = {
      fkQuestion: this.selectedQuestion?.id,
      fkUserAnsKo: this.selectedQuestion?.fkUserKome,
      fkUserAnsKome: this.selectedQuestion?.fkUserKo,
      value: this.selectedAnswer
    }

    this.answerService.addAnswer(data).subscribe((res: any) => {
      this.snackBar.open("Uspjesno ste odgovorili na pitanje!", undefined, {
        duration: 3000
      });

      // console.log("qq", this.questionShow);
      this.questionShow = [];
      this.selectedAnswer = '';
      // console.log("qq", this.questionShow);
      //azuriraj vrijednost
      this.getData();

      this.closebuttonOdgovor.nativeElement.click();
    });
  }

}
