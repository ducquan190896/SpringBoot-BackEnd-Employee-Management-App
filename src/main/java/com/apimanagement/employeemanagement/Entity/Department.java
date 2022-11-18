package com.apimanagement.employeemanagement.Entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name = "Department")
@Table(name = "department")
public class Department {
    @Id
    @SequenceGenerator(
        name = "department_sequence",
        sequenceName = "department_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator =  "department_sequence"
    )
    // @Column(name = "id", updatable = false)
    private Long id;

    @NotBlank(message = "name of department cannot be blank")
    @NonNull
    @Column(name = "name", unique = true, nullable = false)
    private String name;

   
    // @JsonManagedReference
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees = new ArrayList<>() ;

    @Override
    public String toString() {
        return "Department [id=" + id + ", name=" + name + ", employees=" + employees + "]";
    }
    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }
    public void removeEmployee(Employee employee) {
        this.employees.stream().filter(em -> em.getId() != employee.getId()).collect(Collectors.toList());
    }

}
