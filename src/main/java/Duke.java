import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

class Task {
    private String message;
    private boolean isDone;

    Task(String message) {
        this.message = message;
        this.isDone = false;
    }

    public String getMessage() {
        return message;
    }

    public String getStatusIcon() {
        return "[" + (isDone ? "\u2713" : "\u2718") + "] ";
    }

    public void setDone() {
        this.isDone = true;
    }

}

public class Duke {
    // constant SPACE and LINE for format purposes
    public static String SPACE = "     ";
    public static String LINE = "____________________________________________________________\n";
    // add outer frame lines
    public static String format(String input) {
        return SPACE + LINE +
               SPACE + " " + input + "\n" +
               SPACE + LINE;
    }

    public static void main(String[] args) {
        // greeting and exit messages strings
        // list and mark strings
        String messageHello = "Hello! I'm Duke\n" + SPACE + " " + "What can I do for you?";
        String messageBye = "Bye. Hope to see you again soon!";
        String messageList = "Here are the tasks in your list:\n";
        String messageMarked = "Nice! I've marked this task as done:\n";

        // set up scanner
        Scanner scanner = new Scanner(System.in);

        // set up empty list of things to do
        List<Task> list = new ArrayList<>();

        // start to serve
        System.out.println(format(messageHello));

        // continue if have more commands (that are not "bye")
        // echo the command or say bye
        while (scanner.hasNextLine()) {
            String currentCommand = scanner.nextLine();
            if (currentCommand.equals("bye")) {
                System.out.println(format(messageBye));
                scanner.close();
                break;
            } else if (currentCommand.equals("list")) {
                System.out.print(SPACE + LINE);
                System.out.print(SPACE + messageList);
                int counter = 1;
                for (Task task : list) {
                    System.out.println(SPACE + " " + counter + "." + task.getStatusIcon()
                            + task.getMessage());
                    counter++;
                }
                System.out.println(SPACE + LINE);
            } else if (currentCommand.split(" ")[0].equals("done")) {
                int index = Integer.parseInt(currentCommand.split(" ")[1]) - 1;
                Task task = list.get(index);
                task.setDone();
                System.out.println(format(messageMarked + SPACE + "   "
                        +task.getStatusIcon() + task.getMessage()));
            } else {
                list.add(new Task(currentCommand));
                System.out.println(format("added: " + currentCommand));
            }
        }
    }
}