package com.apimanagement.employeemanagement.Entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.*;



@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Table(name = "employee")
@Entity(name = "Employee")
public class Employee {

    @Id
    @SequenceGenerator(
        name = "role_sequence",
        sequenceName = "role_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "role_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;

    @NonNull
    @NotBlank(message = "lastname cannot be blank")
    @Column(name = "lastname", nullable = false)
    private String lastname;

    @NonNull
    @NotBlank(message = "firstname cannot be blank")
    @Column(name = "firstname", nullable = false)
    private String firstname;

    @NonNull
    @Email(message = "email must be in form email")
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
}
