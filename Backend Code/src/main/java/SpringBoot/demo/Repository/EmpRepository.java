package SpringBoot.demo.Repository;

import SpringBoot.demo.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmpRepository extends JpaRepository<Employee,Integer> {

    Optional<Employee> findByName(String name);
    Optional<Employee> findByEmail(String email);

    @Query("SELECT e FROM Employee e JOIN e.addresses a WHERE a.city = :city")
    List<Employee> findEmployeesByCity(@Param("city") String city);
}
