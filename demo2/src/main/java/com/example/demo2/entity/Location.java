package com.example.demo2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "locations")
@Getter
@Setter
public class Location {

    @Id
    @Column(name = "location_id")
    private Integer id;

    @Column(name = "city", length = 30)
    private String city;

    @Column(name = "country_id", length = 2)
    private String countryId;  // 🔥 Esta es la clave para que tu query funcione

    public Location() {
    }
}
