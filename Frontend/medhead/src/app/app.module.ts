import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router'; // Importez RouterModule ici
import { AppComponent } from './app.component';
import { HospitalsComponent } from './hospitals/hospitals.component';

@NgModule({
  declarations: [
    AppComponent,
    HospitalsComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot([]) // Ajoutez RouterModule aux imports avec un tableau de routes vide ou vos routes existantes
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }