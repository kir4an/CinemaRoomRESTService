package cinema.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter

public class Seat {
    @NonNull
    private int row;
    @NonNull
    private int column;
    @NonNull
    private int price;



    public Seat(int row,int column) {
        this.row = row;
        this.column = column;
        this.price = row<=4?10:8;
    }
    public Seat(){

    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Seat other = (Seat) obj;
        if (column != other.column)
            return false;
        if (row != other.row)
            return false;
        return true;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + row;
        result = prime * result + column;
        return result;
    }
}
