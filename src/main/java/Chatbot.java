import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Chatbot {

    private final String DIVIDER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private final String INDENT = "        ";
    private final ArrayList<Task> tasks;

    public Chatbot() {
        this.tasks = new ArrayList<>();
    }

    public void start() {

        Scanner scanner = new Scanner(System.in);
        System.out.println(INDENT + "What can I do for you delightful human?\n" + DIVIDER);

        while(scanner.hasNext()) {

            String input = scanner.nextLine();
            System.out.println(DIVIDER);

            try {
                if (input.equals("bye")) {
                    System.out.println(INDENT + "Guess its time for us to part ways\n"
                            + INDENT + "Thanks for the memories\n" + INDENT + ":`(");
                    System.out.println(DIVIDER);
                    break;
                } else if (input.equals("list")) {
                    printTasks();
                } else if (input.startsWith("done")) {
                    markAsDone(input);
                } else if (input.startsWith("delete")) {
                    deleteTask(input);
                } else {

                    Task currTask = null;

                    if (input.startsWith("todo")) {
                        currTask = createTodo(input);
                    } else if (input.startsWith("deadline")) {
                        currTask = createDeadline(input);
                    } else if (input.startsWith("event")) {
                        currTask = createEvent(input);
                    } else {
                        invalidCommand();
                    }

                    if (currTask != null) {
                        addTask(currTask);
                    }
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                System.out.println(DIVIDER);
            }
        }
    }

    private void printTasks() {
        System.out.println(INDENT + "Here are your tasks for today:");
        for (int i = 0; i < tasks.size(); i++) {
            Task curr = tasks.get(i);
            System.out.println(INDENT + (i + 1) + "." + curr);
        }
        System.out.println(DIVIDER);
    }

    private void markAsDone(String input) throws DukeException {
        if (input.equals("done")) { // Input does not contain which task to mark as done
            throw new DukeException(INDENT + "Sorry sir you will have to try again and this time " +
                    "tell me which task you want me to mark as done");
        }
        int index = Integer.parseInt(input.split(" ")[1]);
        Task curr = tasks.get(index - 1);
        curr.markAsDone();
        System.out.println(INDENT + "Good job! I've marked this task as done:");
        System.out.println(INDENT + "  " + curr);
        System.out.println(DIVIDER);
    }

    private void deleteTask(String input) throws DukeException {
        if (input.equals("delete")) { // Input does not contain which task to mark as done
            throw new DukeException(INDENT + "Sorry sir you will have to try again and this time " +
                    "tell me which task you want me to delete");
        }
        int index = Integer.parseInt(input.split(" ")[1]);
        Task deletedTask = tasks.remove(index - 1); // Subtract 1 due to offset
        System.out.println(INDENT + "Of course sir. I have removed this task:\n" +
                INDENT + "  " + deletedTask);
        System.out.println(INDENT + "Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    private Task createTodo(String input) throws DukeException {
        // Input does not contain the required keyword
        if (input.equals("todo") || input.substring(5).isEmpty()) {
            throw new DukeException(INDENT + "My apologies sir but the description of todo " +
                    "cannot be empty :(");
        }
        return new Todo(input.substring(5));
    }

    private Task createDeadline(String input) throws DukeException {
        // Input does not contain the required keyword
        if (input.equals("deadline") || !input.substring(9).contains("/by")) {
            throw new DukeException(INDENT + "My apologies sir but you will have to use the correct " +
                    "format to create a deadline :(");
        }

        String[] splicedInput = input.substring(9).split(" /by ");
        LocalDate date = convertDate(splicedInput[1]);

        if (date == null) {
            return new Deadline(splicedInput[0], splicedInput[1]);
        }

        return new Deadline(splicedInput[0], date);
    }

    private Task createEvent(String input) throws DukeException {
        // Input does not contain the required keyword
        if (input.equals("event") || !input.substring(6).contains("/at")) {
            throw new DukeException(INDENT + "My apologies sir but you will have to use the correct " +
                    "format to create a event :(");
        }

        String[] splicedInput = input.substring(6).split(" /at ");
        LocalDate date = convertDate(splicedInput[1]);

        if (date == null) {
            return new Event(splicedInput[0], splicedInput[1]);
        }

        return new Event(splicedInput[0], date);
    }

    private LocalDate convertDate(String dateString) {
        LocalDate date = null;
        try {
            date = LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            date = null;
        }
        return date;
    }

    private void invalidCommand() throws DukeException {
        throw new DukeException(INDENT + "Sorry sir but I don't know what you mean :(");
    }

    private void addTask(Task curr) {
        tasks.add(curr);
        System.out.println(INDENT + "Affirmative. I've added this task:\n  " +
                INDENT + curr);
        System.out.println(INDENT + "Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(DIVIDER);
    }
}
