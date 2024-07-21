import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-result',
  standalone: true,
  imports: [],
  templateUrl: './result.component.html',
  styleUrl: './result.component.css'
})
export class ResultComponent implements OnInit {
  selectedRestaurant: string;

  constructor(private router: Router) {
    const navigation = this.router.getCurrentNavigation()
    const state = navigation?.extras?.state as { data: string }
    this.selectedRestaurant = state?.data || 'No restaurant selected'
  }

  ngOnInit(): void {
  }

  goBack():void{
    this.router.navigate(['/'])
  }
}