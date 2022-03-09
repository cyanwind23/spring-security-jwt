package xyz.thiennam.employeems.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.thiennam.employeems.entity.dto.EmployeeDto;
import xyz.thiennam.employeems.entity.dto.SimpleResponse;
import xyz.thiennam.employeems.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("api/v1/employees")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/test")
    public ResponseEntity<SimpleResponse> postTest() {
        return new ResponseEntity<>(
                SimpleResponse.builder()
                        .httpCode(HttpStatus.CREATED.value())
                        .message("User - post successfully!")
                        .build(), HttpStatus.CREATED);
    }

    @GetMapping("/test")
    public ResponseEntity<SimpleResponse> getTest() {
        return new ResponseEntity<>(
                SimpleResponse.builder()
                        .httpCode(HttpStatus.OK.value())
                        .message("User - get successfully!")
                        .build(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAll() {
        List<EmployeeDto> list = employeeService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SimpleResponse> addEmployee(@RequestBody EmployeeDto employeeDto) {
        employeeService.add(employeeDto);
        return new ResponseEntity<>(
                SimpleResponse.builder()
                        .httpCode(HttpStatus.CREATED.value())
                        .message("Employee created!")
                        .build(), HttpStatus.CREATED);
    }

}
