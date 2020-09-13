/**
 * Represents a Parser to interpret String messages.
 */
public class Parser {
    enum Command {
        UNDO, LIST, BYE, DONE, DELETE, DEADLINE, EVENT, TODO, FIND;
    }


    /**
     * Return the Command associated with the message passed in.
     *
     * @param msg String message to parse and find a Command associated with message.
     * @return Command associated with the message passed in.
     */
    public Command parse(String msg) throws DukeException {
        if (msg.equals("bye")) {
            return Command.BYE;

        } else if (msg.equals("undo")) {
            return Command.UNDO;

        } else if (msg.equals("list")) {
            return Command.LIST;

        } else if (msg.matches("^done \\d+$")) {
            return Command.DONE;

        } else if (msg.matches("^delete \\d+$")) {
            return Command.DELETE;

        } else if (msg.matches("^find \\S.*")) {
            return Command.FIND;
        } else {
            if (msg.matches("^deadline \\S.* /by \\S.*$")) {
                return Command.DEADLINE;

            } else if (msg.matches("^event \\S.* /at \\S.*$")) {
                return Command.EVENT;

            } else if (msg.matches("^todo \\S.*$")) {
                return Command.TODO;

            } else if (msg.matches("^todo\\s*$")) {
//                return Command.EMPTY_TASK_TODO;
                throw new DukeException(DukeExceptionType.EMPTY_TASK_TODO);

            } else if (
                    msg.matches("^event\\s/at.*$") ||
                            msg.matches("^deadline\\s/by.*$") ||
                            msg.matches("^event\\s*$") ||
                            msg.matches("^deadline\\s*$")) {
                throw new DukeException(DukeExceptionType.EMPTY_TASK_EVENT_DEADLINE);

            } else if (msg.matches("^event .* /at\\s*$") ||
                    msg.matches("^deadline .* /by\\s*") ||
                    msg.matches("^event.*") ||
                    msg.matches("^deadline.*")) {
                throw new DukeException(DukeExceptionType.EMPTY_DATE);
            }
            throw new DukeException(DukeExceptionType.INVALID_INPUT);
        }
    }
}
