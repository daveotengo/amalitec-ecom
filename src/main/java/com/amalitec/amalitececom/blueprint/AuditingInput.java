package com.amalitec.amalitececom.blueprint;



import lombok.Getter;
import lombok.Setter;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class AuditingInput {


    private String createdBy;


    private LocalDateTime createdAt;


    private String updatedBy;


    private LocalDateTime updatedAt;



}
