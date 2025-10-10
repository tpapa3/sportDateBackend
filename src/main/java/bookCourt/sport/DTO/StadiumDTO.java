package bookCourt.sport.DTO;

import bookCourt.sport.model.Court;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class StadiumDTO{

    private String name;

    private String town;

    private String address;

    private String region;

    private String latitude;

    private String longitude;

    private String sport;

    private String reservationByTime;

    private String mondayStart;

    private String mondayEnd;

    private String tuesdayStart;

    private String tuesdayEnd;

    private String wednesdayStart;

    private String wednesdayEnd;

    private String thursdayStart;

    private String thursdayEnd;

    private String fridayStart;

    private String fridayEnd;

    private String saturdayStart;

    private String saturdayEnd;

    private String sundayStart;
    private String sundayEnd;


    private List<CourtDTO> courts = new ArrayList<>();
}