import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Employee } from '../models/employee.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class EmployeeService {
  baseUrl = 'http://localhost:8080/api/employee';
  baseUrlImage='http://localhost:8080/api/image';

  constructor(private http: HttpClient) {}

  getEmployees():Observable<Employee[]> {
    return this.http.get<Employee[]>(this.baseUrl+'/all');
  }
  getSingle(id:Number):Observable<Employee> {
    return this.http.get<Employee>(this.baseUrl+'/singleEmployee/'+id);
  }
  search(searchkey:String):Observable<Employee[]> {
    return this.http.get<Employee[]>(this.baseUrl+'/searchEmployee/'+searchkey);
  }

 

  postEmployee(formData: FormData):Observable<Employee> {
    return this.http.post<Employee>(this.baseUrl+'/newEmployee', formData);
  }

  deleteEmployee(id: Number) {
    return this.http.delete(this.baseUrl + '/deleteEmployee/' + id);
  }

  editEmployee(employee:Employee,id:number):Observable<Employee> {
    return this.http.put<Employee>(this.baseUrl+'/updateEmployee/'+id, employee);
  }
}