package com.ScheduleMaker.ScheduleMaker.Entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends Auditable {

    // Login identifier and primary key for the user.
    @Id
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    // Hashed password (never store plaintext - hash it in the service layer before saving).
    @Column(nullable = false)
    private String password;

    // Display name shown in the UI.
    @Column(nullable = false, length = 100)
    private String name;

    // Role assigned to the user for authorization.
    @Builder.Default
    @Column(nullable = false, length = 20)
    private String role = "USER";

    // Every schedule this user owns. A user can have many independent schedules
    // (e.g. "Semester Finals", "GATE Prep") running in parallel.
    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Schedule> schedules = new ArrayList<>();
}
