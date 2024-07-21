import { Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { ResultComponent } from './result/result.component';
import { SessionComponent } from './session/session.component';

export const routes: Routes = [ 
      { path: '', component: SessionComponent },
      { path: 'result', component: ResultComponent },
     
];
