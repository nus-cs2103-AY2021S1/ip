package taskbot.logic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

//All files in taskbot.task are used
import taskbot.task.*;

import taskbot.exceptions.InvalidIndexException;
import taskbot.exceptions.TaskAlreadyCompleteException;
import taskbot.exceptions.WrongFormatException;

import static taskbot.helper.Helper.borderString;
import static taskbot.helper.Helper.handleException;

public class Taskbot {
    private String logo;
    private final String name = "TaskBot";
    private ArrayList<Task> tasks;

    /**
     * Constructor for the Taskbot class
     * @param logo The logo to be displayed for the title
     */
    public Taskbot(String logo) {
        this.logo = logo;
        this.tasks = new ArrayList<>();
    }

    /**
     * Prints out the title using the given logo
     */
    public void printTitle() {
        System.out.println(logo);
    }

    /**
     * Method invoked to greet the user
     */
    public void greet() {
        System.out.printf("Hello there, my name is %s.\nHow may I be of assistance today?\n", name);
    }

    /**
     * Method invoked to say goodbye to the user
     */
    public void sayBye() {
        String message = "Goodbye, I await your next visit.";
        borderString(message);
    }
}
