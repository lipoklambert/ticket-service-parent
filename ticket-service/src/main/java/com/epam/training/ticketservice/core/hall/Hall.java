package com.epam.training.ticketservice.core.hall;


import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Hall {

    @Id
    private String name;
    private Long seatRows;
    private Long seatColumns;

    @Override
    public String toString() {
        return "Room " + name + " with " + String.valueOf(seatRows * seatColumns) + " seats, " + seatRows.toString()
                + " rows and " + seatColumns.toString() + " columns";
    }
}
