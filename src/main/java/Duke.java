import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Duke {
    private static final Divider divider = new Divider();
    private static final Data data = new Data();
    private static final TaskList list = data.load();

    public enum Command {
        BYE, LIST, DONE, TASK, DELETE, RETRIEVE, OTHERS
    }

    public static void main(String[] args) {
        sendGreeting();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            try {
                String input = scanner.nextLine();
                handleCommands(getCommand(input), input);
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

    public static Command getCommand(String command) {
        if (command.equals("bye")) {
            return Command.BYE;
        } else if (command.equals("list")) {
            return Command.LIST;
        } else if (command.startsWith("done")) {
            return Command.DONE;
        } else if (command.startsWith("todo")) {
            return Command.TASK;
        } else if (command.startsWith("deadline")) {
            return Command.TASK;
        } else if (command.startsWith("event")) {
            return Command.TASK;
        } else if (command.startsWith("delete")) {
            return Command.DELETE;
        } else if (command.startsWith("retrieve")) {
            return Command.RETRIEVE;
        } else {
            return Command.OTHERS;
        }
    }

    public static void handleCommands(Command command, String input) throws DukeException {
        switch (command) {
            case BYE:
                handleByeCommand();
                break;
            case LIST:
                handleListCommand();
                break;
            case DONE:
                handleDoneCommand(input);
                break;
            case TASK:
                handleTaskCommand(input);
                break;
            case DELETE:
                handleDeleteCommand(input);
                break;
            case RETRIEVE:
                handleRetrieveCommand(input);
                break;
            default:
                throw new InvalidCommandException();
        }
    }

    public static void handleByeCommand() {
        System.out.println(divider.wrapInDivider("Bye. Hope to see you again soon!"));
        data.save(list);
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
                data.save(list);
                System.out.println(divider.wrapInDivider("Nice! I've marked this task as done:\n\t   " +
                        list.getTask(taskNumber)));
            }
        }
    }

    public static void handleTaskCommand(String command) throws DukeException {
        try {
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
            data.save(list);
            int noOfTask = list.getNumberOfTask();
            String taskDescription = getTaskDescription(noOfTask);
            System.out.println(divider.wrapInDivider("Got it. I've added this task: \n\t   " +
                    task + "\n\t " +
                    "Now you have " +
                    taskDescription +
                    " in the list."));
            } catch (DateTimeParseException e) {
                throw new InvalidTaskDateTimeException();
        }
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
                data.save(list);
            }
        }
    }

    private static void handleRetrieveCommand(String command) throws DukeException{
        try {
            StringBuffer sb = new StringBuffer();
            int index = 1;
            String[] split = command.split(" ");
            if (split.length == 1) {
                throw new NoTaskDateTimeException("OOPS!!! Please enter a date.");
            } else {
                LocalDate date = LocalDate.parse(split[1], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                sb.append(String.format("Here are the deadlines and events happening on %s:\n\t ",
                        date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))));
                boolean hasTasks = false;
                for (Task t : list.getList()) {
                    if (t instanceof Deadline) {
                        Deadline d = (Deadline) t;
                        if (d.getDateTime().toLocalDate().isEqual(date)) {
                            hasTasks = true;
                            sb.append(index).append(".").append(t.toString()).append("\n\t ");
                            index++;
                        }
                    }
                    if (t instanceof Event) {
                        Event e = (Event) t;
                        if (e.getDateTime().toLocalDate().isEqual(date)) {
                            hasTasks = true;
                            sb.append(index).append(".").append(t.toString()).append("\n\t ");
                            index++;
                        }
                    }
                }
                if (hasTasks) {
                    System.out.println(divider.wrapInDivider(sb.delete(sb.length() - 3, sb.length() - 1).toString()));
                } else {
                    System.out.println(divider
                            .wrapInDivider(String.format("You do not have any deadlines or events happening on %s! :)",
                                    date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")))));
                }
            }
        } catch (DateTimeParseException e) {
            throw new InvalidTaskDateException();
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