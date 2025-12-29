package SpringBoot.demo.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Table(name="Employee")
@Getter
@Setter
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY
    )
    private int id;
    private String name;
    private String email;
    private String number;
    private String password;
    private String accountNo;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String image;


    private String role;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Address> addresses = new ArrayList<>();

}
