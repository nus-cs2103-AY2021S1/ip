package duke.parser;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import duke.commands.EnumCommand;
import duke.exception.DukeException;


/**
 * A class that represents a parser which parses commands and dateTime
 */
public class Parser {

    /**
     * Constructs a parser that takes in no parameter.
     */
    public Parser() {
    }


    /**
     * Converts user input to command enumerations.
     *
     * @param instruction the string that represents user input
     * @return enumeration of commands that matches the input
     * @throws DukeException if the input format is illegal
     */
    public static EnumCommand parseCommand(String instruction) throws DukeException {
        Integer indexOfSplit = instruction.indexOf(' ');
        String command;
        EnumCommand enumCommand;

        if (indexOfSplit == -1) {
            command = instruction;
        } else {
            command = instruction.substring(0, indexOfSplit);
        }

        assert command.length() > 0 : "The parsing command should not be empty";

        if (command.equals("bye")) {
            enumCommand = EnumCommand.BYE;
        } else if (command.equals("done")) {
            enumCommand = EnumCommand.DONE;
        } else if (command.equals("delete")) {
            enumCommand = EnumCommand.DELETE;
        } else if (command.equals("list")) {
            enumCommand = EnumCommand.LIST;
        } else if (command.equals("todo")) {
            enumCommand = EnumCommand.TODO;
        } else if (command.equals("deadline")) {
            enumCommand = EnumCommand.DEADLINE;
        } else if (command.equals("event")) {
            enumCommand = EnumCommand.EVENT;
        } else if (command.equals("check")) {
            enumCommand = EnumCommand.CHECK;
        } else if (command.equals("find")) {
            enumCommand = EnumCommand.FIND;
        } else if (command.equals("update")) {
            enumCommand = EnumCommand.UPDATE;
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means, there is a typo  :-(");
        }

        return enumCommand;
    }

    /**
     * Converts the user input of date to the correct date format.
     *
     * @param date the input date string from user.
     * @return LocalDate Object that matches the user input date.
     * @throws DukeException if the user input format is illegal.
     * @throws DateTimeException if the date is an illegal date.
     */
    public static LocalDate parseDate(String date) throws DukeException, DateTimeException {
        Boolean isFormatValid;
        Boolean isYearValid;
        Boolean isMonthValid;
        Boolean isDayValid;

        String[] dateSplit = date.split("-");
        isFormatValid = dateSplit.length == 3;
        if (!isFormatValid) {
            throw new DukeException("The input date format is incorrect");
        }
        isYearValid = dateSplit[0].strip().length() == 4;
        isMonthValid = dateSplit[1].strip().length() == 2;
        isDayValid = dateSplit[2].strip().length() == 2;

        if (!isYearValid || !isMonthValid || !isDayValid) {
            throw new DukeException("The input date format is incorrect");
        }

        try {
            LocalDate localDate = LocalDate.parse(date);
            return localDate;
        } catch (DateTimeException e) {
            throw new DukeException("Sorry, the date time is invalid.");
        }
    }

    /**
     * Converts the user input of dateTime to the correct dateTime format.
     *
     * @param dateTime the input dateTime string from user.
     * @return a LocalDateTime object that matches the input string.
     * @throws DukeException if the user input format is illegal.
     * @throws DateTimeException if the date is an illegal date.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws DukeException {
        String[] dateTimeSplit = dateTime.strip().split(" ");

        if (dateTimeSplit.length != 2) {
            throw new DukeException("The format of the input date and time is incorrect");
        }

        try {
            String date = dateTimeSplit[0].strip();
            LocalDate localDate = parseDate(date);
            String time = dateTimeSplit[1].strip();
            if (time.length() != 4) {
                throw new DukeException("The input time format is incorrect");
            }

            LocalTime localTime = LocalTime.of(Integer.parseInt(time.substring(0, 2)),
                    Integer.parseInt(time.substring(2, 4)));
            LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
            return localDateTime;

        } catch (DateTimeException e) {
            throw new DukeException("Sorry, the date time is invalid.");
        }


    }
}
