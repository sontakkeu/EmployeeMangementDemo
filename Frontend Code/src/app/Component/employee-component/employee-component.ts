import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { EmployeeService } from '../../Service/employee-service';
import { EmployeeModel } from '../../Model/employee-model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-employee-component',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './employee-component.html',
  styleUrls: ['./employee-component.css'],
})
export class EmployeeComponent implements OnInit {
  employeeList: EmployeeModel[] = [];

  //constructor(private empService: EmployeeService) {}
  constructor(private empService: EmployeeService, private cd: ChangeDetectorRef){}
  ngOnInit() {
    console.log("ngOnInit called");
    this.loadEmployees();
  }

  loadEmployees() {
    console.log("Loading employees...");
    this.empService.getAllEmployee().subscribe({
      next: (data) => {
        console.log("Data arrived:", data);
        this.employeeList = data;
        this.cd.detectChanges();
      },
      error: (err) => console.log("Error:", err),
    });
  }
  
// @Component({
//   selector: 'app-employee-component',
//   templateUrl: './employee-component.html',
//   styleUrls: ['./employee-component.css'],
//   standalone: true,  
//   imports: [CommonModule],
  
// })

// export class EmployeeComponent implements OnInit{

//   employeeList: EmployeeModel[] = [];

//   constructor(private empService : EmployeeService){}

//   ngOnInit() {
//     console.log("ngOnInit called");
//     this.loadEmployees();
//   }
  
//   // loadEmployees(){
//   //   this.empService.getAllEmployee().subscribe({
//   //     next:(data)=>{
//   //       this.employeeList=data;
//   //     },
//   //     error:(err)=>{
//   //       console.log('Error while fetching data', err);
//   //     }
//   //   });
//   // }
//   loadEmployees() {
//     console.log("Loading employees...");
  
//     this.empService.getAllEmployee().subscribe({
//       next: (data) => {
//         console.log("Data arrived:", data);
//         this.employeeList = data;
//       },
//       error: (err) => console.log("Error:", err)
//     });
//   }
  
  
  
// }
}

