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
                String[] actionExtracted = Evaluator.extractAction(next);
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
                        String[] timeExtracted = Evaluator.extractTime(body);
                        String content = timeExtracted[0];
                        String time = timeExtracted[1];
                        manager.addTask(content, status, time);
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println(Messenger.INDEX_FORMAT_ERROR);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(Messenger.COMMAND_UNRECOGNIZABLE_ERROR);
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

    private static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    private static void close() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
