import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Duke {

    enum TypeOfTask {
        TODO,
        DEADLINE,
        EVENT
    }

    private static String formatReply(String text) {
        String line = "\t_______________________________________________________________";
        return line + "\n\t\t" + text.replaceAll("\\n", "\n\t\t") + "\n" + line;
    }

    private static String listTasks(ArrayList<Task> tasks) {
        String listOfTasks = "";
        for (int i = 0; i < tasks.size(); i++) {
            listOfTasks = listOfTasks + (i + 1) + ". " + tasks.get(i).toString()
                    + (i == tasks.size() - 1 ? "" : "\n");
        }
        return listOfTasks;
    }

    private static Task getTask(String command, TypeOfTask typeOfTask, Scanner input) throws MissingInfoException {
        String[] commandArray = input.nextLine().split(" ");
        String description = "";
        LocalDateTime timing = null;

        for (int i = 1; i < commandArray.length; i++) {
            if (commandArray[i].equals("/by")) {
                timing = getTiming(command, commandArray, i + 1);
                break;
            } else if (commandArray[i].equals("/at")) {
                timing = getTiming(command, commandArray, i + 1);
                break;
            }
            if (i == 1) {
                description = commandArray[i];
            } else {
                description = description + " " + commandArray[i];
            }
        }

        if (description.isEmpty()) {
            throw new MissingInfoException("OOPS!!! The description of a " + command + " cannot be empty.");
        }

        if ((typeOfTask.equals(TypeOfTask.DEADLINE) || typeOfTask.equals(TypeOfTask.EVENT)) && timing == null) {
            throw new MissingInfoException("OOPS!!! The date/time of a " + command + " cannot be empty.");
        }

        switch (typeOfTask) {
            case TODO:
                return new Todo(description);
            case DEADLINE:
                return new Deadline(description, timing);
            case EVENT:
                return new Event(description, timing);
            default:
                return new Task(description);
        }
    }

    private static LocalDateTime getTiming(String command, String[] commandArray, int index) throws MissingInfoException, DateTimeParseException {
        String timing = "";
        for (int i = index; i < commandArray.length; i++) {
            if (i == index) {
                timing = commandArray[i];
            } else {
                timing = timing + " " + commandArray[i];
            }
        }

        if (timing.isEmpty()) {
            throw new MissingInfoException("OOPS!!! The date/time of a " + command + " cannot be empty.");
        }
        try {
            return LocalDateTime.parse(timing);
        } catch (DateTimeParseException e) {
            throw e;
        }
    }

    public static void main(String[] args) {
        System.out.println(formatReply("Hello! I'm Duke\nWhat can I do for you?"));
        ArrayList<Task> taskList = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        while (true) {
            String command = input.next();
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                System.out.println(formatReply("Here are the tasks in your list:\n" + listTasks(taskList)));
            } else if (command.equals("done")) {
                try {
                    Task task = taskList.get(input.nextInt() - 1);
                    task.completeTask();
                    System.out.println(formatReply("This task has been marked as done:\n" + task.toString()));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(formatReply("OOPS!!! Task number is invalid."));
                } catch (InputMismatchException e) {
                    input.nextLine();
                    System.out.println(formatReply("OOPS!!! Task number must be a number."));
                }
            } else if (command.equals("delete")) {
                try {
                    int taskNumber = input.nextInt();
                    Task task = taskList.get(taskNumber - 1);
                    taskList.remove(taskNumber - 1);
                    System.out.println(formatReply("This task has been removed:\n" + task.toString()
                            + "\nNow you have " + taskList.size() + " in the list."));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(formatReply("OOPS!!! Task number is invalid."));
                } catch (InputMismatchException e) {
                    input.nextLine();
                    System.out.println(formatReply("OOPS!!! Task number must be a number."));
                }
            } else {
                try {
                    TypeOfTask typeOfTask;
                    switch (command) {
                        case "todo":
                            typeOfTask = TypeOfTask.TODO;
                            break;
                        case "deadline":
                            typeOfTask = TypeOfTask.DEADLINE;
                            break;
                        case "event":
                            typeOfTask = TypeOfTask.EVENT;
                            break;
                        default:
                            throw new InvalidCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    Task newTask = getTask(command, typeOfTask, input);
                    taskList.add(newTask);
                    System.out.println(formatReply("Got it. I've added this task:\n" + newTask.toString()
                            + "\nNow you have " + taskList.size() + " tasks in the list."));
                } catch (InvalidCommandException e) {
                    System.out.println(formatReply(e.getMessage()));
                } catch (MissingInfoException e) {
                    System.out.println(formatReply(e.getMessage()));
                } catch (DateTimeParseException e) {
                    System.out.println(formatReply("OOPS!!! Date format is invalid. Make sure it is yyyy-mm-ddTHH:mm."));
                }
            }
        }
        System.out.println(formatReply("Bye. Hope to see you again soon!"));
    }
}

class InvalidCommandException extends Exception {
    public InvalidCommandException(String message) {
        super(message);
    }
}

class MissingInfoException extends Exception {
    public MissingInfoException(String message) {
        super(message);
    }
}