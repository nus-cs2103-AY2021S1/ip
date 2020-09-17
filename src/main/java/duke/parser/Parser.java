package duke.parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ViewDayCommand;
import duke.exception.DukeException;

/**
 * Parses and understands user commands.
 * Break down user input string and creates appropriate user commands.
 * Checks for wrong user inputs and throw Duke Exceptions if necessary.
 */
public class Parser {

    private static String suggestion = "\n\nAdditionally, do note that the description cannot be empty "
            + "for the following commands:"
            + "\n1. todo"
            + "\n2. event"
            + "\n3. deadline"
            + "\n4. done"
            + "\n5. delete"
            + "\n6. find"
            + "\n7. view";

    private static String unknownCommandMessage = "OOPS!!! I've not yet learned what that means."
            + " Please enter a valid command :) "
            + suggestion;

    private static boolean isValidDescription(String[] userInput) throws DukeException {
        if (userInput.length == 1 || userInput[1].equals("")) {
            throw new DukeException("Hmm, it seems some of your arguments are missing."
            + suggestion);
        }
        return true;
    }

    private static Command DeadlineAndEventHandler(String[] userInput, String eventType
            , String keyword, DateTimeFormatter dateFormatter
            , DateTimeFormatter timeFormatter) throws DukeException {

        String[] content = userInput[1].split(keyword, 2);
        if (content.length == 1 || content[0].equals("")) {
            throw new DukeException("It seems you've not used the appropriate "
                    + "command syntax. \uD83D\uDE1E\n\nPlease use the following formatting:"
                    + "\nevent DESCRIPTION /at DD.MM.YY HHmm or\n"
                    + "deadline DESCRIPTION /by DD.MM.YY HHmm");
        }
        if (content[1].equals("")) {
            throw new DukeException("Beep bop!!! We can't seem to find your task time. "
                    + "\uD83D\uDE1E\n\nPlease type "
                    + (eventType.equals("deadline") ? "/by" : "/at")
                    + " before your preferred timing");
        }
        try {
            String[] dateTime = content[1].split(" ", 3);

            if (dateTime.length != 3) {
                throw new DukeException("Beep bop!!! We can't seem to find your event time. "
                        + "\uD83D\uDE1E");
            }

            LocalDate currentDate = LocalDate.now();
            LocalTime currentTime = LocalTime.now();
            LocalDate localDate = LocalDate.from(dateFormatter.parse(dateTime[1]));
            LocalTime localTime = LocalTime.from(timeFormatter.parse(dateTime[2]));

            if (localDate.isBefore(currentDate)) {
                throw new DukeException("Strange... It seems that your task occurred "
                        + "in the past...\nUnfortunately, I'm not yet equipped with the "
                        +"ability to time travel...");
            }
            if (localDate.isEqual(currentDate) && localTime.isBefore(currentTime)) {
                throw new DukeException("Strange... It seems that your task occurred "
                        + "in the past...\nUnfortunately, I'm not yet equipped with the "
                        +"ability to time travel...");
            }

            return new AddCommand(eventType, content[0], localDate, localTime);

        } catch (DateTimeParseException error) {
            throw new DukeException("It seems like you've provided us "
                    + "with the wrong date time format for your event. \uD83D\uDE1E"
                    + "\n\nPlease structure it as dd.mm.yy HHmm where H refers to hour and m refers to min"
                    + "\n\nFor example, 05.02.20 1200 represents 5th Feb 2020, 12pm");
        }
    }

    private static Command DeleteAndDoneHandler(String[] userInput, String taskType) throws DukeException {
        try {
            int index = Integer.parseInt(userInput[1]) - 1;
            switch (taskType) {
            case "done":
                return new DoneCommand(index);
            case "delete":
                return new DeleteCommand(index);
            default:
                throw new DukeException("Wrong usage of DeleteAndDoneHandler method in Parser class");
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Please provide a positive number when indicating task index.");
        }
    }

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

        if (userCommand.equals("bye")) {
            return new ExitCommand();
        }
        if (userCommand.equals("list")) {
            return new ListCommand();
        }

        isValidDescription(userWord);

        // Switch block checks for the appropriate commands to create based on user input.
        switch (userWord[0]) {
        case "view":
            return new ViewDayCommand(userWord[1], dateFormatter);
            // Fallthrough

        case "find":
            return new FindCommand(userWord[1]);
            //Fallthrough

        case "todo":
            return new AddCommand("todo", userWord[1]);
            //Fallthrough

        case "deadline":
            return DeadlineAndEventHandler(userWord, "deadline", "/by", dateFormatter, timeFormatter);
            //Fallthrough

        case "event":
            return DeadlineAndEventHandler(userWord, "event", "/at", dateFormatter, timeFormatter);
            //Fallthrough

        case "done":
            return DeleteAndDoneHandler(userWord, "done");
            //Fallthrough

        case "delete":
            return DeleteAndDoneHandler(userWord, "delete");
            //Fallthrough

        default:
            throw new DukeException(unknownCommandMessage);
            //Fallthrough
        }
    }
}
