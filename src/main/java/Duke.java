import java.io.*;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

enum Category {
    TODO, DEADLINE, EVENT, LIST, BYE, DONE, DELETE, READ
}

public class Duke {
    public static Command decideCategory(String input) throws IllegalArgumentException {
        String[] wordsArray = input.split(" ", 2);
        String categoryWord = wordsArray[0];
        String description = wordsArray.length == 2 ? wordsArray[1] : null;
        switch (categoryWord) {
            case "todo":
                return new TodoCommand(Category.TODO, description);
            case "deadline":
                return new DeadlineCommand(Category.DEADLINE, description);
            case "event":
                return new EventCommand(Category.EVENT, description);
            case "done":
                return new DoneCommand(Category.DONE, description);
            case "delete":
                return new DeleteCommand(Category.DELETE, description);
            case "list":
                return new ListCommand(Category.LIST, description);
            case "bye":
                return new ByeCommand(Category.BYE, description);
            default:
                throw new IllegalArgumentException("-------------------------------------------\n" +
                        "☹ OOPS!!! Invalid input. Try again!\n"
                        + "-------------------------------------------");
        }
    }


    public static void processCommand(Command command, TaskList taskList) throws IOException {
        int taskNumber;
        switch (command.getCategory()) {
            case TODO:
                Todo newTask = new Todo(command.getDescription());
                taskList.addTask(newTask);
                System.out.println(newTask +
                        String.format("\nNow you have %d tasks in the list.\n", taskList.getTaskLength())
                        + "-------------------------------------------");
                break;
            case DEADLINE:
                Deadline newDeadline = new Deadline(command.getDescription());
                taskList.addTask(newDeadline);
                System.out.println(newDeadline +
                        String.format("\nNow you have %d tasks in the list.\n", taskList.getTaskLength())
                        + "-------------------------------------------");
                break;
            case EVENT:
                Event newEvent = new Event(command.getDescription());
                taskList.addTask(newEvent);
                System.out.println(newEvent +
                        String.format("\nNow you have %d tasks in the list.\n", taskList.getTaskLength())
                        + "-------------------------------------------");

                break;
            case LIST:
                System.out.println("-------------------------------------------\n" +
                        "Here are the tasks in your list:");
                taskList.showList();
                System.out.println(String.format("Now you have %d tasks in the list.\n", taskList.getTaskLength()) +
                        "-------------------------------------------");
                break;
            case DONE:
                System.out.println("-------------------------------------------\n" +
                        "Nice! I've marked this task as done:");
                taskNumber = Integer.parseInt(command.getDescription());
                Task doneTask = taskList.getTask(taskNumber);
                doneTask.markAsDone();
                System.out.println(doneTask + "\n-------------------------------------------");
                break;
            case DELETE:
                System.out.println("-------------------------------------------\n" +
                        " Noted. I've removed this task:\n");
                taskNumber = Integer.parseInt(command.getDescription());
                Task deletedTask = taskList.getTask(taskNumber);
                taskList.removeTask(deletedTask);
                System.out.println(deletedTask + "\n-------------------------------------------");
                break;
            case BYE: //bye
                System.out.println("-------------------------------------------\n" +
                        "Bye bye! Hope to see you again soon!\n" +
                        "-------------------------------------------");
                System.exit(0);
                break;
        }
    }

    public static void printHello() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("-------------------------------------------\n" +
                "Hello! I'm Duke\n" + "What can I do for you?\n" +
                "-------------------------------------------\n");
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        printHello();
        Scanner sc = new Scanner(System.in);
        String input;

        TaskList taskList = Storage.load();
        while (true) {
            try {
                input = sc.nextLine(); //1. read input
                Command command = decideCategory(input);
                processCommand(command, taskList);
                Storage.store(taskList);
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            } catch (DateTimeParseException exception) {
                System.out.println(exception.getMessage());
            } catch (FileNotFoundException exception) {
                System.out.println("File does not exist yet :(");
            }
        }
    }
}










