package com.skillswapcomunity.skillswapcomunity.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String description;
    private String achievements;

    @ManyToOne
    @JoinColumn(name="skill_id")
    private Skill skill;
    private BigDecimal salary;
    private BigDecimal rating;
    private BigDecimal experience;

    @ManyToOne
    @JoinColumn(name="company_id")
    @JsonBackReference
    private Company company;

    @OneToMany(mappedBy = "sender")
    @JsonManagedReference
    private List<Requests> sentRequests;

    @OneToMany(mappedBy = "recipient")
    @JsonManagedReference
    private List<Requests> receivedRequests;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
