package SpringBoot.demo.Controller;

import SpringBoot.demo.Entity.Address;
import SpringBoot.demo.Entity.Employee;
import SpringBoot.demo.Service.EmpService;
import SpringBoot.demo.dto.EmployeeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Slf4j

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/Employee")
public class EmpController {

    @Autowired
    private EmpService empService;

    //  Add new Employee
//    @PostMapping("/add")
//    public ResponseEntity<Employee> addEmp(@RequestBody Employee employee) {
//        log.info("Adding new Employee: {}", employee);
//        Employee savedEmployee = empService.createEmployee(employee);
//        log.info("Employee created with ID: {}", savedEmployee.getId());
//        return ResponseEntity.ok(savedEmployee);
//    }

@PostMapping("/add")
public ResponseEntity<Employee> addEmp(@RequestBody Employee employee) {
    log.info("Adding new Employee: {}", employee);
    if (employee.getAddresses() != null) {
        employee.getAddresses().forEach(address -> address.setEmployee(employee));
    }
    Employee savedEmployee = empService.createEmployee(employee);
    log.info("Employee created with ID: {}", savedEmployee.getId());
    return ResponseEntity.ok(savedEmployee);
}
    // Fetch all Employee
    @GetMapping("allrecord")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        log.info("Fetching all employees");
        List<Employee> employees = empService.getAllEmployees();
        log.info("Total employees fetched: {}", employees.size());
        return ResponseEntity.ok(employees);
    }

    //Fetch by using id
   @GetMapping("fetch/{id}")
    public ResponseEntity<Employee>finfId(@PathVariable int id){
        log.info("Fetech employee with id : {} ",id);
        Employee emp = empService.getById(id);
        return ResponseEntity.ok(emp);

    }

    //Fetch by using name
    @GetMapping("fetchByName/{name}")
    public ResponseEntity<Employee> findByName(@PathVariable String name) {
        log.info("Fetch employee with name: {}", name);
        Employee emp = empService.getByName(name);
        return ResponseEntity.ok(emp);
    }

    //Fetch by using email
    @GetMapping("fetchByEmail/{email}")
    public ResponseEntity<Employee> findByEmail(@PathVariable String email) {
        log.info("Fetch employee with Email: {}", email);
        Employee emp = empService.getByEmail(email);
        return ResponseEntity.ok(emp);
    }

    //update by usnig id
    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable int id,
            @RequestBody Employee employee) {
        log.info("Updating employee with id : {}", id);
        Employee updated = empService.updateById(id, employee);
        return ResponseEntity.ok(updated);
    }

    //Update by using email
    @PutMapping("update/email/{email}")
    public ResponseEntity<Employee>updateByEmail(@PathVariable String email , @RequestBody Employee updateemploye){
        log.info("Employee fetch with email :  {} ",email);
        Employee emp1= empService.updateByEmail(email,updateemploye);
        return  ResponseEntity.ok(emp1);
    }

    //Update by using name
    @PutMapping("/update/name/{name}")
    public ResponseEntity<Employee>updateByName(@PathVariable String name , @RequestBody Employee updateemployee){
        log.info("Employee data fetch with name : {} ",name);
        Employee employee=empService.updateByName(name,updateemployee);
        return ResponseEntity.ok(employee);
    }

    //Delete record useing id
   @DeleteMapping("delete/{id}")
    public  ResponseEntity<Employee>delete(@PathVariable int id){
        log.info("fetch employee with id : {} ",id);
        Employee employee=empService.delete(id);
        return ResponseEntity.ok(employee);
  }

    //Delete record useing name
//    @DeleteMapping("delete/name/{name}")
//    public  ResponseEntity<Employee>delete(@PathVariable String name){
//        log.info("fetch employee with name : {} ",name);
//        Employee employee=empService.delete(name);
//        return ResponseEntity.ok(employee);
//    }


    @GetMapping("/dto/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeDto(@PathVariable int id) {
        log.info("Fetching Employee DTO for ID: {}", id);
        EmployeeDto employeeDto = empService.fetchdto(id);
        return ResponseEntity.ok(employeeDto);
    }

    @GetMapping("dto/all")
    public ResponseEntity<List<EmployeeDto>> getAll() {
        log.info("Fetching all employees");
        List<EmployeeDto> employees = empService.getall();
        log.info("Total employees fetched: {}", employees.size());
        return ResponseEntity.ok(employees);
    }

    @DeleteMapping("delete/{empId}/address/{addressId}")
    public ResponseEntity<Employee> deleteAddress(
            @PathVariable int empId,
            @PathVariable int addressId) {
        log.info("Deleting address {} for employee {}", addressId, empId);
        Employee employee = empService.deleteAddress(empId, addressId);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<EmployeeDto>> getEmployeesByCity(@PathVariable String city) {
        log.info("Checking employees from city: {}", city);
        List<EmployeeDto> employees = empService.findEmployeesByCity(city);
        return ResponseEntity.ok(employees);
    }

    @PutMapping("/update/{empId}/address/{addressId}")
    public ResponseEntity<Employee> updateAddress(
            @PathVariable int empId,
            @PathVariable int addressId,
            @RequestBody Address updatedAddress) {
        log.info("Updating address {} for employee {}", addressId, empId);
        Employee employee = empService.updateAddress(empId, addressId, updatedAddress);
        return ResponseEntity.ok(employee);
    }

    @PatchMapping("/update/{empId}/address/{addressId}")
    public ResponseEntity<Employee> updateAddressFields(
            @PathVariable int empId,
            @PathVariable int addressId,
            @RequestBody Address updatedAddress) {
        log.info("Partially updating address {} for employee {}", addressId, empId);
        Employee employee = empService.updateAddressFields(empId, addressId, updatedAddress);
        return ResponseEntity.ok(employee);
    }

}
