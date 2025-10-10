package bookCourt.sport.DTO;

import bookCourt.sport.model.Stadium;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class CourtDTO {

    private String name;

    private String category;

    private String price;

    private Boolean interior;
}
