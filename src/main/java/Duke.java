import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hi there, I'm TARS!\nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        boolean stop = false;

        while (!stop) {
            String[] line = scanner.nextLine().split(" ", 2);
            String operation = line[0];
            switch (operation) {
                case "bye":
                    stop = true;
                    System.out.println("Bye bye. See you again soon!");
                    break;

                case "list":
                    try {
                        Helper.showList();
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "done":
                    try {
                        Helper.markAsDone(line);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "todo":
                case "deadline":
                case "event":
                    if (line.length == 1) {
                        System.out.println("☹ OOPS! Description for command '" + operation + "' not found, try again!");
                        break;
                    } else if (operation.equals("todo")) {
                        try {
                            Helper.addToDo(line);
                        } catch (DukeException e) {
                            System.out.println(e.getMessage());
                        }
                    } else if (operation.equals("deadline")) {
                        try {
                            Helper.addDeadline(line);
                        } catch (DukeException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        try {
                            Helper.addEvent(line);
                        } catch (DukeException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;

                case "delete":
                    try {
                        Helper.deleteTask(line);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                default:
                    // Handles all other inputs
                    System.out.println("☹ Sorry, I don't recognise that command! Try one of the following instead: todo, event, deadline, done or delete");
            }
        }
    }
}