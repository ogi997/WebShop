import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment.development";

@Injectable({
  providedIn: 'root'
})
export class AnswerService {

  constructor(private http: HttpClient) { }

  public addAnswer(data: any) {
    return this.http.post(environment.apiUrl + "/answers", data, {headers: environment.headerOption});
  }
  public existAnswerForQuestionId(questionId: any) {
    return this.http.get<boolean>(environment.apiUrl + `/answers/exist/${questionId}`, {headers: environment.headerOption});
  }
  public getAnswerForQuestionId(questionId: any) {
    return this.http.get(environment.apiUrl + `/answers/${questionId}`, {headers: environment.headerOption});
  }
}
