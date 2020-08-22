package duke.parser;

import duke.commands.EnumCommand;
import duke.exception.DukeException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Parser {

    public static EnumCommand parseCommand(String instruction) throws DukeException {
        Integer indexOfSplit = instruction.indexOf(' ');
        String command;
        EnumCommand enumCommand;

        if (indexOfSplit == -1) {
            command = instruction;
        } else {
            command = instruction.substring(0, indexOfSplit);
        }

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
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means, there is a typo  :-(");
        }

        return enumCommand;
    }

    public static LocalDate dateProcessor(String date) throws DukeException, DateTimeException {

        String[] dateSplit = date.split("-");
        if (dateSplit.length != 3 || dateSplit[0].strip().length() != 4
                || dateSplit[1].strip().length() != 2 || dateSplit[2].strip().length() != 2) {
            throw new DukeException("The input date format is incorrect");
        }
        LocalDate localDate = LocalDate.parse(date);

        return localDate;
    }

    public static LocalDateTime dateTimeProcessor(String dateTime) throws DukeException, DateTimeException {
        String[] dateTimeSplit = dateTime.strip().split(" ");
        if (dateTimeSplit.length != 2) {
            throw new DukeException("The format of the input date and time is incorrect");
        }
        String date = dateTimeSplit[0].strip();
        String[] dateSplit = date.split("-");
        if (dateSplit.length != 3 || dateSplit[0].strip().length() != 4
                || dateSplit[1].strip().length() != 2 || dateSplit[2].strip().length() != 2) {
            throw new DukeException("The input date format is incorrect");
        }
        LocalDate localDate = LocalDate.parse(date);

        String time = dateTimeSplit[1].strip();
        if (time.length() != 4) {
            throw new DukeException("The input time format is incorrect");
        }
        LocalTime localTime = LocalTime.of(Integer.parseInt(time.substring(0, 2)), Integer.parseInt(time.substring(2, 4)));
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        return localDateTime;
    }
}
