import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task tasks = new Task();
        Parser parser = new Parser();

        // TODO: 20/8/20 Improve runtime: keep an internal counter of task (for done.*)
        Printer.initialMessage();
        while(true) {
            try {
                String echo = sc.nextLine();

                // Command Handling
                // Exit
                if (echo.equals("bye")) {
                    Printer.exitMessage();
                    break;
                }

                // Querying items
                else if (echo.matches("list\\s*")) {
                    Printer.printList(tasks.printTodoList());
                }

                // Checks if it matches done and an integer
                else if (echo.matches("done.*")) {
                    int index = parser.parseDone(echo, tasks.length());
                    Printer.printDone(tasks.markAsDone(index));
                }

                // Checks if it matches done and an integer
                else if (echo.matches("delete.*")) {
                    int index = parser.parseDelete(echo, tasks.length());
                    Printer.printDelete(tasks.delete(index));
                }

                // Add items
                else {
                    Pair<TaskType, ArrayList<String>> res = parser.parseAdd(echo);
                    ArrayList<String> description = res.getU();
                    TaskType type = res.getT();
                    switch (type) {
                        case TODO:
                            Printer.printAdd(tasks.add(new ToDo(description.get(0))));
                            break;
                        case DEADLINE:
                            Printer.printAdd(tasks.add(new Deadline(description.get(0), description.get(1))));
                            break;
                        case EVENT:
                            Printer.printAdd(tasks.add(new Event(description.get(0), description.get(1))));
                            break;
                        case NONE:
                            break;
                    }
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
