package xyz.thiennam.employeems.service;

import xyz.thiennam.employeems.entity.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDto> getAll();

    void add(EmployeeDto dto);
}
