package duke;
import java.time.LocalDate;
import java.util.HashMap;

/**
 * Parse the user input is useable form.
 */
public class Parser {
    /**
     * Returns instruction type by parsing user input.
     *
     * @param userInput Input give from user.
     * @return Instruction type of user input.
     */
    static String parseInstruction(String userInput) {
        String instructionType = userInput.split(" ")[0];
        return instructionType;
    }

    /**
     * Return index of tasks which will be marked as done by parsing mark task done instruction.
     *
     * @param userInput Input give from user.
     * @return Index of task which will be marked as done.
     */
    static public int parseMarkDoneInstr(String userInput) {
        int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
        assert index >= 0 : "index cannot be nagative";
        return index;
    }

    /**
     * Return index of task which will be deleted by parsing delete task instruction.
     *
     * @param userInput Input give from user.
     * @return Index of task which will be deleted.
     */
    static public int parseDeleteInstr(String userInput) {
        int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
        assert index >= 0 : "index cannot be nagative";
        return index;
    }

    /**
     * Returns information required to add todo task by parsing add todo instruction.
     *
     * @param userInput Input give from user.
     * @return Description of todo.
     */
    static public String parseAddTodoInstr(String userInput) throws DukeException {
        // check if input is valid
        if (userInput.split(" ", 2).length == 1) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }

        // get data
        String description = userInput.split(" ", 2)[1];
        return description;
    }

    /**
     * Returns information required to create deadline by parsing add deadline instruction.
     *
     * @param userInput iInput give from user.
     * @return HashMap containing description and deadline of task.
     */
    static public HashMap<String, Object> parseAddDeadlineInstr(String userInput) throws DukeException {
        // check if input is valid
        if (userInput.split(" ", 2).length == 1) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        String task = userInput.split(" ", 2)[1];
        if (task.split(" /by ", 2).length < 2) {
            throw new DukeException("☹ OOPS!!! The description and time is required for deadline");
        }

        // get data
        String description = task.split(" /by ", 2)[0];
        String time = task.split(" /by ")[1];
        LocalDate localTime = LocalDate.parse(time);
        HashMap<String, Object> parsedData = new HashMap<String, Object>() {
            {
                put("description", description);
                put("time", localTime);
            }
        };
        return parsedData;
    }

    /**
     * Returns information required to create event by parsing add event instruction.
     *
     * @param userInput Input give from user.
     * @return HashMap containing description and eventTime of event.
     */
    static public HashMap<String, Object> parseAddEventInstr(String userInput) throws DukeException {
        // check if input is valid
        if (userInput.split(" ", 2).length == 1) {
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
        }
        String task = userInput.split(" ", 2)[1];
        if (task.split(" /at ", 2).length < 2) {
            throw new DukeException("☹ OOPS!!! The description and time is required for event");
        }

        // get data
        String description = userInput.split(" /at ", 2)[0];
        String time = task.split(" /at ")[1];
        LocalDate localTime = LocalDate.parse(time);
        HashMap<String, Object> parsedData = new HashMap<String, Object>() {
            {
                put("description", description);
                put("time", localTime);
            }
        };
        return parsedData;
    }

    static public String parseFindInstr(String userInput) {
        String keyword = userInput.split(" ")[1];
        return keyword;
    }

    static public HashMap<String, Object> parseSetPriorityInstr(String userInput) throws DukeException{
        // check if input is valid
        String[] splitUserInput = userInput.split(" ");
        if (splitUserInput.length != 5) {
            throw new DukeException("☹ OOPS!!! Invalid format for priority command. \n"
                    + "Example: priority 2 to task 3");
        }

        // get data
        Integer priorityLevel = Integer.parseInt(splitUserInput[1]);
        if (!(1 <= priorityLevel && priorityLevel <= 3)) {
            throw new DukeException("\"☹ OOPS!!! Priority level can only be either 1, 2, or 3.");
        }
        Integer taskIndex = Integer.parseInt(splitUserInput[4]);
        HashMap<String, Object> parsedData = new HashMap<String, Object>() {
            {
                put("priorityLevel", priorityLevel);
                put("taskIndex", taskIndex);
            }
        };
        return parsedData;
    }

}
