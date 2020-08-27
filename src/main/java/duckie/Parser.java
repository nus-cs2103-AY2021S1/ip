package duckie;

import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duckie.command.*;
import duckie.exception.*;
import duckie.task.*;

/**
 * Responsible for the parsing of input Commands
 */
public class Parser {
    private static boolean is_Word(String s) {
        return (s.length() > 0 && s.split("\\s+").length == 1);
    }

    /**
     * Parse the input Command to direct Duckie on what actions to carry out
     * @param fullCommand Input string command
     * @return Specific Command
     * @throws DuckieException
     */
    public static Command parse(String fullCommand) throws DuckieException {
        String input = fullCommand.strip();
        if (input.equalsIgnoreCase("bye")) {
            return new ByeCommand();
        } else if (input.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (input.toLowerCase().indexOf("done") == 0) {
            if (is_Word(input)) {
                throw new DuckieInsufficientInfoException();
            }

            try {
                Integer.parseInt(input.split(" ")[1]);
            } catch (NumberFormatException e) {
                throw new DuckieException("You have to input a number after 'done'!");
            }

            int ind = Integer.parseInt(input.split(" ")[1]);

            return new DoneCommand(ind);
        } else if (input.toLowerCase().indexOf("delete") == 0) {
            if (is_Word(input)) {
                throw new DuckieInsufficientInfoException();
            }

            String description = input.split(" ")[1].strip();

            if (description.toLowerCase().equals("all")) {
                return new DeleteAllCommand();
            } else {
                try {
                    Integer.parseInt(input.split(" ")[1]);
                } catch (NumberFormatException e) {
                    throw new DuckieException("You have to input a number after 'delete'!\n"
                            + "\t" + "If you want to delete all, input 'delete all'");
                }
                int ind = Integer.parseInt(description);
                return new DeleteCommand(ind);
            }
        } else if (input.toLowerCase().indexOf("todo") == 0) {
            if (is_Word(input)) {
                throw new DuckieInsufficientInfoException();
            }
            String todo = input.split(" ", 2)[1];
            Task t1 = new Todo(todo);
            return new AddCommand(t1);
        } else if (input.toLowerCase().indexOf("deadline") == 0) {
            if (is_Word(input)) {
                throw new DuckieInsufficientInfoException();
            }

            if (input.contains("/")) {
                String[] splitted = input.split("/");
                if (is_Word(splitted[1])) {
                    throw new DuckieException("Please state a date in the format 'DD MMM YYYY' after '/by'.\n"
                            + "\t" + "For example, 'deadline Quiz /by 21 Aug 2000'.");
                }
                String time = splitted[1].split(" ", 2)[1];
                String description = splitted[0].split(" ", 2)[1];
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
                LocalDate date = LocalDate.parse(time, formatter);
                Task t1 = new Deadline(description, date);
                return new AddCommand(t1);
            } else {
                throw new DuckieException("Please use '/by' to indicate the date input.");
            }
        } else if (input.toLowerCase().indexOf("event") == 0) {
            if (is_Word(input)) {
                throw new DuckieInsufficientInfoException();
            }

            if (input.contains("/")) {
                String[] splitted = input.split("/");
                if (is_Word(splitted[1])) {
                    throw new DuckieException("Please state a date in the format 'DD MMM YYYY HH:MM a' after '/at'.\n"
                            + "\t" + "For example, 'event Party /at 21 Aug 2000 07:20 PM'.");
                }
                String time = splitted[1].split(" ", 2)[1];
                String description = splitted[0].split(" ", 2)[1];
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a");
                LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
                Task t1 = new Event(description, dateTime);
                return new AddCommand(t1);
            } else {
                throw new DuckieException("Please use '/at' to indicate the date input.");
            }
        } else if (input.toLowerCase().indexOf("find") == 0) {
            if (is_Word(input)) {
                throw new DuckieInsufficientInfoException();
            }

            String keyword = input.split(" ", 2)[1];
            return new FindCommand(keyword);
        } else {
            throw new DuckieInvalidCommandException();
        }

    }
}
