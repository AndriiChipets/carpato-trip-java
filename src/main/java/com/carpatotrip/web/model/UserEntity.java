package com.carpatotrip.web.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder (setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", schema = "carpato_trip_schema")
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "user_email", length = 320, unique = true)
    private String email;
    
    @Column(name = "user_password")
    private String password;
    
    @Builder.Default
    @ToString.Exclude
    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private Set<TravelClub> travelClubs = new HashSet<>();
    
    @Builder.Default
    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_roles", schema = "carpato_trip_schema",
        joinColumns = { @JoinColumn (name = "user_id", referencedColumnName = "id") }, 
        inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") }
    )
    @EqualsAndHashCode.Exclude
    private Set<Role> roles = new HashSet<>();
    
}
