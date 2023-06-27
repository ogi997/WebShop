import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment.development";
import {Question} from "../../customTypes/Question";

@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  constructor(private http: HttpClient) { }

  public addQuestion(data: any) {
    return this.http.post(environment.apiUrl + "/questions", data, {headers: environment.headerOption});
  }
  public getQuestionsForUserId(userId: any) {
    return this.http.get(environment.apiUrl + `/questions/user/${userId}`, {headers: environment.headerOption});
  }
  public getQuestionById(questionId: any) {
    return this.http.get<Question>(environment.apiUrl + `/questions/${questionId}`, {headers: environment.headerOption});
  }
  public getQuestionByProductId(productId: any) {
    return this.http.get(environment.apiUrl + `/questions/product/${productId}`, {headers: environment.headerOption});
  }
}
