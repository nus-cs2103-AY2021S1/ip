import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.*;

public class Alice {
    private static final String PROMPT = " > ";
    private static final String DIVIDER = "____________________________________________________________\n";
    private final ArrayList<Task> tasks;

    public Alice() {
        tasks = new ArrayList<>();
    }

    public boolean canContinue(String input) {
        String[] arr = input.split(" ", 2);
        try {
            Command cmd = Command.getCommandType(arr[0]);
            if (cmd == Command.BYE) {
                System.out.println(sayGoodbye());
                return false;
            }
            if (arr.length == 1) {
                System.out.print(frameOutput(processCommand(cmd, "")) + PROMPT);
                return true;
            }
            System.out.print(frameOutput(processCommand(cmd, arr[1])) + PROMPT);
            return true;
        } catch (AliceException ex) {
            System.out.print(frameOutput(ex.getMessage()) + PROMPT);
            return true;
        }
    }

    private String processCommand(Command cmd, String params) throws AliceException {
        if (cmd == Command.LIST && params.isBlank()) {
            // Display task list
            return getTaskList();
        } else if (cmd == Command.HELP) {
            // Get help
            return Command.getCommandList();
        } else if (cmd == Command.DONE) {
            // Mark as done
            return markTaskAsDone(params);
        } else if (cmd == Command.DELETE) {
            // Delete
            return deleteTask(params);
        } else if (cmd == Command.TODO) {
            // Add to-do
            return addTodo(params);
        } else if (cmd == Command.DEADLINE) {
            // Add deadline
            return addDeadline(params);
        } else if (cmd == Command.EVENT) {
            // Add event
            return addEvent(params);
        } else if (cmd == Command.EMPTY) {
            // Empty command
            throw new AliceException("Please give me something to do. T_T");
        } else {
            // Invalid command
            throw new AliceException("That command is not in my dictionary!");
        }
    }

    private String markTaskAsDone(String s_index) throws AliceException {
        try {
            int index = Integer.parseInt(s_index) - 1;
            tasks.get(index).markAsDone();
            return "Great work! I've marked this task as done:\n    " + tasks.get(index);
        } catch (NumberFormatException e) {
            throw new AliceException("That is not a valid number.");
        } catch (IndexOutOfBoundsException e) {
            throw new AliceException("That task number is not in the list.");
        }
    }

    private String deleteTask(String s_index) throws AliceException {
        try {
            int index = Integer.parseInt(s_index) - 1;
            Task removed = tasks.remove(index);
            return "Roger. I've removed this task from your list:\n    "
                    + removed
                    + "\nNow you have " + tasks.size() + " task in your list";
        } catch (NumberFormatException e) {
            throw new AliceException("That is not a valid number.");
        } catch (IndexOutOfBoundsException e) {
            throw new AliceException("That task number is not in the list.");
        }
    }

    private static final List<DateTimeFormatter> KNOWN_FORMATS = createDateFormats();

    private static List<DateTimeFormatter> createDateFormats() {
        // List of acceptable date time format with optional time/year
        List<String> knownPatterns = Arrays.asList(
                "d/M[/uuuu][ HHmm]", "d-M[-uuuu][ HHmm]",
                "M/d[/uuuu][ HHmm]", "M-d[-uuuu][ HHmm]",
                "uuuu/M/d[ HHmm]", "uuuu-M-d[ HHmm]",
                "d-MMM[-uuuu][ HHmm]", "d MMM[ HHmm]"
        );

        List<DateTimeFormatter> knownFormats = new ArrayList<>();
        for (int i = 0; i < knownPatterns.size(); i++) {
            knownFormats.add(
                    new DateTimeFormatterBuilder()
                            .appendPattern(knownPatterns.get(i))
                            .parseDefaulting(ChronoField.YEAR, LocalDateTime.now().getYear())
                            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                            .toFormatter()
            );
        }
        return knownFormats;
    }

    private LocalDateTime parseDateTime(String dateTimeString) throws AliceException {
        for (int i = 0; i < KNOWN_FORMATS.size(); i++) {
            try {
                return LocalDateTime.parse(dateTimeString, KNOWN_FORMATS.get(i));
            } catch (DateTimeParseException ex) {
                // ignore
            }
        }

        throw new AliceException("Invalid datetime! Please use 24h format for time");
    }

    private String addTodo(String details) throws AliceException {
        if (!details.isBlank()) {
            return addTask(new Todo(details));
        } else {
            throw new AliceException("The todo description cannot be left empty.");
        }
    }

    private String addDeadline(String details) throws AliceException {
        String[] desc_date = details.split(" /by ", 2);
        if (desc_date.length == 2 && !desc_date[1].isBlank()) {
            String description = desc_date[0];
            String dateTime = desc_date[1];
            LocalDateTime deadlineDt = parseDateTime(dateTime);
            return addTask(new Deadline(description, deadlineDt));
        } else if (details.isBlank()) {
            // Empty description
            throw new AliceException("The deadline description cannot be left empty.");
        } else if (details.endsWith("/by")) {
            // Empty date
            throw new AliceException("You cannot create an deadline without the date.");
        } else {
            // No /by marker
            throw new AliceException("I can't find the date. Did you forget to add '/by'?");
        }
    }

    private String addEvent(String details) throws AliceException {
        String[] desc_date = details.split(" /on ", 2);
        if (desc_date.length == 2 && !desc_date[1].isBlank()) {
            String description = desc_date[0];
            String dateTime = desc_date[1];

            LocalDateTime eventDateTime = parseDateTime(dateTime);
            return addTask(new Event(description, eventDateTime));
        } else if (details.isBlank()) {
            // Empty event description
            throw new AliceException("The event description cannot be left empty.");
        } else if (details.endsWith("/on")) {
            // Empty start-end time
            throw new AliceException("You cannot create an event without the start and end time.");
        } else {
            // No /on marker
            throw new AliceException("I can't find the date & time of the event. Did you forget to add '/on'?");
        }
    }

    private String addTask(Task t) {
        tasks.add(t);
        return "Roger. I've added the task to your list:\n    " + t
                + "\nNow you have " + tasks.size() + " task in your list";
    }

    private String frameOutput(String s) {
        return DIVIDER + s + "\n" + DIVIDER;
    }

    private String getTaskList() {
        if (tasks.isEmpty()) {
            return "You have no tasks at the moment.";
        }

        StringBuilder s = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            s.append(i + 1).append(". ").append(tasks.get(i));
            if (i < tasks.size() - 1) {
                s.append("\n");
            }
        }
        return s.toString();
    }

    public String greet() {
        String logo = " _____  _     _____ _____  _____\n" +
                "/  _  \\| |   |_   _/  __ \\|  ___|\n" +
                "| |_| || |     | | | /  \\/| |__\n" +
                "|  _  || |     | | | |    |  __|\n" +
                "| | | || |_____| |_| \\__/\\| |___\n" +
                "\\_| |_/\\_____/\\___/ \\____/\\____/\n";

        return logo +
                "\nHello! I'm Alice\n" +
                "How can I help you today?\n" +
                DIVIDER + PROMPT;
    }

    public String sayGoodbye() {
        return frameOutput("Goodbye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Alice alice = new Alice();
        System.out.print(alice.greet());

        Scanner sc = new Scanner(System.in);

        String input;
        boolean isExiting;
        while (sc.hasNextLine()) {
            input = sc.nextLine();
            isExiting = !alice.canContinue(input);
            if (isExiting) {
                return;
            }
        }
    }
}
