import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'medhead';

  constructor(public http: HttpClient) {}
  ngOnInit(): void {
    this.http.get('http://localhost:9000/hospitals/').subscribe(response => console.log(response));
  }

}
