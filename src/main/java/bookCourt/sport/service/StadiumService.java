package bookCourt.sport.service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import bookCourt.sport.DTO.StadiumDTO;
import bookCourt.sport.model.Court;
import bookCourt.sport.model.Stadium;
import bookCourt.sport.response.ApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import bookCourt.sport.repo.StadiumRepo;
import bookCourt.sport.utils.Utility;

@Service
@Transactional
public class StadiumService {

    private final StadiumRepo stadiumRepository;

    public StadiumService(StadiumRepo stadiumRepository) {
        this.stadiumRepository = stadiumRepository;
    }

    public ApiResponse<List<Stadium>> getAllStadiums() {
        List<Stadium> stadiums = stadiumRepository.findAll();
        System.out.println(stadiums);
        if(stadiums.isEmpty()){
            return ApiResponse.error("No stadium found");
        }
        return ApiResponse.success(stadiums);
    }

    public ApiResponse<Stadium> getStadiumById(Long id) {
        if(id<0){
            return  ApiResponse.error("Invalid stadium with this ID");
        }
        Optional<Stadium> stadium = stadiumRepository.findById(id);
        return stadium.map(ApiResponse::success).orElseGet(() -> ApiResponse.error("Not found stadium with this ID"));
    }

    public ApiResponse<Stadium> createStadium(StadiumDTO stadiumDTO) {
        Stadium stadium = new Stadium();

        stadium.setName(stadiumDTO.getName());
        stadium.setTown(stadiumDTO.getTown());
        stadium.setLatitude(stadiumDTO.getLatitude());
        stadium.setLongitude(stadiumDTO.getLongitude());
        stadium.setAddress(stadiumDTO.getAddress());
        stadium.setRegion(stadiumDTO.getRegion());
        stadium.setSport(stadiumDTO.getSport());
        stadium.setReservationByTime(LocalTime.parse(Utility.normalizeTime(stadiumDTO.getReservationByTime())));

        stadium.setMondayStart(LocalTime.parse(Utility.normalizeTime(stadiumDTO.getMondayStart())));
        stadium.setMondayEnd(LocalTime.parse(Utility.normalizeTime(stadiumDTO.getMondayEnd())));
        stadium.setTuesdayStart(LocalTime.parse(Utility.normalizeTime(stadiumDTO.getTuesdayStart())));
        stadium.setTuesdayEnd(LocalTime.parse(Utility.normalizeTime(stadiumDTO.getTuesdayEnd())));
        stadium.setWednesdayStart(LocalTime.parse(Utility.normalizeTime(stadiumDTO.getWednesdayStart())));
        stadium.setWednesdayEnd(LocalTime.parse(Utility.normalizeTime(stadiumDTO.getWednesdayEnd())));
        stadium.setThursdayStart(LocalTime.parse(Utility.normalizeTime(stadiumDTO.getThursdayStart())));
        stadium.setThursdayEnd(LocalTime.parse(Utility.normalizeTime(stadiumDTO.getThursdayEnd())));
        stadium.setFridayStart(LocalTime.parse(Utility.normalizeTime(stadiumDTO.getFridayStart())));
        stadium.setFridayEnd(LocalTime.parse(Utility.normalizeTime(stadiumDTO.getFridayEnd())));
        stadium.setSaturdayStart(LocalTime.parse(Utility.normalizeTime(stadiumDTO.getWednesdayStart())));
        stadium.setSaturdayEnd(LocalTime.parse(Utility.normalizeTime(stadiumDTO.getWednesdayEnd())));
        stadium.setSundayStart(LocalTime.parse(Utility.normalizeTime(stadiumDTO.getWednesdayStart())));
        stadium.setSundayEnd(LocalTime.parse(Utility.normalizeTime(stadiumDTO.getWednesdayEnd())));


        List<Court> courts = stadiumDTO.getCourts().stream().map(courtDTO -> {
            Court court = new Court();
            court.setName(courtDTO.getName());
            court.setCategory(courtDTO.getCategory());
            court.setPrice(Integer.parseInt(String.valueOf(courtDTO.getPrice())));
            court.setInterior(courtDTO.getInterior());
            court.setStadium(stadium);
            return court;
        }).collect(Collectors.toList());
        stadium.setCourts(courts);

        return ApiResponse.success(stadiumRepository.save(stadium));
    }

    public ApiResponse<Stadium> updateStadium(Long id, Stadium updatedStadium) {
        if(id<0){
          return  ApiResponse.error("Invalid stadium with this ID");
        }
        Optional<Stadium> stadiumUpdated = stadiumRepository.findById(id)
                .map(stadium -> {
                    stadium.setName(updatedStadium.getName());
                    stadium.setTown(updatedStadium.getTown());
                    stadium.setAddress(updatedStadium.getAddress());
                    stadium.setRegion(updatedStadium.getRegion());
                    stadium.setLatitude(updatedStadium.getLatitude());
                    stadium.setLongitude(updatedStadium.getLongitude());
                    stadium.setSport(updatedStadium.getSport());
                    stadium.setMondayStart(updatedStadium.getMondayStart());
                    stadium.setMondayEnd(updatedStadium.getMondayEnd());
                    // similarly for other days...
                    stadium.setCourts(updatedStadium.getCourts());
                    return stadiumRepository.save(stadium);
                });
        return stadiumUpdated.map(ApiResponse::success).orElseGet(() -> ApiResponse.error("Not found stadium with this ID"));
    }

    public ApiResponse<String> deleteStadium(Long id)  {
        if(id < 0){
          return ApiResponse.error("Invalid stadium ID");
        }
        stadiumRepository.deleteById(id);
        return ApiResponse.success("The stadium is deleted");
    }

    public ApiResponse<List<Stadium>> getStadiumBySport(String sport) {
        if(sport.isBlank()){
            return  ApiResponse.error("There is no sport");
        }
        List<Stadium> stadium = stadiumRepository.findBySport(sport);
        if(stadium.isEmpty()) ApiResponse.error("Not found stadiums by this sport");
        return ApiResponse.success(stadium);
    }
}

