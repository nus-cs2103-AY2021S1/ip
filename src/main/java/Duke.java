import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    // Attributes
    private static final String NAME = "DUKE";
    private static final String INTRODUCTION = String.format(
            "Hello! I'm %s\n" +
                    "I was created for the module CS2103T\n" +
                    "What can I do for you?", NAME);
    private static final String EXIT = "Bye. Hope to see you again soon!";
    private static final String LINE = "    ____________________________________________________________";

    private static final List<Task> lst = new ArrayList<>();

    // Scanner
    private static final Scanner sc = new Scanner(System.in);

    // Greeting
    private static void greeting() {
        System.out.println(wrapText(INTRODUCTION));
    }

    // Wrap text in lines
    private static String wrapText(String text) {
        return LINE + "\n    " + text.replaceAll("\n", "\n    ") + "\n" + LINE + "\n";
    }

    // Prints list
    private static void printList() {
        StringBuilder sb = new StringBuilder();
        int sizeOfList = lst.size();
        sb.append("Here are the tasks in your list:");

        for (int i = 0; i < sizeOfList; i++) {
            int number = i + 1;
            String text = lst.get(i).toString();
            sb.append(String.format("\n %s. %s", number, text));
        }

        System.out.println(wrapText(sb.toString()));
    }

    private static void createNewTask(Task newTask) {
        lst.add(newTask);
        int newSize = lst.size();
        String todoText = String.format("Got it. I've added this task:\n" +
                " %s\n" +
                "Now you have %s tasks in the list", newTask, newSize);
        System.out.println(wrapText(todoText));
    }

    // Exit
    private static void exit() {
        System.out.println(wrapText(EXIT));
    }

    public static void main(String[] args) {
        greeting();
        while (sc.hasNext()) {
            try {

                // Input Handling
                String input = sc.nextLine();
                String[] words = input.split(" ", 2);
                String firstWord = words[0];
                String remaining = words.length > 1 ? words[1] : "";

                switch (firstWord) {
                    case "bye":
                        exit();
                        break;
                    case "list":
                        printList();
                        break;
                    case "delete": {
                        if (remaining.equals("")) {
                            throw new EmptyBodyException("task number", "task");
                        }
                        int taskNumber = Integer.parseInt(remaining);
                        try {
                            Task task = lst.get(taskNumber - 1);
                            lst.remove(taskNumber - 1);
                            String deleteText = String.format("Okay. I've removed this task:\n" +
                                    " %s", task);
                            System.out.println(wrapText(deleteText));
                            break;
                        } catch (IndexOutOfBoundsException e) {
                            throw new IndexOutOfBoundsDukeException(taskNumber, lst.size(), "task");
                        }
                    }
                    case "done":
                        if (remaining.equals("")) {
                            throw new EmptyBodyException("task number", "task");
                        }
                        int taskNumber = Integer.parseInt(remaining);
                        try {
                            Task task = lst.get(taskNumber - 1);
                            task.markAsDone();
                            String doneText = String.format("Nice! I've marked this task as done:\n" +
                                    " %s", task);
                            System.out.println(wrapText(doneText));
                            break;
                        } catch (IndexOutOfBoundsException e) {
                            throw new IndexOutOfBoundsDukeException(taskNumber, lst.size(), "task");
                        }
                    case "todo":
                        Task newTodo = new Todo(remaining);
                        createNewTask(newTodo);
                        break;
                    case "deadline": {
                        String[] text = remaining.split(" /by ");
                        String description = text[0];
                        if (text.length <= 1) {
                            throw new EmptyBodyException("deadline", "deadline");
                        }
                        try {
                            LocalDate deadline = LocalDate.parse(text[1]);
                            Task newDeadline = new Deadline(description, deadline);
                            createNewTask(newDeadline);
                            break;
                        } catch (DateTimeParseException e) {
                            throw new UnknownInputException(text[1]);
                        }
                    }
                    case "event": {
                        String[] text = remaining.split(" /at ");
                        String description = text[0];
                        if (text.length <= 1) {
                            throw new EmptyBodyException("date and time", "event");
                        }
                        String dateTime = text[1];
                        Task newEvent = new Event(description, dateTime);
                        createNewTask(newEvent);
                        break;
                    }
                    default:
                        throw new UnknownInputException(input);
                }
            } catch (DukeException e) {
                System.out.println(wrapText(e.toString()));
            }
        }
    }
}