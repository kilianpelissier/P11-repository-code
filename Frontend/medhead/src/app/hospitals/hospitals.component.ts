import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-hospitals',
  templateUrl: './hospitals.component.html',
  styleUrls: ['./hospitals.component.css']
})
export class HospitalsComponent implements OnInit {
  form: FormGroup;
  hospitals: any[] = [];
  specializations: any[] = [];
  closestHospital: string = '';
  distance: number = 0;
  coordonates: any[] = [];
  address: string = '';
  time: number = 0;
  isLoading = false;

  private apiKey = '203f16bd-d289-452c-9371-b0e042a64ef4';
  private headers = new HttpHeaders().set('X-API-KEY', this.apiKey);

  constructor(private fb: FormBuilder, private http: HttpClient) {
    this.form = this.fb.group({
      hospital: [''],
      specialization: ['']
    });
  }

  ngOnInit(): void {
    this.loadHospitals();
    this.loadSpecializations();
  }

  loadHospitals() {
    console.log(this.headers);
    this.http.get('http://localhost:9000/hospitals', { headers: new HttpHeaders().set('X-API-KEY', '203f16bd-d289-452c-9371-b0e042a64ef4') }).subscribe((data: any) => {
      this.hospitals = data;
    });
  }

  loadSpecializations() {
    this.http.get('http://localhost:9000/specializations', { headers: this.headers }).subscribe((data: any) => {
      this.specializations = data;
    });
  }

  async onSubmit() {
    const selectedHospital = +this.form.value.hospital;
    const selectedSpecialization = this.form.value.specialization;
    
    const hospitalsWithSpecialization: any = await this.http.get(`http://localhost:9000/hospitals/specialization/${selectedSpecialization}`, { headers: this.headers }).toPromise();
    let closestDistance = Infinity;
    let closestHospitalName = "";
    let coordonatesClosestHospital = [];
    let addressClosestHospital = "";
    let timeToHospital = 0;
    
    // Fonction de délai
    const delay = (ms: number) => new Promise(resolve => setTimeout(resolve, ms));
    this.isLoading = true;
    // Simulez la récupération des données de l'hôpital
    // Remplacez ceci par votre logique de récupération des données
    setTimeout(() => {
      this.isLoading = false;
      // Logique pour définir les données de l'hôpital
    }, 20000); // Supposons que cela prend 20 secondes pour obtenir les données

    for (const hospital of hospitalsWithSpecialization) {
      const hospitalId = +hospital.id;
      if (hospitalId !== selectedHospital) {
        // Introduire un délai avant chaque requête
        await delay(550); // Délai de 550 ms: à modifier selon l'accès à l'API

        const response: any = await this.http.get(`http://localhost:9000/distance/${selectedHospital}/${hospitalId}`, { headers: this.headers }).toPromise();
        if (response && response.distances && response.distances[0] && response.distances[0][0] < closestDistance) {
          closestDistance = response.distances[0][0];
          closestHospitalName = hospital.name;
          coordonatesClosestHospital = hospital.gps;
          addressClosestHospital = hospital.address;
          timeToHospital = response.distances[0][1];
        }
      }
    }
    this.closestHospital = closestHospitalName;
    this.distance = closestDistance / 1000;
    this.coordonates = coordonatesClosestHospital;
    this.address = addressClosestHospital;
    this.time = (timeToHospital / 1000) / 60;
  }
}