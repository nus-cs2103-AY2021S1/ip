import java.util.Scanner;

public class Duke {
    private final Divider divider;
    private final Scanner scanner;
    private final TaskList list;

    public Duke() {
        divider = new Divider();
        scanner = new Scanner(System.in);
        list = new TaskList();
    }

    public void sendGreeting() {
        System.out.println(divider.wrapInDivider("Hello! I'm Duke\n What can I do for you?"));
    }

    public void handleTaskCommand(String command, String commandWord) {
        int lenOfCommandWord = commandWord.length() + 1;
        if (command.length() > lenOfCommandWord) { // check if task description exist
            String content = command.substring(lenOfCommandWord);
            Task task = null;
            if (commandWord.equals("todo")) {
                task = new Todo(content);
            } else if (commandWord.equals("deadline")) {
                String[] deadline = content.split("(\\s/by\\s)", 2);
                if (deadline.length == 2) {
                    String description = deadline[0];
                    String by = deadline[1];
                    task = new Deadline(description, by);
                }
            } else {
                String[] event = content.split("(\\s/at\\s)", 2);
                if (event.length == 2) {
                    String description = event[0];
                    String at = event[1];
                    task = new Event(description, at);
                }
            }
            if (task != null) {
                list.addTask(task);
                int noOfTask = list.getNumberOfTask();
                String taskDescription = "";
                if (noOfTask > 1) {
                    taskDescription = noOfTask + " tasks";
                } else {
                    taskDescription = noOfTask + " task";
                }
                System.out.println(divider.wrapInDivider("Got it. I've added this task: \n   " +
                        task + "\n " +
                        "Now you have " +
                        taskDescription +
                        " in the list."));
            }
        }
    }

    public void receiveCommands() {
        while(scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println(divider.wrapInDivider("Bye. Hope to see you again soon!"));
                scanner.close();
                System.exit(0);
            } else if (command.equals("list")) {
                if (list.getNumberOfTask() == 0) {
                    System.out.println("You have no task currently!");
                } else {
                    System.out.println(divider.wrapInDivider(list.toString()));
                }
            } else if (command.startsWith("done")) {
                String[] split = command.split("\\s+");
                if (split.length == 2 && split[1].matches("[0-9]+")) {
                    int taskNumber = Integer.parseInt(split[1]) - 1;
                    list.doTask(taskNumber);
                    System.out.println(divider.wrapInDivider("Nice! I've marked this task as done:\n   " +
                            list.getTask(taskNumber)));
                }
            } else if (command.startsWith("todo")) {
                handleTaskCommand(command, "todo");
            } else if (command.startsWith("deadline")) {
                handleTaskCommand(command, "deadline");
            } else if (command.startsWith("event")) {
                handleTaskCommand(command, "event");
            } else {

            }
        }
    }

}
