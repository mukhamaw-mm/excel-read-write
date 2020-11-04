package com.mukham.laptopfilereadandwrite.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

//import javax.persistence.Entity;

@Data
@ToString
@Entity
@Table(name="LAPTOP")
@AllArgsConstructor
@NoArgsConstructor
public class Laptop {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     @JsonIgnore
    @Column(name="ID")
    Long id;
    @Column(name="LAPTOP_NAME")
    String laptop_Name;
    @Column(name="MOUSE_NAME")
    String mouse_Name;
    @Column(name="LAPTOP_PRICE")
    Double laptop_Price;
    @Column(name="MOUSE_PRICE")
    Double mouse_Price;
    @Column(name="RAM")
    String ram;
    @Column(name="PROCESSOR")
    String processor;
    @Column(name="STORAGE")
    String storage;
    @Column(name="TOTAL")
    Double total;
    @JsonIgnore
    @Column(name="IS_DELETED")
    Boolean de;

}
