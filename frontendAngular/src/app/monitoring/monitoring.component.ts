import { Component, OnInit } from '@angular/core';
import {FormGroup, FormBuilder, Validators, FormControl} from '@angular/forms';
import {Router} from '@angular/router';
import {BackendService} from '../backend.service';
import {ChartDataSets} from 'chart.js';
import {Color, Label} from 'ng2-charts';



@Component({
  selector: 'app-monitoring',
  templateUrl: './monitoring.component.html',
  styleUrls: ['./monitoring.component.css']
})
export class MonitoringComponent implements OnInit {
  constructor( private formBuilder: FormBuilder, private router: Router, public backendService: BackendService)
  {
  }

  monitoringForm: FormGroup;


  lineChartData: ChartDataSets[] = [
    { data: [], label: 'Poids' },
    { data: [], label: 'Diète' },
    { data: [], label: 'Bien-être' },
  ];

  lineChartLabels: Label[];

  lineChartOptions = {
    responsive: true,
  };

  lineChartColors: Color[] = [
    {
      borderColor: 'black',
      backgroundColor: 'rgba(255,255,0,0.28)',
    },
  ];

  lineChartLegend = true;
  lineChartPlugins = [];
  lineChartType = 'line';

  ngOnInit(): void {
    this.monitoringForm = this.formBuilder.group({
      weight: [null, [Validators.required]],
      diet: [null, Validators.required],
      etat: [null, Validators.required]
    });

    this.backendService.getMonitoring().subscribe(o => {
        this.lineChartLabels = o.map(x => x.date);
        this.lineChartData[0].data = o.map(x => x.weight);
        this.lineChartData[1].data = o.map(x => x.diet);
        this.lineChartData[2].data = o.map(x => x.mental);
      }
    );
  }

  entrerMonitoring(): void {
    if (!this.monitoringForm.valid) {
      return;
    } else {
      this.backendService.createMonitoring(
        this.monitoringForm.controls.weight.value,
        this.monitoringForm.controls.etat.value,
        this.monitoringForm.controls.diet.value)
        .subscribe(res => {
          this.ngOnInit();
        });
    }
  }


}
