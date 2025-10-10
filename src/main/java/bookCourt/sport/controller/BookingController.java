package bookCourt.sport.controller;

import bookCourt.sport.model.Booking;
import bookCourt.sport.model.Stadium;
import bookCourt.sport.response.ApiResponse;
import bookCourt.sport.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    private final BookingService bookingService;
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<Booking>>> getCourtsByStadiumDate(@RequestParam(name = "date") String date,
                                                                             @RequestParam(name = "hour") String hour,
                                                                             @RequestParam(name = "selectedStadium") Long stadiumId){

        ApiResponse<List<Booking>> response = bookingService.getCourtsByStadiumDate(date,hour,stadiumId);
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
