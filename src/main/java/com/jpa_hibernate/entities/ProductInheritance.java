package com.jpa_hibernate.entities;

import com.jpa_hibernate.entities.keys.ProductKey;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class ProductInheritance {

    @Id
    protected long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
