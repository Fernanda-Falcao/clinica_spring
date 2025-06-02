package com.br.spring_security.clinica.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@MappedSuperclass
@Data
@NoArgsConstructor
public abstract class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public boolean hasNotId() {
        return id == null;
    }

    public boolean hasId() {
        return id != null;
    }
}