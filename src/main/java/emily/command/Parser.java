package main.java.emily.command;

import main.java.emily.task.Deadline;
import main.java.emily.task.Event;
import main.java.emily.task.Task;
import main.java.emily.task.ToDos;

/**
 * Deals with making sense of the user command
 */

public class Parser {

    Parser() {

    }

    /**
     * Convert the user input string to a proper Task
     * @param str of user input
     * @return A new Task with the input details
     * @throws DukeException when the user input is invalid or is not in the proper form
     */
    public Task process(String str) throws DukeException {
        Task item = new Task("");

        try {
            if (str.contains("todo")) {
                String describe = str.substring(5);
                item = new ToDos(describe);

                System.out.println("    Got it! I have added this task:");
                System.out.println("        " + item);
                return item;
            } else if (str.contains("deadline")) {

                String description = str.substring(9);
                String[] temp = description.split("/by ");

                item = new Deadline(temp[0], temp[1]);
                return item;

            } else {

                String description = str.substring(6);
                String[] temp = description.split("/at ");
                item = new Event(temp[0], temp[1]);
                return item;

            }
        }catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Invalid input");
        } catch (java.time.DateTimeException e) {
            throw new DukeException("Invalid timestamp, should be in the form of yyy-mm--dd");
        }
    }
}
