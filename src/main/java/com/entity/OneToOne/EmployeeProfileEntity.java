package com.entity.OneToOne;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "employee_profile")
@Data
public class EmployeeProfileEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer employeeProfileid;
  private String city;

}
