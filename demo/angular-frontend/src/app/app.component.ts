// src/app/app.component.ts
import { Component, OnInit } from '@angular/core';
import { ActivityService } from './activity.service';

@Component({
  selector: 'app-root',
  template: `
    <div *ngIf="activities">
      <h2>Recommended Activities</h2>
      <p>{{ activities }}</p>
    </div>
    <button (click)="fetchActivities()">Get Activities</button>
  `,
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  activities: string | undefined;

  constructor(private activityService: ActivityService) { }

  ngOnInit(): void {
  }

  fetchActivities(): void {
    this.activityService.getActivities().subscribe(
      data => this.activities = data,
      error => console.error('Error fetching activities', error)
    );
  }
}
