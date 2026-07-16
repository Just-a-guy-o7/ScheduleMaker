package com.ScheduleMaker.ScheduleMaker.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * Extend this in every entity to get created_at / updated_at automatically,
 * populated by Spring Data JPA auditing (no manual timestamp code needed anywhere).
 *
 * Requires @EnableJpaAuditing on the main @SpringBootApplication class.
 * No @Setter here on purpose - these two fields should only ever be written
 * by the AuditingEntityListener, never by application code.
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {

    // Set once, automatically, the moment the row is first persisted. Never changes after.
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Refreshed automatically on every save() - useful for debugging "when did this last change".
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
