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

    public String getTypeLetter() {
        // dummy value
        return "";
    }
}

class Todo extends Task {
    Todo(String message) {
        super(message);
    }

    @Override
    public String getTypeLetter() {
        // dummy value
        return "[T]";
    }
}

class Event extends Task {
    Event(String message) {
        super(message);
    }

    @Override
    public String getTypeLetter() {
        // dummy value
        return "[E]";
    }
}

class Deadline extends Task {
    Deadline(String message) {
        super(message);
    }

    @Override
    public String getTypeLetter() {
        // dummy value
        return "[D]";
    }
}

class Convert {
    static String at(String s) {
        String first = s.split("/at")[0];
        String second = s.split("/at")[1];
        return first + "(at:" + second + ")";
    }

    static String by(String s) {
        String first = s.split("/by")[0];
        String second = s.split("/by")[1];
        return first + "(by:" + second + ")";
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
        final String messageHello = "Hello! I'm Duke\n" + SPACE + " " + "What can I do for you?";
        final String messageBye = "Bye. Hope to see you again soon!";
        final String messageList = "Here are the task(s) in your list:\n";
        final String messageMarked = "Nice! I've marked this task as done:\n";
        final String messageAdded = "Got it. I've added this task:\n";

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
            String taskType = currentCommand.split(" ")[0];

            if (currentCommand.equals("bye")) {
                System.out.println(format(messageBye));
                scanner.close();
                break;
            } else if (currentCommand.equals("list")) {
                System.out.print(SPACE + LINE);
                System.out.print(SPACE + messageList);
                int counter = 1;
                for (Task task : list) {
                    System.out.println(SPACE + " " + counter + "." + task.getTypeLetter()
                            + task.getStatusIcon() + task.getMessage());
                    counter++;
                }
                System.out.println(SPACE + LINE);
            } else {
                try {
                    String priorCommand = currentCommand.split(" ")[0];
                    String extraCommand = currentCommand.split(" ", 2)[1];
                    if (priorCommand.equals("done")) {
                        int index = Integer.parseInt(extraCommand) - 1;
                        Task task = list.get(index);
                        task.setDone();
                        System.out.println(format(messageMarked + SPACE + "   "
                                + task.getTypeLetter() + task.getStatusIcon() + task.getMessage()));
                    } else {
                        Task task;
                        if (priorCommand.equals("deadline")) {
                            task = new Deadline(Convert.by(extraCommand));
                        } else if (priorCommand.equals("event")) {
                            task = new Event(Convert.at(extraCommand));
                        } else if (priorCommand.equals("todo")) {
                            task = new Todo(extraCommand);
                        } else {
                            // error
                            // throw exception
                            // dummy initialization
                            task = new Task("");
                        }

                        list.add(task);
                        System.out.println(format(messageAdded + SPACE + "   "
                                + task.getTypeLetter() + task.getStatusIcon() + task.getMessage()
                                + "\n " + SPACE + "Now you have " + list.size() + " task(s) in the list."));
                    }
                } catch (IndexOutOfBoundsException e) {
                    // handle exception
                }
            }
        }
    }
}