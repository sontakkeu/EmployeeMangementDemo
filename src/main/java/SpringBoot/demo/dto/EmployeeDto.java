package SpringBoot.demo.dto;


import SpringBoot.demo.Entity.Address;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EmployeeDto {
    private int id;
    private String name;
    private String email;
    private String number;
    private String image;
    private List<Address> addresses;
}
