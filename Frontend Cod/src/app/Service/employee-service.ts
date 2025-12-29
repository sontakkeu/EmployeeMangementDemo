import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EmployeeModel } from '../Model/employee-model';

@Injectable({
  providedIn: 'root',
})
export class EmployeeService {
  private baseUrl = "http://localhost:8080/Employee";

  constructor(private http: HttpClient) {}

 
  //get all data
  getAllEmployee():Observable<EmployeeModel[]>{
      return this.http.get<EmployeeModel[]>(`${this.baseUrl}/allrecord`)
  }
 
 
//Add data 
addNewEmployee(employee: EmployeeModel): Observable<EmployeeModel> {
  return this.http.post<EmployeeModel>(`${this.baseUrl}/add`, employee);
}

  // Update Employee
updateEmployee(id: number, employee: EmployeeModel): Observable<EmployeeModel> {
  return this.http.put<EmployeeModel>(`${this.baseUrl}/update/${id}`, employee);
}
  // 
  // Delete Employee
deleteEmployee(id: number): Observable<void> {
  return this.http.delete<void>(`${this.baseUrl}/delete/${id}`);
}
}