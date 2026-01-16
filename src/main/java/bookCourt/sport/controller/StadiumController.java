package bookCourt.sport.controller;



import java.util.List;

import bookCourt.sport.DTO.StadiumDTO;
import bookCourt.sport.model.Stadium;
import bookCourt.sport.response.ApiResponse;
import bookCourt.sport.service.StadiumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
@RestController
@RequestMapping("/backend/stadiums")
public class StadiumController {

    private final StadiumService stadiumService;

    public StadiumController(StadiumService stadiumService) {
        this.stadiumService = stadiumService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Stadium>>> getAllStadiums() {
        ApiResponse<List<Stadium>> response = stadiumService.getAllStadiums();
        HttpStatus status;
        if (response.getStatus().equals("success")) {
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return ResponseEntity.status(status).body(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Stadium>> getStadiumById(@PathVariable Long id) {
        ApiResponse<Stadium> response = stadiumService.getStadiumById(id);
        HttpStatus status;
        if (response.getStatus().equals("success")) {
            status = HttpStatus.OK;
        } else if (response.getMessage().startsWith("Not found")) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Stadium>> createStadium(@RequestBody @Valid StadiumDTO stadiumDTO) {
        System.out.println(stadiumDTO);
        return ResponseEntity.status(HttpStatus.OK).body(stadiumService.createStadium(stadiumDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Stadium>> updateStadium(@PathVariable Long id, @RequestBody Stadium stadium) {
        ApiResponse<Stadium> response = stadiumService.updateStadium(id, stadium);
        HttpStatus status;
        if (response.getStatus().equals("success")) {
            status = HttpStatus.OK;
        } else if (response.getMessage().startsWith("Not found")) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteStadium(@PathVariable Long id) {
        ApiResponse<String> response = stadiumService.deleteStadium(id);
        HttpStatus status;
        if (response.getStatus().equals("success")) {
            status = HttpStatus.OK;
        }else{
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(response);


    }

    @GetMapping("/{find}/{bySport}")
    public ResponseEntity<ApiResponse<List<Stadium>>> getStadiumBySport(@RequestParam(name="sport") String sport) {
        ApiResponse<List<Stadium>> response = stadiumService.getStadiumBySport(sport);
        HttpStatus status;
        if (response.getStatus().equals("success")) {
            status = HttpStatus.OK;
        } else if (response.getMessage().startsWith("Not found")) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(response);
    }
}
