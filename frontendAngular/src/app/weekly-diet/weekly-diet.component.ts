import { Component, OnInit } from '@angular/core';
import {BackendService} from '../backend.service';

@Component({
  selector: 'app-weekly-diet',
  templateUrl: './weekly-diet.component.html',
  styleUrls: ['./weekly-diet.component.css']
})
export class WeeklyDietComponent implements OnInit {

  constructor(private backendService: BackendService) { }

  ngOnInit(): void {
  }


  getDietList(): void {
    this.backendService.getGroups().subscribe(groups => {
      this.dataSource = new MatTableDataSource(groups);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

}
