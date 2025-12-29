package SpringBoot.demo.Service.Impl;

import SpringBoot.demo.Entity.Address;
import SpringBoot.demo.Entity.Employee;
import SpringBoot.demo.Repository.EmpRepository;
import SpringBoot.demo.Service.EmpService;
import SpringBoot.demo.dto.EmployeeDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmpRepository emprepository;

    @Override
    public Employee createEmployee(Employee employee) {
        if (employee.getRole() == null || employee.getRole().trim().isEmpty()) {
            employee.setRole("USER");
        }
        if (employee.getPassword() != null && !employee.getPassword().isBlank()) {
            employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        }
//        if (employee.getAddresses() != null) {
//            employee.getAddresses().forEach(address -> {
//                address.setEmployee(employee);
//            });
//        }
        log.info("Creating new employee: {}", employee.getName());
        Employee savedEmployee = emprepository.save(employee);
        log.info("Employee created successfully with ID: {}", savedEmployee.getId());
        return savedEmployee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        log.info("fetching all employee");
        return emprepository.findAll();
    }

    @Override
    public Employee getById(int id) {
        log.info("Retrieving employee using id: {}", id);
        Employee employee = emprepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Data not found for id: {}", id);
                    return new RuntimeException("Employee not found with id: " + id);
                });
        log.info("Employee found: {}", employee.getName());
        return employee;
    }

    @Override
    public Employee getByName(String name) {
        log.info("Retrieving employee using name: {}", name);
        Employee employee = emprepository.findByName(name)
                .orElseThrow(() -> {
                    log.error("Data not found for name: {}", name);
                    return new RuntimeException("Employee not found with name: " + name);
                });
        log.info("Employee found: {}", employee.getName());
        return employee;
    }

    @Override
    public Employee getByEmail(String email) {
        log.info("Retrieving employee using email: {}", email);
        return emprepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.error("Data not found for email: {}", email);
                    return new RuntimeException("Employee not found with email: " + email);
                });
    }

    @Override
    public Employee updateById(int id, Employee employee) {

        log.info("Update record with id : {}", id);

        Employee existing = emprepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Employee not found with id " + id));

        existing.setName(employee.getName());
        existing.setEmail(employee.getEmail());
        existing.setNumber(employee.getNumber());
        existing.setAccountNo(employee.getAccountNo());
        existing.setRole(employee.getRole());

        if (employee.getImage() != null) {
            existing.setImage(employee.getImage());
        }
        existing.getAddresses().clear();
        if (employee.getAddresses() != null) {
            for (Address addr : employee.getAddresses()) {
                addr.setEmployee(existing);
                existing.getAddresses().add(addr);
            }
        }

        Employee saved = emprepository.save(existing);
        log.info("Employee updated successfully with id {}", id);

        return saved;
    }


    @Override
    public Employee updateByEmail(String email, Employee employee) {
        log.info("update record with email {} ", email);
        Employee updateemploye = emprepository.findByEmail(email).orElseThrow(() -> {
            log.error("Employee not found with email ", email);
            return new EntityNotFoundException("Employee not found with email " + email);
        });
        updateemploye.setName(employee.getName());
        updateemploye.setEmail(employee.getEmail());
        updateemploye.setNumber(employee.getNumber());
        Employee savedemp = emprepository.save(updateemploye);
        log.info("Employeee updated sucess with email {} ", email);
        return savedemp;
    }

    //Update by name using
    @Override
    public Employee updateByName(String name, Employee employee) {
        log.info("update record with name {} ", name);
        Employee updateemploye = emprepository.findByEmail(name).orElseThrow(() -> {
            log.error("Employee not found with name ", name);
            return new EntityNotFoundException("Employee not found with name " + name);
        });
        updateemploye.setName(employee.getName());
        updateemploye.setEmail(employee.getEmail());
        updateemploye.setNumber(employee.getNumber());
        Employee savedemp = emprepository.save(updateemploye);
        log.info("Employeee updated sucess with name{} ", name);
        return savedemp;

    }

    @Override
    public Employee delete(int id) {
        log.info("Delete record with id {}", id);
        Employee deleteEmp = emprepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Employee not found with id {}", id);
                    return new RuntimeException("Employee not found with id: " + id);
                });
        emprepository.delete(deleteEmp);
        log.info("Employee deleted successfully with id {}", id);
        return deleteEmp;
    }

    @Override
    public Employee deleteByName(String name) {
        log.info("Delete record with name {} : ", name);
        Employee deleteemp = emprepository.findByName(name).orElseThrow(() -> {
            log.error("Employee not found with name {}", name);
            return new RuntimeException("Employee not found with name : " + name);
        });
        emprepository.delete(deleteemp);
        log.info("Employee deleted successfully with name {}", name);
        return deleteemp;
    }



    @Override
    public EmployeeDto fetchdto(int id) {
        log.info("Retrieving employee using id: {}", id);

        Employee employee = emprepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Data not found for id: {}", id);
                    return new RuntimeException("Employee not found with id: " + id);
                });

        log.info("Employee found: {}", employee.getName());

        return EmployeeDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .email(employee.getEmail())
                .number(employee.getNumber())
                .addresses(employee.getAddresses())
                .build();
    }

    @Override
    public List<EmployeeDto> getall() {
        log.info("Fetching all employees");
        List<Employee> employees = emprepository.findAll();
        return employees.stream().map(emp -> EmployeeDto.builder()
                .id(emp.getId())
                .name(emp.getName())
                .email(emp.getEmail())
                .number(emp.getNumber())
                .addresses(emp.getAddresses())
                .build()
        ).toList();
    }

    @Override
    public Employee deleteAddress(int empId, int addressId) {
        log.info("Deleting address with id {} for employee {}", addressId, empId);
        Employee employee = emprepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + empId));
        Address addressToDelete = employee.getAddresses()
                .stream()
                .filter(address -> address.getAddsId() == addressId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + addressId));
        employee.getAddresses().remove(addressToDelete);
        Employee updatedEmployee = emprepository.save(employee);

        log.info("Address id {} deleted successfully for employee {}", addressId, empId);
        return updatedEmployee;
    }

    @Override
    public List<EmployeeDto> findEmployeesByCity(String city) {
        log.info("Fetching employees living in city: {}", city);
        List<Employee> employees = emprepository.findEmployeesByCity(city);

        if (employees.isEmpty()) {
            log.warn("No employees found in city: {}", city);
            throw new RuntimeException("No employees found in city: " + city);
        }

        return employees.stream().map(emp -> EmployeeDto.builder()
                .id(emp.getId())
                .name(emp.getName())
                .email(emp.getEmail())
                .number(emp.getNumber())
                .addresses(emp.getAddresses())
                .build()
        ).toList();
    }
    @Override
    public Employee updateAddress(int empId, int addressId, Address updatedAddress) {
        log.info("Updating address with id {} for employee {}", addressId, empId);


        Employee employee = emprepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + empId));


        Address existingAddress = employee.getAddresses()
                .stream()
                .filter(address -> address.getAddsId() == addressId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + addressId));


        existingAddress.setHouseNo(updatedAddress.getHouseNo());
        existingAddress.setStreet(updatedAddress.getStreet());
        existingAddress.setCity(updatedAddress.getCity());
        existingAddress.setState(updatedAddress.getState());
        existingAddress.setPincode(updatedAddress.getPincode());

        // 4️⃣ Save the employee (cascade = ALL will automatically update address)
        Employee savedEmployee = emprepository.save(employee);

        log.info("Address {} updated successfully for employee {}", addressId, empId);
        return savedEmployee;
    }

    @Override
    public Employee updateAddressFields(int empId, int addressId, Address updatedAddress) {
        log.info("Updating specific address fields for address {} of employee {}", addressId, empId);

        Employee employee = emprepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + empId));

        Address existingAddress = employee.getAddresses()
                .stream()
                .filter(address -> address.getAddsId() == addressId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + addressId));

        if (updatedAddress.getHouseNo() != null) {
            existingAddress.setHouseNo(updatedAddress.getHouseNo());
        }
        if (updatedAddress.getStreet() != null) {
            existingAddress.setStreet(updatedAddress.getStreet());
        }
        if (updatedAddress.getPincode() != null) {
            existingAddress.setPincode(updatedAddress.getPincode());
        }

        // 4️⃣ Save employee (CascadeType.ALL will update the address automatically)
        Employee updatedEmployee = emprepository.save(employee);

        log.info("Updated houseNo/street/pincode for address {} of employee {}", addressId, empId);
        return updatedEmployee;
    }

}
