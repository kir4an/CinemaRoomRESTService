package cinema.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
//comments
@Getter
@Setter
public class Cinema {
    private int rows;
    private int columns;
    private List<Seat> seats;

    @JsonIgnore
    private List<Seat> orderedSeats;

    public Cinema(int rows, int columns, List<Seat> seats) {
        this.rows = rows;
        this.columns = columns;
        this.seats = seats;
        this.orderedSeats = new ArrayList<>();
    }
}
