package com.app.auth.entity;

import com.app.auth.model.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;


@NamedStoredProcedureQuery(
        name = "save_user",
        procedureName = "save_user_details",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "pfirstName", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "plast_name", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "pemail", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "pusername", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "prole", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "pmobile", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "ppassword", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "ppin", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "pbirthdate", type = LocalDate.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "profile_pic_url", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "pReturnCode", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "pReturnMessage", type = String.class)
        }
)
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@ToString
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "mobile")
    private String mobile;
    @Column(name = "email")
    private String email;
    @Column(name = "pin")
    private String pin;
    @Column(name = "birthdate")
    private LocalDate birthDate;
    @Column(name = "profile_pic_url")
    private String displayPictureUrl;
    @Column(name = "created_date")
    private LocalDate createdOn;
    @Column(name = "created_by")
    private Integer createdBy;
    @Column(name = "modified_date")
    private LocalDate modifiedOn;
    @Column(name = "modified_by")
    private Integer modifiedBy;

    @OneToMany(mappedBy = "user")
    private List<TokenEntity> tokens;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
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