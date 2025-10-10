package bookCourt.sport.service;

import bookCourt.sport.model.Stadium;
import bookCourt.sport.repo.BookingRepo;
import bookCourt.sport.model.Booking;
import bookCourt.sport.repo.StadiumRepo;
import bookCourt.sport.response.ApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookingService {

    private final BookingRepo bookingRepository;
    private final StadiumRepo stadiumRepository;

    public BookingService(BookingRepo bookingRepository,StadiumRepo stadiumRepository){
        this.bookingRepository = bookingRepository;
        this.stadiumRepository = stadiumRepository;
    }
    public ApiResponse<List<Booking>> getCourtsByStadiumDate(String date, String hour, Long stadiumId) {
        if(stadiumId <= 0){
           Optional<Stadium> stadium = stadiumRepository.findById(stadiumId);
           if(stadium.isEmpty()) return ApiResponse.error("there is no such stadium with this id");
        }
         return ApiResponse.success(bookingRepository.findReservedCourts(LocalDate.parse(date),hour,stadiumId));
    }
}
