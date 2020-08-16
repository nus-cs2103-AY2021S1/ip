import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greet();
        TaskManager manager = new TaskManager();
        String next = sc.nextLine();
        while (!next.equals("bye")) {
            try {
                if (next.equals("list")) {
                    manager.printList();
                    continue;
                }
                String[] actionExtracted = extractAction(next);
                String status = actionExtracted[0];
                String body = actionExtracted[1];
                switch (status) {
                    case "done":
                        manager.markTaskAsDone(Integer.parseInt(body));
                        break;
                    case "todo":
                        manager.addTask(body, status);
                        break;
                    case "delete":
                        manager.deleteTask(Integer.parseInt(body));
                        break;
                    default:
                        String[] timeExtracted = extractTime(body);
                        String content = timeExtracted[0];
                        String time = timeExtracted[1];
                        manager.addTask(content, status, time);
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("☹ OOPS!!! Seems you have provided an invalid index :-(");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                next = sc.nextLine();
            }
        }
        close();
    }

    private static String[] extractAction(String command) throws DukeException {
        if (command.equals("todo") || command.equals("done") || command.equals("event") || command.equals("deadline")) {
            throw new DukeException(String.format("☹ OOPS!!! The description of a %s cannot be empty.", command));
        }
        return command.split(" ", 2);
    }

    private static String[] extractTime(String command) throws DukeException {
        String[] spliced = command.split(" /by | /at ", 2);
        if (spliced.length < 2) {
            if (spliced[0].charAt(0) == '/') {
                throw new DukeException("☹ OOPS!!! Seems you forgot to supply the main content!");
            } else {
                String message = "☹ OOPS!!! Seems you forgot to supply the time!\n"
                        + "Simply add '/by <time>' for deadline OR '/at <time>' for event behind your command";
                throw new DukeException(message);
            }
        }
        return spliced;
    }

    private static void close() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }
}
