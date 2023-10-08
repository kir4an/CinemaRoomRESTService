package cinema.services;

//import cinema.entities.Cinema;
import cinema.entities.Cinema;
import cinema.entities.OrderedSeat;
import cinema.entities.Seat;
import cinema.repositories.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//comments
@Service
public class CinemaService {
    private final CinemaRepository cinemaRepository;
    @Autowired
    public CinemaService(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    public Cinema getCinemaInfo(){
        return cinemaRepository.getAllSeats();
    }
    public List<OrderedSeat> getOrderedSeats(){
        return cinemaRepository.getOrderedSeats();
    }
    public List<Seat> getAvailableSeats(){
        return cinemaRepository.getAvailableSeats();
    }
    public String getPassword(){
        return cinemaRepository.getSUPER_SECRET();
    }
}
