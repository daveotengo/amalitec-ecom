package com.amalitec.amalitececom.blueprint;


import jakarta.persistence.*;

import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;
import java.util.UUID;


@Data
@MappedSuperclass
public abstract class BaseInput extends AuditingInput implements Serializable {

    private static final long serialVersionUID = 4546097167586314016L;


    private UUID id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        BaseInput that = (BaseInput) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .toHashCode();
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}

