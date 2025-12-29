import { ChangeDetectorRef, Component } from '@angular/core';
import { EmployeeModel } from '../../Model/employee-model';
import { EmployeeService } from '../../Service/employee-service';


@Component({
  selector: 'app-update-component',
  standalone: false,
  templateUrl: './update-component.html',
  styleUrl: './update-component.css',
})
export class UpdateComponent {

  employeeList: EmployeeModel[] = [];
  selectedEmployee: EmployeeModel | null = null;

  constructor(private empService: EmployeeService, private cd: ChangeDetectorRef){}

  ngOnInit() {
    this.loadEmployees();
  }

  loadEmployees() {
    this.empService.getAllEmployee().subscribe({
      next: (data) => {
        this.employeeList = data;
        this.cd.detectChanges();
      },
      error: (err) => {
        console.error('Error loading employees', err);
      }
    });
  }

  openEditForm(emp: EmployeeModel) {
    // Deep clone to avoid modifying original object
    this.selectedEmployee = JSON.parse(JSON.stringify(emp));

    if (this.selectedEmployee) {
      if (!this.selectedEmployee.addresses) this.selectedEmployee.addresses = [];

      // Ensure at least 2 address objects
      if (!this.selectedEmployee.addresses[0]) this.selectedEmployee.addresses[0] = { houseNo: '', street: '', city: '', state: '', pincode: '' };
      if (!this.selectedEmployee.addresses[1]) this.selectedEmployee.addresses[1] = { houseNo: '', street: '', city: '', state: '', pincode: '' };
    }

    // Scroll to update form smoothly
    setTimeout(() => {
      const element = document.getElementById('updateForm');
      if (element) element.scrollIntoView({ behavior: 'smooth', block: 'start' });
    }, 0);
  }

  updateEmployee(id: number, employee: EmployeeModel) {
    this.empService.updateEmployee(id, employee).subscribe({
      next: (data) => {
        console.log('Employee updated successfully', data);
        this.selectedEmployee = null;
        this.loadEmployees();
      },
      error: (err) => console.error('Error while updating employee', err)
    });
  }
  onImageSelect(event: any) {
    const file = event.target.files[0];
    if (!file) return;
  
    const reader = new FileReader();
    reader.onload = () => {
      if (this.selectedEmployee) {
        this.selectedEmployee.image = reader.result as string;
      }
    };
    reader.readAsDataURL(file);
  }
  
  deleteEmployee(id: number) {
    this.empService.deleteEmployee(id).subscribe({
      next: () => {
        console.log('Employee deleted successfully');
        this.loadEmployees();
      },
      error: (err) => console.error('Error while deleting employee', err)
    });
  }

  cancelEdit() {
    this.selectedEmployee = null;
  }
}