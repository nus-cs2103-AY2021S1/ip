import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    protected static final String LINE_BREAK = "    ____________________________________________________________\n";
    protected static final String PRESPACING = "     ";
    protected static final String OPENING_MESSAGE = "Hello! I'm Duke\n" + PRESPACING + "What can I do for you?";
    protected static final String CLOSING_MESSAGE = "Bye. Hope to see you again soon!";
    protected static final String MARKED_MESSAGE = "Nice! I've marked this task as done: ";
    protected static final String ADDED_MESSAGE = "Got it. I've added this task: ";

    protected static final String CLOSING_STRING = "bye";
    protected static final String LIST_STRING = "list";
    protected static final String DONE_STRING = "done";

    protected static final String TO_DO = "todo";
    protected static final String DEADLINE = "deadline";
    protected static final String EVENT = "event";

    protected static final List<Task> taskRecords = new ArrayList<Task>();

    public static void main(String[] args) {
        System.out.println(getOpeningText());

        Scanner scanner = new Scanner(System.in);

        String userInput = "";

        while (!userInput.equals(CLOSING_STRING)) {
            userInput = scanner.nextLine();
            String firstWord = userInput.split(" ")[0];
            if (userInput.equals(CLOSING_STRING)) {
                System.out.println(getClosingText());
            } else if (userInput.equals(LIST_STRING)) {
                System.out.println(processString(getListString()));
            } else if (firstWord.equals(DONE_STRING)) {
                int index = Integer.parseInt(userInput.substring(5));
                System.out.println(registerTaskDone(index));
            } else {
                taskRecords.add(new Task(userInput));
                System.out.println(processString(createAddedString(userInput)));
            }

        }


    }

    protected static String registerTaskDone(int taskNumber) {
        Task taskDone = taskRecords.get(taskNumber - 1);
        taskDone.markDone();
        return processString(MARKED_MESSAGE + '\n' + PRESPACING + "   " + taskDone);
    }

    protected static String getListString() {
        StringBuilder builder = new StringBuilder();
        int count = 1;
        boolean first = true;
        for (Task task : taskRecords) {
            if (!first) {
                builder.append('\n' + PRESPACING);
            } else first = false;
            builder.append(count++ + "." + task.toString());
        }
        return builder.toString();
    }

    protected static String addTask(String taskDescription) {
        String[] details = taskDescription.split(" ", 2);
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
                addedTask = new Task(taskDescription);
        }

        taskRecords.add(addedTask);
        return processString(
                ADDED_MESSAGE + '\n' +
                        PRESPACING + "   " + addedTask + '\n' +
                        PRESPACING + createTaskNumberCountMessage(taskRecords.size())
        );

    }

    protected static String createTaskNumberCountMessage(int count) {
        return "Now you have " + count + " tasks in the list.";
    }

    protected static String createAddedString(String addedString) {
        return "added: " + addedString;
    }


    protected static String processString(String string) {
        return LINE_BREAK  + PRESPACING + string + '\n' + LINE_BREAK;
    }

    protected static String getOpeningText() {
        return processString(OPENING_MESSAGE);
    }

    protected static String getClosingText() {
        return processString(CLOSING_MESSAGE);
    }



}
