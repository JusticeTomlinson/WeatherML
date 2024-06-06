// src/app/activity.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ActivityService {
  private apiUrl = 'http://localhost:8080/api/getActivities'; // Adjust the URL if necessary

  constructor(private http: HttpClient) { }

  getActivities(): Observable<string> {
    return this.http.get(this.apiUrl, { responseType: 'text' });
  }
}
