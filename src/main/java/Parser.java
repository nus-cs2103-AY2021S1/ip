import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

// Class  deals with making sense of the user command
public class Parser {

    public static Command parse(String input) throws DukeException {
        String commandType = input.split(" ")[0];

        if (input.equals("bye")) {
            return new ExitCommand();
        }

        if (input.equals("list")) {
            return new PrintCommand();
        }

        switch (commandType) {
            case "done":
                String[] indexStringArray = Arrays.copyOfRange(input.split(" "), 1, input.split(" ").length);
                ArrayList<Integer> indexList = new ArrayList<>();

                // Convert string index to integer
                for (String index : indexStringArray) {
                    try {
                        Integer i = Integer.parseInt(index);
                        if (i < 1) {
                            throw new DukeException("An invalid index was given.\n"
                                    + "Index must be positive.");
                        } else {
                            indexList.add(i);
                        }
                    } catch (NumberFormatException e) {
                        throw new DukeException("An invalid index was given.\n"
                                + index + " is not an integer.");
                    }
                }

                if (indexList.size() == 0) {
                    throw new DukeException("No index was given . Please provide a valid index.");
                }

                return new CompleteCommand(indexList);

            case "delete":
                String stringIndex = input.split(" ")[1];
                try {
                    int index = Integer.parseInt(stringIndex);
                    return new DeleteCommand(index);
                } catch (NumberFormatException e) {
                    throw new DukeException("An invalid index was given.\n"
                            + stringIndex + " is not an integer.");
                }

            case "todo":
                try {
                    String taskDetails = input.split(" ", 2)[1];
                    Task task = new ToDoTask(taskDetails);
                    return new AddCommand(task);
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Please add a description for the ToDo task.");
                }

            case "event":
                try {
                    String taskDetails = input.split(" ", 2)[1];
                    String summary = taskDetails.split(" /at ", 2)[0];

                    String date = taskDetails.split(" /at ", 2)[1];
                    LocalDate startDate = LocalDate.parse(date.split(" ")[0]);
                    LocalDate endDate = LocalDate.parse(date.split(" ")[1]);

                    Task task = new EventTask(summary, startDate, endDate);
                    return new AddCommand(task);

                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Please provide a summary and date of event.\n"
                            + "Separate the dates from summary using \" /at \" and "
                            + "separate the two dates using a space.");

                } catch (DateTimeParseException e) {
                    throw new DukeException("Please input a valid Date format.\n"
                            + "Valid Date format is YYYY-MM-DD.");
                }

            case "deadline":
                try {
                    String taskDetails = input.split(" ", 2)[1];
                    String summary = taskDetails.split(" /by ", 2)[0];

                    String deadline = taskDetails.split(" /by ", 2)[1];
                    LocalDate formattedDeadline = LocalDate.parse(deadline);

                    Task task = new DeadlineTask(summary, formattedDeadline);
                    return new AddCommand(task);

                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Please provide a summary and deadline.\n"
                            + "Separate the deadline from summary using \" /by \".");

                } catch (DateTimeParseException e) {
                    throw new DukeException("Please input a valid Date format.\n"
                            + "Valid Date format is YYYY-MM-DD.");
                }
        }

        throw new DukeException("This is not a valid command type.\n" +
                "Valid commands start with list, done, delete, todo, deadline, event or bye.");
    }
}
