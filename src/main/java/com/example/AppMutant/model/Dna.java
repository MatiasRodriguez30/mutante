package com.example.AppMutant.model;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dna {
    private String secuencia;
    private boolean isMutant;

}
