package com.apimanagement.employeemanagement.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "role")
@Entity(name = "Role")
public class Role {
    

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
    @NotNull(message = "role cannot be blank")
    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    private RoleType name;

    @Override
    public String toString() {
       
        return this.name.getRoleType();
    }

}
