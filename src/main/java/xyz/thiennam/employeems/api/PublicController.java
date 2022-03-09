package xyz.thiennam.employeems.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.thiennam.employeems.entity.dto.SimpleResponse;

@RestController
@RequestMapping("api/v1/public")
public class PublicController {
    @PostMapping()
    public ResponseEntity<SimpleResponse> postTest() {
        return new ResponseEntity<>(
                SimpleResponse.builder()
                        .httpCode(HttpStatus.CREATED.value())
                        .message("Guest - post successfully!")
                        .build(), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<SimpleResponse> getTest() {
        return new ResponseEntity<>(
                SimpleResponse.builder()
                        .httpCode(HttpStatus.OK.value())
                        .message("Guest - get successfully!")
                        .build(), HttpStatus.OK);
    }
}
