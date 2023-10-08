package cinema.controllers;

//import cinema.entities.Cinema;
import cinema.entities.Cinema;
import cinema.entities.OrderedSeat;
import cinema.entities.Seat;
import cinema.entities.Token;
import cinema.repositories.CinemaRepository;
import cinema.services.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class CinemaController {
    private final CinemaService cinemaService;

    @Autowired
    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping("/seats")
    public Cinema getCinemaInfo() {
        return cinemaService.getCinemaInfo();
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchase(@RequestBody Seat seat){
        if(seat.getColumn()>9 || seat.getRow()>9 || seat.getRow()<1 || seat.getColumn()<1){
            return new ResponseEntity<>(Map.of("error", "The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);
        }
        for (int i = 0; i < cinemaService.getAvailableSeats().size(); i++) {
            Seat s = cinemaService.getAvailableSeats().get(i);
            if(s.equals(seat)){
                OrderedSeat orderedSeat = new OrderedSeat(UUID.randomUUID(),s);
                cinemaService.getOrderedSeats().add(orderedSeat);
                cinemaService.getAvailableSeats().remove(i);
                return new ResponseEntity<>(orderedSeat,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(Map.of("error", "The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestBody Token token){
        List<OrderedSeat> orderedSeats = cinemaService.getOrderedSeats();
        for(OrderedSeat orderedSeat:orderedSeats){
            if(orderedSeat.getToken().equals(token.getToken())){
                orderedSeats.remove(orderedSeat);
                cinemaService.getAvailableSeats().add(orderedSeat.getTicket());
                return new ResponseEntity<>(Map.of("ticket",orderedSeat.getTicket()),HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(Map.of("error","Wrong token!"),HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/stats")
    public ResponseEntity<?> cinemaStats(@RequestParam(required = false) String password){
        if(password!=null && password.equals(cinemaService.getPassword())){
            Map<String,Integer> statistics = new HashMap<>();
            int currentIncome = 0;
            for(OrderedSeat orderedSeat: cinemaService.getOrderedSeats()){
                currentIncome += orderedSeat.getTicket().getPrice();
            }
            int currentAvailable = cinemaService.getAvailableSeats().size();
            int currentPurchased = cinemaService.getOrderedSeats().size();

            statistics.put("income",currentIncome);
            statistics.put("available",currentAvailable);
            statistics.put("purchased",currentPurchased);
            return new ResponseEntity<>(statistics,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Map.of("error", "The password is wrong!"), HttpStatus.valueOf(401));
        }
    }

}
