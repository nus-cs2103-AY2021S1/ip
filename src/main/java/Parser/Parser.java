package Parser;

import Command.Command;
import DukeException.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Parses and validates the user command
 */
public class Parser {

    /**
     * Validates user's input.
     *
     * @param command user command.
     * @param splitNum length of the split string.
     * @param isTime about time.
     * @throws DukeException if splitNum less than 2.
     */
    public static void input(Command command, int splitNum, boolean isTime) throws DukeException {
        if (splitNum < 2 ) {
            if ((isTime && command == Command.DEADLINE)
            ) {
                throw new DukeException("HEY!!! Feed me with {/by [date]}. MUG is hungry T_T");
            } else if (isTime
                    && command == Command.EVENT) {
                throw new DukeException("HEY!!! Feed me with {/at [date]}. MUG is hungry T_T");
            } else {
                throw new DukeException("HEY!!! Don't be stingy give MUG more information >.<");
            }
        }
    }

    /**
     * Validates the info that user's input.
     *
     * @param command user command.
     * @param info task description.
     * @param isTime about time.
     * @throws DukeException if info pass in is empty.
     */
    public static void info(Command command, String info, boolean isTime) throws DukeException {
        if (info.trim().equals("")) {
            if ((isTime && command == Command.DEADLINE)
            ) {
                throw new DukeException("HEY!!! Feed me with {/by [date]}. MUG is hungry T_T");
            } else if (isTime
                    && command == Command.EVENT) {
                throw new DukeException("HEY!!! Feed me with {/at [date]}. MUG is hungry T_T");
            } else {
                throw new DukeException("HEY!!! Don't be stingy give MUG more information >.<");
            }
        }
    }

    /**
     * Parses and validates the date.
     *
     * @param date date.
     * @return parse date.
     * @throws DukeException if date pass in with wrong format.
     */
    public static LocalDate date(String date) throws DukeException {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException ex) {
            throw new DukeException("MUG is picky. Give him the correct date format(YYYY-MM-DD) XD");
        }
    }

    /**
     * Parses and validates command.
     *
     * @param command user command.
     * @return parse command.
     * @throws DukeException if wrong command give.
     */
    public static Command command(String command) throws DukeException {
        try {
            return Command.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new DukeException("Hey!!! I'm sorry, but MUG don't know what that means :-()");
        }
    }

    /**
     * Parses and validates integer number.
     *
     * @param strIndex integer in string.
     * @param splitNum length of the split string.
     * @return Integer number.
     * @throws DukeException if splitNum smaller than 2 or strIndex is not integer in string.
     */
    public static int index(String strIndex, int splitNum) throws DukeException {
        try {
            int index = Integer.parseInt(strIndex);

            if (splitNum < 2) {
                throw new DukeException("HEY!!! Don't be stingy give MUG more information >.<");
            }
            return index;
        } catch ( NumberFormatException ex) {
            throw new DukeException("Please feed MUG an integer number ~_~");
        }
    }
}

