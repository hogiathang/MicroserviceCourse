package com.accounts.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter @Setter @ToString
public class BaseEntity {
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Column(insertable = false, nullable = false)
    private LocalDateTime updatedAt;

    @Column(updatable = false, nullable = true)
    private String createdBy;

    @Column(insertable = false, nullable = true)
    private String updatedBy;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.createdBy = "DefaultUser";
    }
}
