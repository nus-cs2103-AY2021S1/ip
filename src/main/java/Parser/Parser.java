package Parser;

import Command.Command;
import MugException.MugException;

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
     * @throws MugException if splitNum less than 2.
     */
    public static void input(Command command, int splitNum, boolean isTime) throws MugException {
        if (splitNum < 2 ) {
            if ((isTime && command == Command.DEADLINE)
            ) {
                throw new MugException("HEY!!! Feed me with {/by [date]}. MUG is hungry T_T");
            } else if (isTime
                    && command == Command.EVENT) {
                throw new MugException("HEY!!! Feed me with {/at [date]}. MUG is hungry T_T");
            } else {
                throw new MugException("HEY!!! Don't be stingy give MUG more information >.<");
            }
        }
    }

    /**
     * Validates the info that user's input.
     *
     * @param command user command.
     * @param info task description.
     * @param isTime about time.
     * @throws MugException if info pass in is empty.
     */
    public static void info(Command command, String info, boolean isTime) throws MugException {
        if (info.trim().equals("")) {
            if ((isTime && command == Command.DEADLINE)
            ) {
                throw new MugException("HEY!!! Feed me with {/by [date]}. MUG is hungry T_T");
            } else if (isTime
                    && command == Command.EVENT) {
                throw new MugException("HEY!!! Feed me with {/at [date]}. MUG is hungry T_T");
            } else {
                throw new MugException("HEY!!! Don't be stingy give MUG more information >.<");
            }
        }
    }

    /**
     * Parses and validates the date.
     *
     * @param date date.
     * @return parse date.
     * @throws MugException if date pass in with wrong format.
     */
    public static LocalDate date(String date) throws MugException {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException ex) {
            throw new MugException("MUG is picky. Give him the correct date format(YYYY-MM-DD) XD");
        }
    }

    /**
     * Parses and validates command.
     *
     * @param command user command.
     * @return parse command.
     * @throws MugException if wrong command give.
     */
    public static Command command(String command) throws MugException {
        try {
            return Command.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new MugException("Hey!!! I'm sorry, but MUG don't know what that means :-()");
        }
    }

    /**
     * Parses and validates integer number.
     *
     * @param strIndex integer in string.
     * @param splitNum length of the split string.
     * @return Integer number.
     * @throws MugException if splitNum smaller than 2 or strIndex is not integer in string.
     */
    public static int index(String strIndex, int splitNum) throws MugException {
        try {
            int index = Integer.parseInt(strIndex);

            if (splitNum < 2) {
                throw new MugException("HEY!!! Don't be stingy give MUG more information >.<");
            }
            return index;
        } catch ( NumberFormatException ex) {
            throw new MugException("Please feed MUG an integer number ~_~");
        }
    }
}

