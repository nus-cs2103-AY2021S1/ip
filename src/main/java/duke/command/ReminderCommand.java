package duke.command;

import duke.CommandAgent;
import duke.DukeException;

public class ReminderCommand extends Command {
    private static final String REMINDER_REQUEST = "remind";

    /**
     * Creates a ReminderCommand.
     *
     * @param content The task information supplied by the user.
     * @throws DukeException If the content has no input or improper input.
     */
    public ReminderCommand(String content) throws DukeException {
        int numberOfTasks = CommandAgent.getListSize();
        if (content.isEmpty()) {
            throw new DukeException("☹ OOPS!!! Please enter a digit no more than " + numberOfTasks);
        } else if (!(Character.isDigit(content.charAt(0)))) {
            throw new DukeException("☹ OOPS!!! Please enter a numerical value");
        } else if (Integer.parseInt(content) > numberOfTasks) {
            throw new DukeException("☹ OOPS!!! We don't have that may tasks yet");
        } else if (Integer.parseInt(content) <= 0) {
            throw new DukeException("☹ OOPS!!! You may want to check for at least 1 most recent task to do");
        } else {
            this.content = content;
        }
    }

    @Override
    public String sendRequest() {
        return REMINDER_REQUEST;
    }
}
