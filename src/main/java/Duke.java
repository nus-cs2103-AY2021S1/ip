import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Duke is a Personal Assistant Chatbot that helps a user keep track of various things.
 */
public class Duke {

    protected static final String LINE_BREAK = "    ____________________________________________________________\n";
    protected static final String PRESPACING = "     ";
    protected static final String OPENING_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    protected static final String CLOSING_MESSAGE = "Bye. Hope to see you again soon!";
    protected static final String MARKED_MESSAGE = "Nice! I've marked this task as done: ";
    protected static final String ADDED_MESSAGE = "Got it. I've added this task: ";
    protected static final String REMOVED_MESSAGE = "Noted. I've removed this task: ";
    protected static final String LIST_HEADER = "Here are the tasks in your list: ";

    protected static final String CLOSING_STRING = "bye";
    protected static final String LIST_STRING = "list";
    protected static final String DONE_STRING = "done";
    protected static final String DELETE_STRING = "delete";

    protected static final String TO_DO = "todo";
    protected static final String DEADLINE = "deadline";
    protected static final String EVENT = "event";

    protected static final List<String> validTaskTypes;
    static {
        validTaskTypes = new ArrayList<>();
        validTaskTypes.add(TO_DO);
        validTaskTypes.add(DEADLINE);
        validTaskTypes.add(EVENT);
    }

    protected static final List<Task> taskRecords = new ArrayList<Task>();

    public static void main(String[] args) {
        System.out.println(getOpeningText());

        Scanner scanner = new Scanner(System.in);

        String userInput = "";

        while (!userInput.equals(CLOSING_STRING)) {
            userInput = scanner.nextLine();
            String firstWord = userInput.split(" ")[0];
            try {
                if (userInput.equals(CLOSING_STRING)) {
                    System.out.println(getClosingText());
                } else if (userInput.equals(LIST_STRING)) {
                    System.out.println(getListString());
                } else if (firstWord.equals(DONE_STRING)) {
                    System.out.println(registerTaskDone(userInput));
                } else if (firstWord.equals(DELETE_STRING)) {
                    System.out.println(registerDeleteTask(userInput));
                } else {
                    System.out.println(addTask(userInput));
                }

            } catch (DukeException e) {
                System.out.println(processErrorString(e));
            }
        }


    }

    protected static String registerDeleteTask(String userInput) {
        String[] details = userInput.split(" ", 2);
        if (details.length == 1) {
            throw new DukeException("Please specify a task to delete!");
        }
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(details[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid number input!");
        }
        if (taskNumber > taskRecords.size()) {
            throw new DukeException("No such task!");
        }
        Task taskRemoved = taskRecords.remove(taskNumber - 1);;
        return processString(REMOVED_MESSAGE + '\n'
                + "   " + taskRemoved + '\n'
                + createTaskNumberCountMessage(taskRecords.size()));

    }

    protected static String processErrorString(DukeException e) {
        String message = e.getMessage();
        return processString(message);
    }

    protected static String registerTaskDone(String userInput) {
        String[] details = userInput.split(" ", 2);
        if (details.length == 1) {
            throw new DukeException("Please specify a task to complete!");
        }
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(details[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid number input!");
        }
        if (taskNumber > taskRecords.size()) {
            throw new DukeException("No such task!");
        }
        Task taskDone = taskRecords.get(taskNumber - 1);
        taskDone.markDone();
        return processString(MARKED_MESSAGE + '\n' + "   " + taskDone);
    }

    protected static String getListString() {
        StringBuilder builder = new StringBuilder();
        builder.append(LIST_HEADER);
        int count = 1;
        for (Task task : taskRecords) {
            builder.append('\n');
            builder.append(count++ + "." + task.toString());
        }
        return processString(builder.toString());
    }

    protected static String addTask(String taskDescription) {
        String[] details = taskDescription.split(" ", 2);
        if (details.length == 1 && validTaskTypes.contains(details[0])) {
            throw new DukeException("The description of a " + details[0] + " cannot be empty.");
        }
        Task addedTask;
        switch(details[0]) {
            case TO_DO:
                addedTask = new ToDos(details[1]);
                break;
            case DEADLINE:
                addedTask = new Deadlines(details[1]);
                break;
            case EVENT:
                addedTask = new Events(details[1]);
                break;
            default:
                throw new DukeException("Not a valid command!");
        }

        taskRecords.add(addedTask);
        return processString(
                ADDED_MESSAGE + '\n'
                        + "   " + addedTask + '\n'
                        + createTaskNumberCountMessage(taskRecords.size())
        );

    }

    protected static String createTaskNumberCountMessage(int count) {
        return "Now you have " + count + " tasks in the list.";
    }


    protected static String processString(String string) {
        return LINE_BREAK  + PRESPACING
                + string.replaceAll("\n", '\n' + PRESPACING)
                + '\n' + LINE_BREAK;
    }

    protected static String getOpeningText() {
        return processString(OPENING_MESSAGE);
    }

    protected static String getClosingText() {
        return processString(CLOSING_MESSAGE);
    }



}
