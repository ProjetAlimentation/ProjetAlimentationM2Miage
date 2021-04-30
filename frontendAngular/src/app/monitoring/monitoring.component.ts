import { Component, OnInit } from '@angular/core';
import {FormGroup, FormBuilder, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {BackendService} from '../backend.service';

@Component({
  selector: 'app-monitoring',
  templateUrl: './monitoring.component.html',
  styleUrls: ['./monitoring.component.css']
})
export class MonitoringComponent implements OnInit {

  monitoringForm: FormGroup;
  constructor( private formBuilder: FormBuilder, private router: Router, public contactService: BackendService)
  {
  }

  ngOnInit(): void {

  }
  entrerMonitoring(weight, etat, diet): void
  {

     console.log(weight) ;
     console.log(etat);
     console.log(diet) ;
  }

}
