import java.util.Scanner;

public class Duke {
    private static final Divider divider = new Divider();
    private static final TaskList list = new TaskList();

    public static void main(String[] args) {
        sendGreeting();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            try {
                String command = scanner.nextLine();
                handleCommands(command);
            } catch (Exception e){
                System.out.println(divider.wrapInDivider(e.getMessage()));
            }
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

    public static void handleCommands(String command) throws DukeException {
        if (command.equals("bye")) {
            handleByeCommand();
        } else if (command.equals("list")) {
            handleListCommand();
        } else if (command.startsWith("done")) {
            handleDoneCommand(command);
        } else if (command.startsWith("todo")) {
            handleTaskCommand(command);
        } else if (command.startsWith("deadline")) {
            handleTaskCommand(command);
        } else if (command.startsWith("event")) {
            handleTaskCommand(command);
        } else if (command.startsWith("delete")) {
            handleDeleteCommand(command);
        } else {
            throw new InvalidCommandException();
        }
    }

    public static void handleByeCommand() {
        System.out.println(divider.wrapInDivider("Bye. Hope to see you again soon!"));
        System.exit(0);
    }

    public static void handleListCommand() throws NoTaskException {
        if (list.getNumberOfTask() <= 0) {
            throw new NoTaskException();
        } else {
            System.out.println(divider.wrapInDivider(list.toString()));
        }
    }

    private static void handleDoneCommand(String command) throws DukeException{
        String[] split = command.split(" ");
        if (split.length != 2 || !split[1].matches("[0-9]+")) {
            throw new InvalidDoneException();
        } else {
            int taskNumber = Integer.parseInt(split[1]) - 1;
            if (taskNumber < 0 || taskNumber >= list.getNumberOfTask()) {
                throw new InvalidTaskNumberException();
            } else {
                list.doTask(taskNumber);
                System.out.println(divider.wrapInDivider("Nice! I've marked this task as done:\n\t   " +
                        list.getTask(taskNumber)));
            }
        }
    }

    public static void handleTaskCommand(String command) throws DukeException {
        String[] splitCommand = command.split(" ", 2);
        String taskWord = splitCommand[0];
        Task task = null;
        if (taskWord.equals("todo")) {
            if (splitCommand.length != 2) {
                throw new NoTaskContentException("OOPS!!! The description of a todo cannot be empty.");
            } else {
                String description = splitCommand[1];
                task = new Todo(description);
            }
        } else if (taskWord.equals("deadline")) {
            if (splitCommand.length != 2) {
                throw new NoTaskContentException("OOPS!!! The description and date/time of a deadline cannot be empty.");
            } else {
                String content = splitCommand[1];
                String[] splitContent = content.split(" /by ", 2);
                if (splitContent.length != 2) {
                    throw new NoTaskDateTimeException("OOPS!!! The date/time of a deadline cannot be empty.");
                } else {
                    String description = splitContent[0];
                    String by = splitContent[1];
                    task = new Deadline(description, by);
                }
            }
        } else if (taskWord.equals("event")) {
            if (splitCommand.length != 2) {
                throw new NoTaskContentException("OOPS!!! The description and duration of an event cannot be empty.");
            } else {
                String content = splitCommand[1];
                String[] splitContent = content.split(" /at ", 2);
                if (splitContent.length != 2) {
                    throw new NoTaskDurationException("OOPS!!! The duration of an event cannot be empty.");
                } else {
                    String description = splitContent[0];
                    String at = splitContent[1];
                    task = new Event(description, at);
                }
            }
        } else {
            throw new InvalidCommandException();
        }

        list.addTask(task);
        int noOfTask = list.getNumberOfTask();
        String taskDescription = getTaskDescription(noOfTask);
        System.out.println(divider.wrapInDivider("Got it. I've added this task: \n\t   " +
                task + "\n\t " +
                "Now you have " +
                taskDescription +
                " in the list."));
    }

    private static void handleDeleteCommand(String command) throws DukeException {
        String[] split = command.split(" ");
        if (split.length != 2 || !split[1].matches("[0-9]+")) {
            throw new InvalidDeleteException();
        } else {
            int taskNumber = Integer.parseInt(split[1]) - 1;
            if (taskNumber < 0 || taskNumber >= list.getNumberOfTask()) {
                throw new InvalidTaskNumberException();
            } else {
                int noOfTask = list.getNumberOfTask() - 1;
                String taskDescription = getTaskDescription(noOfTask);
                System.out.println(divider.wrapInDivider("Noted. I've removed this task:\n\t   " +
                        list.getTask(taskNumber) + "\n\t " +
                        "Now you have " +
                        taskDescription +
                        " in the list."));
                list.removeTask(taskNumber);
            }
        }
    }

    // print "task" if there is 1 task, else print "tasks" if there is more than 1
    public static String getTaskDescription(int noOfTask) {
        String taskDescription = "";
        if (noOfTask > 1) {
            taskDescription = noOfTask + " tasks";
        } else {
            taskDescription = noOfTask + " task";
        }
        return taskDescription;
    }
}