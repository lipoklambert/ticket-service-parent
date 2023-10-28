package com.epam.training.ticketservice.ui.commands;

import com.epam.training.ticketservice.core.hall.Hall;
import com.epam.training.ticketservice.core.hall.HallDataHandling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.util.List;

@ShellComponent
public class HallCommand extends SecurityConfiguration {

    private final HallDataHandling hallDataHandling;


    @Autowired
    public HallCommand(HallDataHandling hallDataHandling) {
        this.hallDataHandling = hallDataHandling;
    }

    @ShellMethodAvailability("isAdmin")
    @ShellMethod(key = "create room", value = "Creates new room in the Database")
    public String createNewCinemaHall(String name, Long rows, Long columns) {
        hallDataHandling.createCinemaHall(name, rows, columns);
        return "Room created";
    }

    @ShellMethodAvailability("isAdmin")
    @ShellMethod(key = "update room", value = "Updates an existing room in the Database")
    public String updateHall(String name, Long rows, Long columns) {
        hallDataHandling.updateCinemaHall(name, rows, columns);
        return "Room updated";
    }

    @ShellMethodAvailability("isAdmin")
    @ShellMethod(key = "delete room", value = "Deletes room from Database")
    public String deleteHall(String name) {
        hallDataHandling.deleteCinemaHall(name);
        return "Room deleted";
    }

    @ShellMethod(key = "list rooms", value = "List all rooms.")
    public String listHalls() {
        List<Hall> hallList = hallDataHandling.listCinemaHalls();
        StringBuilder stringToReturn = new StringBuilder();
        if (hallList.isEmpty()) {
            stringToReturn.append("There are no rooms at the moment");
        } else {
            for (Hall hall : hallList) {
                stringToReturn.append(hall.toString()).append("\n");
            }
        }
        return stringToReturn.toString();
    }
}
