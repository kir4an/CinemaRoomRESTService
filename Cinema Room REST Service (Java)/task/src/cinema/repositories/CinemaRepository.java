package cinema.repositories;

import cinema.entities.Cinema;

import cinema.entities.OrderedSeat;
import cinema.entities.Seat;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
//comments
@Repository
public class CinemaRepository {
    private static List<Seat> availableSeats;
    private static List<OrderedSeat> orderedSeats = new ArrayList<>();

    private String SUPER_SECRET = "super_secret";

    public String getSUPER_SECRET() {
        return SUPER_SECRET;
    }

    private  static int TOTAL_ROWS = 9;
    private  static int TOTAL_COLUMN = 9;


    static {
        availableSeats = new ArrayList<>();
        for(int row=1;row<=TOTAL_ROWS;row++){
            for(int column = 1;column<=TOTAL_COLUMN;column++){
                availableSeats.add(new Seat(row,column));
                }
            }
    }
    public static Cinema getAllSeats(){
        availableSeats = new ArrayList<>();
        for(int row=1;row<=TOTAL_ROWS;row++){
            for(int column = 1;column<=TOTAL_COLUMN;column++){
                availableSeats.add(new Seat(row,column));
            }
        }
        return new Cinema(TOTAL_ROWS,TOTAL_COLUMN, availableSeats);
    }
    public static List<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public static int getTotalRows() {
        return TOTAL_ROWS;
    }

    public static int getTotalColumn() {
        return TOTAL_COLUMN;
    }
    public static List<OrderedSeat> getOrderedSeats() {
        return orderedSeats;
    }
}
