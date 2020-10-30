import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  constructor(private httpclient: HttpClient) { }

getExpenses(groupId: String): Observable<any>{
    return this.httpclient.get("http://localhost:8080" + groupId);
}

getUsers(): Observable<any>{
  return this.httpclient.get("http://localhost:8080/users");
}

setExpenses(post: string, groupId: String): Observable<any>{
    return this.httpclient.post("http://localhost:8080" + groupId, post);
}
}
