import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    protected static final String LINE_BREAK = "    ____________________________________________________________\n";
    protected static final String PRESPACING = "     ";
    protected static final String OPENING_MESSAGE = "Hello! I'm Duke\n" + PRESPACING + "What can I do for you?";
    protected static final String CLOSING_MESSAGE = "Bye. Hope to see you again soon!";
    protected static final String MARKED_MESSAGE = "Nice! I've marked this task as done: ";

    protected static final String CLOSING_STRING = "bye";
    protected static final String LIST_STRING = "list";
    protected static final String DONE_STRING = "done";

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
