package spring.employees;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Emp {
    private int id;
    private String name;
    private float salary;
    private String designation;

    public Emp() {}
}
