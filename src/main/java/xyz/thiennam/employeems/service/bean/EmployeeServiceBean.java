package xyz.thiennam.employeems.service.bean;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.thiennam.employeems.entity.Employee;
import xyz.thiennam.employeems.entity.dto.EmployeeDto;
import xyz.thiennam.employeems.repository.EmployeeRepo;
import xyz.thiennam.employeems.service.EmployeeService;
import xyz.thiennam.employeems.util.DateTimeUtil;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceBean implements EmployeeService {
    private final EmployeeRepo employeeRepo;

    @Override
    public List<EmployeeDto> getAll() {
        return toDtos(employeeRepo.findAll());
    }

    private EmployeeDto toDto(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId().toString());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setEmail(employee.getEmail());
        dto.setPhoneNumber(employee.getPhoneNumber());
        dto.setDob(DateTimeUtil.toMillis(employee.getDob()));

        return dto;
    }

    private List<EmployeeDto> toDtos(List<Employee> list) {
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

    private Employee fromDto(EmployeeDto dto) {
        Employee employee = new Employee();
        employee.setId(dto.getId() == null || dto.getId().isEmpty() ? UUID.randomUUID() : UUID.fromString(dto.getId()));
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setPhoneNumber(dto.getPhoneNumber());
        employee.setEmail(dto.getEmail());
        employee.setDob(DateTimeUtil.toLocalDate(dto.getDob()));

        return employee;
    }

    @Override
    public void add(EmployeeDto dto) {
        employeeRepo.save(fromDto(dto));
    }
}
