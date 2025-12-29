import { Component } from '@angular/core';
import { EmployeeService } from '../../Service/employee-service';
import { EmployeeModel } from '../../Model/employee-model';
import { AddressModel } from '../../Model/address-model';

@Component({
  selector: 'app-add-employee-component',
  standalone: false,
  templateUrl: './add-employee-component.html',
  styleUrl: './add-employee-component.css',
})
export class AddEmployeeComponent {

constructor(private empService : EmployeeService){}

employeeList: EmployeeModel[] = [];

  showForm: boolean = false;

  employee: EmployeeModel = {
    name: '',
    email: '',
    number: '',
    password: '',
    accountNo: '',
    image: '',
    addresses: []
  };

  newAddress: AddressModel = {
    houseNo: '',
    street: '',
    city: '',
    state: '',
    pincode: ''
  };

  toggleForm() {
    this.showForm = !this.showForm;
  }
  onImageSelect(event: any) {
    const file = event.target.files[0];
  
    if (file) {
      const reader = new FileReader();
      reader.onload = () => {
        this.employee.image = reader.result as string; 
      };
      reader.readAsDataURL(file);
    }
  }
  addAddress() {
    this.employee.addresses.push({ ...this.newAddress });
    this.newAddress = {
      houseNo: '',
      street: '',
      city: '',
      state: '',
      pincode: ''
    };
  }

  saveEmployee() {
    this.empService.addNewEmployee(this.employee).subscribe({
      next: (data) => {
        alert("Employee Added Successfully!");
        this.employeeList.push(data);
        this.resetForm();
        this.showForm = false;
      },
      error: () => alert("Failed to add employee")
    });
  }

  resetForm() {
    this.employee = {
      name: '',
      email: '',
      number: '',
      password: '',
      accountNo: '',
      image: '',
      addresses: []
    };
  }
}