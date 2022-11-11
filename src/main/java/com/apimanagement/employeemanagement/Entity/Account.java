package com.apimanagement.employeemanagement.Entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "account")
@Entity(name = "Account")
public class Account {
    
    
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

    
 

    @NotBlank(message = "username cannot be blank")
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotBlank(message = "password cannot be blank")
    @Column(name = "password", nullable = false)
    private String password;    

    @ManyToMany(
        cascade = CascadeType.PERSIST,
        fetch = FetchType.EAGER
    )
   private Set<Role> roles = new HashSet<>();

   public Account( String username, String password) {
    this.username = username;
    this.password =  new BCryptPasswordEncoder().encode(password);
}

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.username + " is the user of this account";
    }
}
