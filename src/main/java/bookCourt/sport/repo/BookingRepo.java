package bookCourt.sport.repo;

import bookCourt.sport.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long> {
    @Query("SELECT b FROM Booking b WHERE b.date = :date AND b.hour = :hour AND b.stadium.id = :stadiumId")
    List<Booking> findReservedCourts(
            @Param("date") LocalDate date,
            @Param("hour") String hour,
            @Param("stadiumId") Long stadiumId
    );
}
