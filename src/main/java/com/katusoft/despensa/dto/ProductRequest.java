package com.katusoft.despensa.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor 
@Getter 
@Setter 
public class ProductRequest {

    private String nombre;
    private Double precio;
    private Integer cantidad;
    private LocalDate fechaVencimiento;
    private LocalDate fechaIngreso;

}
