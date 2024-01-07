package com.devansh.splitKro.model.user;

import com.devansh.splitKro.model.group.SplitKroGroup;
import com.devansh.splitKro.model.group.UserUserRelationship;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    private String emailId;
    @Column(unique = true, nullable = false)
    private String mobileNumber;
    private String name;
    private String password;

    @ManyToMany(mappedBy = "userSet")
    @JsonIgnore
    private Set<SplitKroGroup> splitKroGroups = new HashSet<>();

    @OneToMany(mappedBy = "user1", cascade = CascadeType.ALL)
    private List<UserUserRelationship> relationshipsAsUser1 = new ArrayList<>();

    @OneToMany(mappedBy = "user2", cascade = CascadeType.ALL)
    private List<UserUserRelationship> relationshipsAsUser2 = new ArrayList<>();


    public User(UserRequestDto userRequestDto) {
        this.emailId = userRequestDto.getEmailId();
        this.mobileNumber = userRequestDto.getMobileNumber();
        this.name = userRequestDto.getName();
        this.password = userRequestDto.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return getEmailId();
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
