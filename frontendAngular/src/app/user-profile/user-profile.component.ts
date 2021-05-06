import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {BackendService} from '../backend.service';
// @ts-ignore
import {ProfileInterface} from '../models/ProfileInterface';
import {User} from '../models/User';
import {MatSnackBar} from '@angular/material/snack-bar';


@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  profileForm: FormGroup;
  showMsg = false;

  allergenMap = new Map([
    ['GLUTEN_FREE', 'Sans gluten'],
    ['LACTOSE_FREE', 'Sans lactose'],
    ['EGG_FREE', 'Sans oeufs'],
    ['NUTS_FREE', 'Sans fruits à coque'],
    ['PEANUT_FREE', 'Sans arachides'],
    ['SOJA_FREE', 'Sans soja']
  ]);

  disabilityMap = new Map([
    ['DIABETIC', 'Diabétique'],
    ['CHOLESTEROL', 'Cholesterol'],
    ['HIGH_BLOOD_PRESSURE', 'Hypertension artérielle']
  ]);

  regimeMap = new Map([
    ['VEGETARIAN', 'Végétarien'],
    ['VEGETALIEN', 'Végétalien']
  ]);



  allergenSelected: string;
  regimeSelected: string;


  userProfile: ProfileInterface;

  constructor(private formBuilder: FormBuilder, private backendService: BackendService, private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    const username = localStorage.getItem('token');

    this.backendService.getProfile(username).subscribe(o => {
      this.userProfile = o;

      this.profileForm = new FormGroup({
        weight: new FormControl(o.weight),
        age: new FormControl(o.age),
        allergen: new FormControl(o.allergen),
        regime: new FormControl(o.regime),
      });

      this.allergenSelected = this.userProfile.allergen;
      this.regimeSelected = this.userProfile.regime;
    });
  }


  submit(): void{
    const username = localStorage.getItem('token');
    const formValues = this.profileForm.value;


    this.userProfile.age = formValues.age;
    this.userProfile.weight = formValues.weight;
    this.userProfile.allergen = this.allergenSelected;
    this.userProfile.regime = this.regimeSelected;

    const user: User = {
      username
    };

    this.userProfile.user = user;

    this.backendService.saveProfile(this.userProfile).subscribe();
    this.snackBar.open('Validé', '', {duration: 2000});

  }

}
