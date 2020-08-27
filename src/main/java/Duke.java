import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskList tasks = new TaskList();
        Parser parser = new Parser();
        Storage storage = new Storage();

        // TODO: 20/8/20 Improve runtime: keep an internal counter of task (for done.*)
        Ui.initialMessage();
        while (true) {
            try {
                String echo = sc.nextLine();

                // Command Handling
                // Exit
                if (echo.equals("bye")) {
                    storage.save(tasks);
                    Ui.exitMessage();
                    break;
                }

                // Querying items
                else if (echo.matches("(?i)list\\s*")) {
                    Ui.printList(tasks.printTodoList());
                }

                // Checks if it matches done and an integer
                else if (echo.matches("(?i)done.*")) {
                    int index = parser.parseDone(echo, tasks.length());
                    Ui.printDone(tasks.markAsDone(index));
                }

                // Checks if it matches done and an integer
                else if (echo.matches("(?i)delete.*")) {
                    int index = parser.parseDelete(echo, tasks.length());
                    Ui.printDelete(tasks.delete(index));
                }

                // Add items
                else {
                    Task task = parser.parseAdd(echo);
                    Ui.printAdd(tasks.add(task));
                }
            } catch (DukeException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
