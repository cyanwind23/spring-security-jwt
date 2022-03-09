package xyz.thiennam.employeems.service.bean;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.thiennam.employeems.entity.dto.EmployeeDto;
import xyz.thiennam.employeems.service.EmployeeService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServiceBeanTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    void addEmployeeTest() {
        EmployeeDto dto = new EmployeeDto(null, "1", "Employee", 1234L, null, null);
        employeeService.add(dto);
    }
}