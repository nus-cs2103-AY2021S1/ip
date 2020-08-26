import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    enum Command {
        LIST, BYE, DONE, DELETE, DEADLINE, EVENT, TODO, EMPTY_TASK_TODO, EMPTY_TASK_EVENT_DEADLINE, EMPTY_DATE, NULL;
    }

    Command parse(String msg) {
        if (msg.equals("bye")) {
            return Command.BYE;

            //          PRINTING LIST
        } else if (msg.equals("list")) {
            return Command.LIST;

            //          UPDATING STATUS OF EVENTS
        } else if (msg.matches("^done \\d+$")) {
            return Command.DONE;

        } else if (msg.matches("^delete \\d+$")) {
            return Command.DELETE;
            //          CREATING NEW TASKS
        } else {

            //              DEADLINES
            if (msg.matches("^deadline \\S.* /by \\S.*$")) {
                return Command.DEADLINE;
                //              EVENTS
            } else if (msg.matches("^event \\S.* /at \\S.*$")) {
                return Command.EVENT;
                //              TODOS
            } else if (msg.matches("^todo \\S.*$")) {
                return Command.TODO;

//                        Checks for empty TODO
            } else if (msg.matches("^todo\\s*$")) {
                return Command.EMPTY_TASK_TODO;

//                        Checks for empty TASK for event/deadline
            } else if (
                    msg.matches("^event\\s/at.*$") ||
                            msg.matches("^deadline\\s/by.*$") ||
                            msg.matches("^event\\s*$") ||
                            msg.matches("^deadline\\s*$")) {
                return Command.EMPTY_TASK_EVENT_DEADLINE;

//                        Checks for empty DATE for event/deadline
            } else if (msg.matches("^event .* /at\\s*$") ||
                    msg.matches("^deadline .* /by\\s*") ||
                    msg.matches("^event.*") ||
                    msg.matches("^deadline.*")) {
                return Command.EMPTY_DATE;
            }

            return Command.NULL;
        }
    }
}
