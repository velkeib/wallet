import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class HomeService {
  

    constructor(private httpclient: HttpClient) { }

    getExpenses(): Observable<any>{
        return this.httpclient.get("http://localhost:8080/expenses");
    }

}