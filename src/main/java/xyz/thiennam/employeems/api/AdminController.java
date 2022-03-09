package xyz.thiennam.employeems.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.thiennam.employeems.entity.dto.SimpleResponse;


@RestController
@RequestMapping("api/v1/admin")
@AllArgsConstructor
public class AdminController {
    @PostMapping("/test")
    public ResponseEntity<SimpleResponse> postTest() {
        return new ResponseEntity<>(
                SimpleResponse.builder()
                        .httpCode(HttpStatus.CREATED.value())
                        .message("Admin - post successfully!")
                        .build(), HttpStatus.CREATED);
    }

    @GetMapping("/test")
    public ResponseEntity<SimpleResponse> getTest() {
        return new ResponseEntity<>(
                SimpleResponse.builder()
                        .httpCode(HttpStatus.OK.value())
                        .message("Admin - get successfully!")
                        .build(), HttpStatus.OK);
    }
}
