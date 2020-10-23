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
    static public int parseMarkDoneInstr(String userInput) throws DukeException {
        int index;
        try {
            index = Integer.parseInt(userInput.split(" ")[1]) - 1;
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! Right format is 'done [index]'. \n Check the index using list command.");
        }
        assert index >= 0 : "index cannot be nagative";
        return index;
    }

    /**
     * Return index of task which will be deleted by parsing delete task instruction.
     *
     * @param userInput Input give from user.
     * @return Index of task which will be deleted.
     */
    static public int parseDeleteInstr(String userInput) throws DukeException {
        int index;
        try {
            index = Integer.parseInt(userInput.split(" ")[1]) - 1;
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! Right format is 'delete [index]'. \n Check the index using list command.");
        }
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
            throw new DukeException("☹ OOPS!!! Right format is 'todo [description]'");
        }

        // get data
        String description = userInput.split(" ", 2)[1];
        if (description.trim().length() == 0) {
            throw new DukeException("☹ OOPS!!! Description cannot be empty");
        }
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
            throw new DukeException("☹ OOPS!!! Right format is\n'deadline [description] /by [YYYY-MM-DD]'");
        }
        String task = userInput.split(" ", 2)[1];
        if (task.split(" /by ", 2).length < 2) {
            throw new DukeException("☹ OOPS!!! Right format is\n'deadline [description] /by [YYYY-MM-DD]'");
        }

        // get data
        String description = task.split(" /by ", 2)[0];
        String time = task.split(" /by ")[1];
        LocalDate localTime;
        try{
            localTime = LocalDate.parse(time);
        } catch (java.time.format.DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! Right format is\n'event [description] /at [YYYY-MM-DD]'");
        }
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
            throw new DukeException("☹ OOPS!!! Right format is\n'event [description] /at [YYYY-MM-DD]'");
        }
        String task = userInput.split(" ", 2)[1];
        if (task.split(" /at ", 2).length < 2) {
            throw new DukeException("☹ OOPS!!! Right format is\n'event [description] /at [YYYY-MM-DD]'");
        }

        // get data
        String description = task.split(" /at ", 2)[0];
        String time = task.split(" /at ")[1];
        LocalDate localTime;
        try{
            localTime = LocalDate.parse(time);
        } catch (java.time.format.DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! Right format is\n'event [description] /at [YYYY-MM-DD]'");
        }
        HashMap<String, Object> parsedData = new HashMap<String, Object>() {
            {
                put("description", description);
                put("time", localTime);
            }
        };
        return parsedData;
    }

    static public String parseFindInstr(String userInput) throws DukeException {
        String keyword = "";
        try {
            keyword = userInput.split(" ")[1];
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! Right format is 'find [keyword]'");
        }
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
        Integer taskIndex = Integer.parseInt(splitUserInput[4]) - 1;
        HashMap<String, Object> parsedData = new HashMap<String, Object>() {
            {
                put("priorityLevel", priorityLevel);
                put("taskIndex", taskIndex);
            }
        };
        return parsedData;
    }

}
