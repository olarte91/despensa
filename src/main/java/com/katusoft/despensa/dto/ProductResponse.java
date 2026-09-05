package com.katusoft.despensa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor 
@Getter 
@Setter 
public class ProductResponse {

    private Long id;
    private String nombre;
    private Double precio;
    private Integer cantidad;
    private String fechaVencimiento;
    private String fechaIngreso;

}
