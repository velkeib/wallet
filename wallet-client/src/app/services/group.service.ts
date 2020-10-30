import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class GroupService {

  constructor(private httpclient: HttpClient) { }

  getGroups(): Observable<any>{
    return this.httpclient.get("http://localhost:8080/getgroups");
  }

}
