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
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.toString());
            } finally {
                next = sc.nextLine();
            }
        }
        close();
    }

    private static String[] extractAction(String command) throws DukeException {
        String[] split = command.split(" ", 2);
        int len = split.length;

        // command is empty
        if (len == 0) {
            throw new DukeException("☹ OOPS!!! The command cannot be empty!");
        }

        String status = split[0];
        if (!(status.equals("todo") ||
                status.equals("done") ||
                status.equals("event") ||
                status.equals("deadline") ||
                status.equals("delete"))) {
            throw new DukeException("☹ OOPS!!! Check if you have spelled correctly!");
        }

        // no description supplied
        if (len == 1) {
            throw new DukeException(String.format("☹ OOPS!!! The description of a %s cannot be empty.", command));
        }
        return split;
    }

    private static String[] extractTime(String command) throws DukeException {
        String[] split = command.split(" /by | /at ", 2);
        if (split.length < 2) {
            if (split[0].charAt(0) == '/') {
                throw new DukeException("☹ OOPS!!! Seems you forgot to supply the main content!");
            } else {
                String message = "☹ OOPS!!! Seems you forgot to supply the time!\n"
                        + "Simply add '/by <time>' for deadline OR '/at <time>' for event behind your command";
                throw new DukeException(message);
            }
        }
        return split;
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
