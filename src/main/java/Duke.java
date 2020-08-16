import java.util.Scanner;

public class Duke {
    private static final Divider divider = new Divider();
    private static final TaskList list = new TaskList();

    public static void main(String[] args) {
        sendGreeting();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            handleCommands(command);
        }
    }

    public static void sendGreeting() {
        String logo = " ____        _        \n\t "
                + "|  _ \\ _   _| | _____ \n\t "
                + "| | | | | | | |/ / _ \\\n\t "
                + "| |_| | |_| |   <  __/\n\t "
                + "|____/ \\__,_|_|\\_\\___|\n\t ";
        System.out.println(divider.wrapInDivider(logo + "\n\t Hello! I'm Duke\n\t What can I do for you?"));
    }

    public static void handleCommands(String command) {
        if (command.equals("bye")) {
            handleByeCommand();
        } else if (command.equals("list")) {
            handleListCommand();
        } else if (command.startsWith("done")) {
            handleDoneCommand(command);
        } else if (command.startsWith("todo")) {
            handleTaskCommand(command, "todo");
        } else if (command.startsWith("deadline")) {
            handleTaskCommand(command, "deadline");
        } else if (command.startsWith("event")) {
            handleTaskCommand(command, "event");
        } else {
        }
    }

    public static void handleByeCommand() {
        System.out.println(divider.wrapInDivider("Bye. Hope to see you again soon!"));
        System.exit(0);
    }

    public static void handleListCommand() {
        if (list.getNumberOfTask() == 0) {
            System.out.println(divider.wrapInDivider("You have no task currently!"));
        } else {
            System.out.println(divider.wrapInDivider(list.toString()));
        }
    }

    private static void handleDoneCommand(String command) {
        String[] split = command.split("\\s+");
        if (split.length == 2 && split[1].matches("[0-9]+")) {
            int taskNumber = Integer.parseInt(split[1]) - 1;
            if (taskNumber >= 0 && taskNumber < list.getNumberOfTask()) {
                list.doTask(taskNumber);
                System.out.println(divider.wrapInDivider("Nice! I've marked this task as done:\n\t   " +
                        list.getTask(taskNumber)));
            }
        }
    }

    public static void handleTaskCommand(String command, String commandWord) {
        int lenOfCommandWord = commandWord.length() + 1;
        if (command.length() > lenOfCommandWord) { // check if task description exist
            String content = command.substring(lenOfCommandWord);
            Task task = null;
            if (commandWord.equals("todo")) {
                task = new Todo(content);
            } else if (commandWord.equals("deadline")) {
                String[] deadline = content.split("(\\s/by\\s)", 2); // check of date/time exist
                if (deadline.length == 2) {
                    String description = deadline[0];
                    String by = deadline[1];
                    task = new Deadline(description, by);
                }
            } else {
                String[] event = content.split("(\\s/at\\s)", 2); // check if duration exist
                if (event.length == 2) {
                    String description = event[0];
                    String at = event[1];
                    task = new Event(description, at);
                }
            }
            if (task != null) { // print "task" if there is 1 task, else print "tasks" if there is more than 1
                list.addTask(task);
                int noOfTask = list.getNumberOfTask();
                String taskDescription = "";
                if (noOfTask > 1) {
                    taskDescription = noOfTask + " tasks";
                } else {
                    taskDescription = noOfTask + " task";
                }
                System.out.println(divider.wrapInDivider("Got it. I've added this task: \n\t   " +
                        task + "\n\t " +
                        "Now you have " +
                        taskDescription +
                        " in the list."));
            }
        }
    }
}