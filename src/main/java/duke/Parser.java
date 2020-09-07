package duke;
import java.time.LocalDate;
import java.util.HashMap;

/**
 * Parse the user input is useable form
 */
public class Parser {
    /**
     * parse and get the instruction type of user input
     * @param user_input input give from user
     * @return instruction type of user input
     */
    static String parseInstruction(String user_input) {
        String instructionType = user_input.split(" ")[0];
        return instructionType;
    }

    /**
     * parse instruction for marking tasks done and return index of task
     * @param user_input input give from user
     * @return index of task which will be marked as done
     */
    static int parseMarkDoneInstr(String user_input) {
        int index = Integer.parseInt(user_input.split(" ")[1]) - 1;
        assert index >= 0 : "index cannot be nagative";
        return index;
    }

    /**
     * parse instruction for deleting task
     * @param user_input input give from user
     * @return index of task which will be deleted
     */
    static int parseDeleteInstr(String user_input) {
        int index = Integer.parseInt(user_input.split(" ")[1]) - 1;
        assert index >= 0 : "index cannot be nagative";
        return index;
    }

    /**
     * parse instruction for adding todo
     * @param user_input input give from user
     * @return description of todo
     */
    static String parseAddTodoInstr(String user_input) throws DukeException {
        // check if input is valid
        if (user_input.split(" ", 2).length == 1) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        // get data
        String description = user_input.split(" ", 2)[1];
        return description;
    }

    /**
     * parse instruction for adding deadline
     * @param user_input input give from user
     * @return hashMap containing description and deadline of task
     */
    static HashMap<String, Object> parseAddDeadlineInstr(String user_input) throws DukeException {
        // check if input is valid
        if (user_input.split(" ", 2).length == 1) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        String task = user_input.split(" ", 2)[1];
        if (task.split(" /by ", 2).length < 2) {
            throw new DukeException("☹ OOPS!!! The description and time is required for deadline");
        }
        // get data
        String description = task.split(" /by ", 2)[0];
        String time = task.split(" /by ")[1];
        LocalDate l_time = LocalDate.parse(time);
        HashMap<String, Object> parsedData = new HashMap<String, Object>(){
            { put("description", description); put("time", l_time); }
        };
        return parsedData;
    }

    /**
     * parse instruction for adding event
     * @param user_input input give from user
     * @return hashMap containing description and eventTime of event
     */
    static HashMap<String, Object> parseAddEventInstr(String user_input) throws DukeException {
        // check if input is valid
        if (user_input.split(" ", 2).length == 1) {
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
        }
        String task = user_input.split(" ", 2)[1];
        if (task.split(" /at ", 2).length < 2) {
            throw new DukeException("☹ OOPS!!! The description and time is required for event");
        }
        // get data
        String description = user_input.split(" /at ", 2)[0];
        String time = task.split(" /at ")[1];
        LocalDate l_time = LocalDate.parse(time);
        HashMap<String, Object> parsedData = new HashMap<String, Object>() {
            { put("description", description); put("time", l_time); }
        };
        return parsedData;
    }

    static String parseFindInstr(String user_input) {
        String keyword = user_input.split(" ")[1];
        return keyword;
    }


}
