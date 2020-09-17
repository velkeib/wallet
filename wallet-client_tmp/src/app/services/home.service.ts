import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  constructor(private httpclient: HttpClient) { }

getExpenses(): Observable<any>{
    return this.httpclient.get("http://localhost:8080/expenses");
}

getUsers(): Observable<any>{
  return this.httpclient.get("http://localhost:8080/users");
}

setExpenses(post: string): Observable<any>{
    return this.httpclient.post("http://localhost:8080/expenses", post);
}
}
