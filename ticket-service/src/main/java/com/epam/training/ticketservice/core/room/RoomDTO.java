package com.epam.training.ticketservice.core.room;

public class RoomDTO {
    private String name;
    private Long seatRows;
    private Long seatColumns;

    public RoomDTO() {
        // Default constructor
    }

    public RoomDTO(String name, Long seatRows, Long seatColumns) {
        this.name = name;
        this.seatRows = seatRows;
        this.seatColumns = seatColumns;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSeatRows() {
        return seatRows;
    }

    public void setSeatRows(Long seatRows) {
        this.seatRows = seatRows;
    }

    public Long getSeatColumns() {
        return seatColumns;
    }

    public void setSeatColumns(Long seatColumns) {
        this.seatColumns = seatColumns;
    }
}
