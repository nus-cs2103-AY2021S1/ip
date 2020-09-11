import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Parser {
    public static final DateTimeFormatter DATE_INPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter DATE_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    public static final DateTimeFormatter TIME_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("h.mma");

    /**
     * Executes the relevant functions based on the given user command.
     *
     * @return the output from executing the command.
     */
    public String parseCommands(TaskList tasks, Ui ui, String command) throws DukeException {
        String output;
        String[] splitCommand = command.split(" ");

        switch (splitCommand[0]) {
        case "bye":
            output = ui.getExitMessage();
            break;

        case "deadline":
            output = this.parseDeadline(splitCommand, tasks);
            break;

        case "delete":
            output = tasks.deleteTask(this.parseIndexFromString(splitCommand));
            break;

        case "done":
            output = tasks.markTaskAsDone(this.parseIndexFromString(splitCommand));
            break;

        case "event":
            output = this.parseEvent(splitCommand, tasks);
            break;

        case "find":
            output = tasks.findTasks(splitCommand[1]);
            break;

        case "list":
            output = tasks.listAllTasks();
            break;

        case "todo":
            output = this.parseToDo(splitCommand, tasks);
            break;

        default:
            throw new DukeException("Oops! I am sorry but I don't understand what that means");
        }

        assert output != null;
        assert !output.isEmpty();
        return output;
    }

    private String parseDeadline(String[] splitCommand, TaskList tasks) throws DukeException {
        if (splitCommand.length < 2) {
            throw new DukeException("Description of deadline cannot be empty");
        }

        int byIndex = IntStream.range(0, splitCommand.length)
                .filter(i -> splitCommand[i].equals("by"))
                .findFirst()
                .orElse(-1);

        if (byIndex == -1 || (byIndex + 3 > splitCommand.length)) {
            throw new DukeException("Date and time of deadline cannot be empty");
        }

        String deadlineDescription = String.join(" ",
                Arrays.copyOfRange(splitCommand, 1, splitCommand.length - 3));
        String date = splitCommand[splitCommand.length - 2];
        String time = splitCommand[splitCommand.length - 1];

        try {
            LocalDate parsedDate = LocalDate.parse(date, Parser.DATE_INPUT_FORMAT);
            LocalTime parsedTime = LocalTime.parse(time.substring(0, 2) + ":" + time.substring(2));
            String formattedDate = parsedDate.format(Parser.DATE_OUTPUT_FORMAT);
            String formattedTime = parsedTime.format(Parser.TIME_OUTPUT_FORMAT);
            return tasks.addNewDeadline(deadlineDescription, formattedDate, formattedTime);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please input the date and time in a valid format e.g. by 20/02/2020 1600");
        }
    }

    private String parseEvent(String[] splitCommand, TaskList tasks) throws DukeException {
        if (splitCommand.length < 2) {
            throw new DukeException("Description of event cannot be empty");
        }

        int atIndex = IntStream.range(0, splitCommand.length)
                .filter(i -> splitCommand[i].equals("at"))
                .findFirst()
                .orElse(-1);

        if (atIndex == -1 || (atIndex + 3 > splitCommand.length)) {
            throw new DukeException("Date, start and end time of event cannot be empty");
        }

        String eventDescription = String.join(" ", Arrays.copyOfRange(splitCommand, 1, splitCommand.length - 3));
        String date = splitCommand[splitCommand.length - 2];
        String time = splitCommand[splitCommand.length - 1];

        try {
            LocalDate parsedDate = LocalDate.parse(date, Parser.DATE_INPUT_FORMAT);
            String[] startEndTime = time.split("-");
            if (startEndTime.length < 2) {
                throw new DukeException("Please enter the end timing of the event e.g. at 20/02/2020 1600-1800");
            }
            LocalTime startTime = LocalTime.parse(startEndTime[0].substring(0, 2) + ":" + startEndTime[0].substring(2));
            LocalTime endTime = LocalTime.parse(startEndTime[1].substring(0, 2) + ":" + startEndTime[1].substring(2));

            String formattedDate = parsedDate.format(Parser.DATE_OUTPUT_FORMAT);
            String formattedStartTime = startTime.format(Parser.TIME_OUTPUT_FORMAT);
            String formattedEndTime = endTime.format(Parser.TIME_OUTPUT_FORMAT);
            return tasks.addNewEvent(eventDescription, formattedDate, formattedStartTime, formattedEndTime);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please input the date, start and end timing in a valid format "
                    + "e.g. at 20/02/2020 1600-1800");
        }
    }

    private int parseIndexFromString(String[] splitCommand) throws DukeException {
        if (splitCommand.length < 2) {
            throw new DukeException("Index of the task cannot be empty");
        }

        try {
            int index = Integer.parseInt(splitCommand[1]);
            return index;
        } catch (NumberFormatException e) {
            throw new DukeException("Index must be a number");
        }
    }

    private String parseToDo(String[] splitCommand, TaskList tasks) throws DukeException {
        String description = String.join(" ",
                Arrays.copyOfRange(splitCommand, 1, splitCommand.length));

        if (description.isEmpty()) {
            throw new DukeException("Description of ToDo cannot be empty");
        }

        return tasks.addNewToDo(description);
    }
}
