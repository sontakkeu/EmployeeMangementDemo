package SpringBoot.demo.Service;

import SpringBoot.demo.Entity.Employee;
import SpringBoot.demo.Repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpDetailsSer implements UserDetailsService {
    @Autowired
    private EmpRepository empRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee emp = empRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new org.springframework.security.core.userdetails.User(
                emp.getEmail(),
                emp.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + emp.getRole()))
        );
    }
}
