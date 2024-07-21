import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { SessionComponent } from './session/session.component';
import { HttpClient } from '@angular/common/http'; 
import { ToastComponent } from './toast/toast.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet,ToastComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css' 
})
export class AppComponent {
  title = 'restaurant-picker-frontend';
}
