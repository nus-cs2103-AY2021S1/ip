package duke.tool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.exceptions.NoDescriptionException;
import duke.exceptions.NoSuchOrderException;
import duke.exceptions.NoTaskChosenException;
import duke.exceptions.NoThisNumOfTaskException;
import duke.exceptions.NoTimeException;
import duke.tasks.Deadline;
import duke.tasks.Delete;
import duke.tasks.Done;
import duke.tasks.Event;
import duke.tasks.Exit;
import duke.tasks.Find;
import duke.tasks.List;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * The class deals with making sense of the user command.
 */
public class Parser {
    private static DateTimeFormatter validFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Check whether the characters in the string represents an integer.
     * @param s
     * @return
     */
    public static boolean isInteger(String s) {
        if (s == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Parse the command from user and return the corresponding task.
     * @param order the order from user.
     * @param tl
     * @return the task which can be understood by Duke.
     */
    public static Task parse(String order, TaskList tl) {
        try {
            int numOfOrders = tl.getNumOfTasks();
            if (order != null) {
                String[] command = order.split(" ", 2);

                switch (ValidInput.getCmdType(command[0]).name()) {
                    case "EXIT":
                        return new Exit();
                    case "LIST":
                        return new List();
                    case "DONE":
                        if (command.length == 1) {
                            throw new NoTaskChosenException(command[0]);
                        } else if (!isInteger(command[1])
                                || Integer.parseInt(command[1]) > numOfOrders) {
                            throw new NoThisNumOfTaskException();
                        } else {
                            return new Done(Integer.parseInt(command[1]) - 1);
                        }
                    case "DELETE":
                        if (command.length == 1) {
                            throw new NoTaskChosenException(command[0]);
                        } else if (!isInteger(command[1])
                                || Integer.parseInt(command[1]) > numOfOrders) {
                            throw new NoThisNumOfTaskException();
                        } else {
                            return new Delete((Integer.parseInt(command[1]) - 1));
                        }
                    case "FIND":
                        return new Find(command[1]);
                    case "DEADLINE":
                        if (command.length == 1) {
                            throw new NoDescriptionException(command[0]);
                        }
                        String[] splitAgain = command[1].split("/by ");
                        if (splitAgain.length == 1) {
                            throw new NoTimeException(command[0]);
                        }
                        return new Deadline(splitAgain[0],
                                LocalDateTime.parse(splitAgain[1], validFormat), false);
                    case "EVENT":
                        if (command.length == 1) {
                            throw new NoDescriptionException(command[0]);
                        }
                        splitAgain = command[1].split("/at ");
                        if (splitAgain.length == 1) {
                            throw new NoTimeException(command[0]);
                        }
                        return new Event(splitAgain[0],
                                LocalDateTime.parse(splitAgain[1], validFormat), false);
                    case "TODO":
                        if (command.length == 1) {
                            throw new NoDescriptionException(command[0]);
                        }
                        return new Todo(command[1], false);
                    default:
                        throw new NoSuchOrderException();
                }
            }
        } catch (NoDescriptionException | NoTimeException | NoSuchOrderException
                | NoTaskChosenException | NoThisNumOfTaskException e) {
            e.printStackTrace();
        }
        return null;
    }

}
