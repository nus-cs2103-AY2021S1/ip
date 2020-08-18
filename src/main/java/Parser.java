import java.util.ArrayList;

public class Parser {
    public Parser(){}

    public ArrayList<String> parse(String echo) {
        if (echo.matches("^todo.*")) {
            printAdd(tasks.add(new ToDo(echo)));
        } else if (echo.matches("^deadline.*\\/by.*")) {
            String[] res = echo.substring("deadline".length()).split("/by");
            printAdd(tasks.add(new Deadline(res[0], res[1])));
        } else if (echo.matches("^event.*\\/at.*")) {
            String[] res = echo.substring("event".length()).split("/at");
            printAdd(tasks.add(new Event(res[0], res[1])));
        }

        // Querying items
        else if (echo.equals("list")) {
            printList(tasks.printTodoList());
        } else if (echo.matches("done\\s[0-9]+")) { // Checks if it matches done and an integer
            // Strip the done, leaving the integer
            int index = Integer.parseInt(echo.replaceAll("done ", ""));
            printDone(tasks.markAsDone(index));
        }

        // Exit command
        else if (echo.equals("bye")) {
            exitMessage();
            break;
        }
    }
}
