package duke.Parser;

import duke.Commands.AddCommand;
import duke.Commands.Command;
import duke.Commands.DeleteCommand;
import duke.Commands.DoneCommand;
import duke.Commands.ExitCommand;
import duke.Commands.ListCommand;

import duke.Exceptions.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parses and understands user commands.
 * Break down user input string and creates appropriate user commands.
 * Checks for wrong user inputs and throw Duke Exceptions if necessary.
 */
public class Parser {

    /**
     * Static method to parse user input.
     * @param userCommand User string from the command line.
     * @return Appropriate command to reflect user input.
     * @throws DukeException
     */
    public static Command parse(String userCommand) throws DukeException {

        // Formats the way users input their dates for a task.
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yy");

        // Formats the way users input their time for a task.
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        String[] userWord = userCommand.split(" ", 2);

        // Exits the program.
        if (userCommand.equals("bye")) {
            return new ExitCommand();
        }
        // Switch block checks for the appropriate commands to create based on user input.
        switch (userWord[0]) {
        case "list":
            return new ListCommand();
            //Fallthrough

        case "find" :
            if (userWord.length == 1 || userWord[1].equals("")) {
                throw new DukeException("   ☹ OOPS!!! The description of the command find cannot be empty.");
            }
            return new FindCommand(userWord[1]);
            //Fallthrough

        case "todo":
            if (userWord.length == 1 || userWord[1].equals("")) {
                throw new DukeException("   ☹ OOPS!!! The description of the command todo cannot be empty.");
            }
            return new AddCommand("todo", userWord[1]);
            //Fallthrough

        case "deadline":
            if (userWord.length == 1 || userWord[1].equals("")) {
                throw new DukeException("   ☹ OOPS!!! The description of the command deadline cannot be empty.");
            }
            String[] content = userWord[1].split("/by", 2);
            if (content.length == 1 || content[0].equals("")) {
                throw new DukeException("   ☹ OOPS!!! We can't seem to find your event description.");
            }
            if (content[1].equals("")) {
                throw new DukeException("   ☹ OOPS!!! We can't seem to find your event time. Please type /by before your preferred timing");
            }
            try {
                String[] dateTime = content[1].split(" ",3);
                LocalDate localDate = LocalDate.from(dateFormatter.parse(dateTime[1]));
                LocalTime localTime = LocalTime.from(timeFormatter.parse(dateTime[2]));
                return new AddCommand("deadline", content[0], localDate, localTime);

            } catch (DateTimeParseException error) {
                throw new DukeException("   ☹ OOPS!!! It seems like you've provided us with the wrong date time format for your event. " +
                        "Please structure it as yyyy-mm-dd hh:mm");
            }
            //Fallthrough

        case "event":
            if (userWord.length == 1 || userWord[1].equals("")) {
                throw new DukeException("   ☹ OOPS!!! The description of the command event cannot be empty.");
            }
            String[] content2 = userWord[1].split("/at", 2);
            if (content2.length == 1 || content2[0].equals("")) {
                throw new DukeException("   ☹ OOPS!!! We can't seem to find your event description.");
            }
            if (content2[1].equals("")) {
                throw new DukeException("   ☹ OOPS!!! We can't seem to find your event time. Please type /at before your preferred timing");
            }
            try {
                String[] dateTime = content2[1].split(" ",3);
                LocalDate localDate = LocalDate.from(dateFormatter.parse(dateTime[1]));
                LocalTime localTime = LocalTime.from(timeFormatter.parse(dateTime[2]));
                return new AddCommand("event", content2[0], localDate, localTime);

            } catch (DateTimeParseException error) {
                throw new DukeException("   ☹ OOPS!!! It seems like you've provided us with the wrong date time format for your event. " +
                        "Please structure it as yyyy-mm-dd hh:mm");
            }
            //Fallthrough

        case "done":
            if (userWord.length == 1 || userWord[1].equals("")) {
                throw new DukeException("   ☹ OOPS!!! The description of the command done cannot be empty.");
            }
            try {
                int index = Integer.parseInt(userWord[1]) - 1;
                return new DoneCommand(index);
            } catch (NumberFormatException e) {
                throw new DukeException("    Please provide a positive number when indicating task index.");
            }
            //Fallthrough

        case "delete":
            if (userWord.length == 1 || userWord[1].equals("")) {
                throw new DukeException("   ☹ OOPS!!! The description of the command done cannot be empty.");
            }
            try {
                int index2 = Integer.parseInt(userWord[1]) - 1;
                return new DeleteCommand(index2);
            } catch (NumberFormatException e) {
                throw new DukeException("    Please provide a positive number when indicating task index.");
            }
            //Fallthrough

        default:
            throw new DukeException("    Sorry! I'm not really sure what to do with this command yet ☹");
            //Fallthrough
        }
    }
}
