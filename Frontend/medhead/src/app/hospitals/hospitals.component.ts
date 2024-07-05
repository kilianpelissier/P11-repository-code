import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

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
    this.http.get('http://localhost:9000/hospitals').subscribe((data: any) => {
      this.hospitals = data;
    });
  }

  loadSpecializations() {
    this.http.get('http://localhost:9000/specializations').subscribe((data: any) => {
      this.specializations = data;
    });
  }

  async onSubmit() {
    console.log("hey bg");
    const selectedHospital = +this.form.value.hospital;
    const selectedSpecialization = this.form.value.specialization;
    console.log(`Selected Hospital: ${selectedHospital}, Selected Specialization: ${selectedSpecialization}`);
    
    const hospitalsWithSpecialization: any = await this.http.get(`http://localhost:9000/hospitals/specialization/${selectedSpecialization}`).toPromise();
    console.log(hospitalsWithSpecialization);
    let closestDistance = Infinity;
    let closestHospitalName = "";

    // Fonction de délai
    const delay = (ms: number) => new Promise(resolve => setTimeout(resolve, ms));

    for (const hospital of hospitalsWithSpecialization) {
      const hospitalId = +hospital.id;
      if (hospitalId !== selectedHospital) {
        // Introduire un délai avant chaque requête
        await delay(550); // Délai de 100 ms, ajustez selon les besoins

        const response: any = await this.http.get(`http://localhost:9000/distance/${selectedHospital}/${hospitalId}`).toPromise();
        console.log(response);
        if (response && response.distances && response.distances[0] && response.distances[0][0] < closestDistance) {
          closestDistance = response.distances[0][0];
          closestHospitalName = hospital.name;
        }
      }
    }
    this.closestHospital = closestHospitalName;
}
}