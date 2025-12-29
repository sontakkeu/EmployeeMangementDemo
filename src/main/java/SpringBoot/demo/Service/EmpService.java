package SpringBoot.demo.Service;

import SpringBoot.demo.Entity.Address;
import SpringBoot.demo.Entity.Employee;
import SpringBoot.demo.dto.EmployeeDto;

import java.util.List;

public interface  EmpService {
    Employee createEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getById(int id);
    Employee getByName(String name);
    Employee getByEmail(String email);
    Employee updateById(int id, Employee employee);
    Employee updateByEmail(String email, Employee employe);
    Employee updateByName(String name, Employee employee);
    Employee delete(int id);
    Employee deleteByName(String name );

    EmployeeDto fetchdto(int id);
    List<EmployeeDto> getall();

    Employee deleteAddress(int empId, int addressId);

    List<EmployeeDto> findEmployeesByCity(String city);

    Employee updateAddress(int empId, int addressId, Address updatedAddress);

    Employee updateAddressFields(int empId, int addressId, Address updatedAddress);

}
