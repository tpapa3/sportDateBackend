package bookCourt.sport.repo;

import bookCourt.sport.model.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface StadiumRepo extends JpaRepository<Stadium, Long> {
    List<Stadium> findBySport(String sport);

}
