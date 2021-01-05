package com.northwind.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
@Data
public class Pole {
    @Id
    private Integer PID;
    private float distance;
}
