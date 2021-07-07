package mug.validator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import mug.command.Command;
import mug.mugexception.MugException;

/**
 * Validates or parses the user command
 */
public class Validator {

    /**
     * Validates user's input.
     *
     * @param command User command.
     * @param splitInputLen Length of the split string.
     * @param isTime time related.
     * @throws MugException If splitNum less than 2.
     */
    public static void input(Command command, int splitInputLen, boolean isTime) throws MugException {
        if (splitInputLen < 2) {
            boolean isDeadline = command == Command.DEADLINE;
            boolean isEvent = command == Command.EVENT;
            if ((isTime && isDeadline)) {
                throw new MugException("HEY!!! Feed me with {/by [date]}. Mug is hungry T_T");
            } else if (isTime && isEvent) {
                throw new MugException("HEY!!! Feed me with {/at [date]}. Mug is hungry T_T");
            } else {
                throw new MugException("HEY!!! Don't be stingy give Mug more information >.<");
            }
        }
    }

    /**
     * Validates the info that user's input.
     *
     * @param command User command.
     * @param info Task description.
     * @param isTime time related.
     * @throws MugException If info pass in is empty.
     */
    public static void info(Command command, String info, boolean isTime) throws MugException {
        boolean isDeadline = command == Command.DEADLINE;
        boolean isEvent = command == Command.EVENT;
        if (info.contains("|")) {
            throw new MugException("Sorry!! Mug don't like to eat food with \"|\" :(");
        }
        if (info.trim().equals("")) {
            if ((isTime && isDeadline)) {
                throw new MugException("HEY!!! Feed me with {/by [date]}. Mug is hungry T_T");
            } else if (isTime && isEvent) {
                throw new MugException("HEY!!! Feed me with {/at [date]}. Mug is hungry T_T");
            } else {
                throw new MugException("HEY!!! Don't be stingy give Mug more information >.<");
            }
        }
    }

    /**
     * Parses and validates the date.
     *
     * @param date Date.
     * @return Parse date.
     * @throws MugException If date pass in with wrong format.
     */
    public static LocalDate date(String date) throws MugException {
        try {
            return LocalDate.parse(date.trim());
        } catch (DateTimeParseException ex) {
            throw new MugException("Mug is picky. Give him the correct date format(YYYY-MM-DD) XD");
        }
    }

    /**
     * Parses and validates command.
     *
     * @param command User command.
     * @return Parse command.
     * @throws MugException If wrong command give.
     */
    public static Command command(String command) throws MugException {
        try {
            return Command.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new MugException("Hey!!! I'm sorry, but Mug don't know what that means :3");
        }
    }

    /**
     * Parses and validates integer number.
     *
     * @param strIndex Integer in string.
     * @param splitInputLen Length of the split string.
     * @return Integer number.
     * @throws MugException If splitInputLen smaller than 2 or strIndex is not integer in string.
     */
    public static int index(String strIndex, int splitInputLen) throws MugException {
        try {
            int index = Integer.parseInt(strIndex.trim());

            if (splitInputLen < 2) {
                throw new MugException("HEY!!! Don't be stingy give Mug more information >.<");
            }
            return index;
        } catch (NumberFormatException ex) {
            throw new MugException("Please feed Mug an integer number ~_~");
        }
    }
}

