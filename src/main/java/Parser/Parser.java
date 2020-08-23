package parser;

import command.Command;
import mugexception.MugException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    public static void input(Command command, int splitNum, boolean isTime) throws MugException {
        if (splitNum < 2) {
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

    public static LocalDate date(String date) throws MugException {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException ex) {
            throw new MugException("MUG is picky. Give him the correct date format(YYYY-MM-DD) XD");
        }
    }

    public static Command command(String command) throws MugException {
        try {
            return Command.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new MugException("Hey!!! I'm sorry, but MUG don't know what that means :-()");
        }
    }

    public static int index(String strIndex, int splitNum) throws MugException {
        try {
            int index = Integer.parseInt(strIndex);

            if (splitNum < 2) {
                throw new MugException("HEY!!! Don't be stingy give MUG more information >.<");
            }
            return index;
        } catch (NumberFormatException ex) {
            throw new MugException("Please feed MUG an integer number ~_~");
        }
    }
}

