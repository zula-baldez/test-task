import {Component} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {MatDialog} from "@angular/material/dialog";
import {PopupComponent} from './popup.component';

export interface NameDTO {
  name: string
}

export interface AgeDTO {
  age: bigint,
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  inputText: string = '';
  url: string = "http://localhost:8080"
  old: string = ""
  result: string = ""


  constructor(private http: HttpClient, private dialog: MatDialog) {
    this.http.get<NameDTO>(this.url + "/max-age").subscribe((data: NameDTO) => {
      this.old = data.name.toString();
    })
  }

  getAgeByName() {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("name", this.inputText);
    this.http.get<AgeDTO>(this.url + "/age", {params: queryParams}).subscribe((data: AgeDTO) => {
      this.result = data.age.toString()
      this.dialog.open(PopupComponent, {
        data: {message: "age is: " + data.age.toString()}
      });

    });
  }

  getFrequencyByName() {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("name", this.inputText);
    this.http.get<AgeDTO>(this.url + "/request-statistics", {params: queryParams}).subscribe((data: AgeDTO) => {
      this.result = data.age.toString()
      this.dialog.open(PopupComponent, {
        data: {message: "name was requested: " + data.age.toString() + " times"}
      });
    });
  }


}
