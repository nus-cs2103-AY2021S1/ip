package duke.command;

import duke.DukeException;

public class HelpCommand implements Command {
    private static final String HELP_MESSAGE = "You can ask me to do these:\n"
                    + "list: List the current tasks in your list.\n"
                    + "bye: Saves any tasks in the list and quits the program.\n"
                    + "todo: Add a To-Do task.\n"
                    + "event: Add an event task.\n"
                    + "deadline: Add a deadline task.\n"
                    + "done: Mark task as done based on the task's number.\n"
                    + "delete: Deletes task based on the task's number.\n"
                    + "find: Find a task which matches your description.\n"
                    + "help: Repeat this list of possible commands.\n"
                    + "For more specific help, type help [command]";
    private final String userInput;
    public HelpCommand(String fullInput) {
        this.userInput = fullInput;
    }

    @Override
    public String executeWithResponse() throws DukeException {
        String[] splitInput = userInput.split(" ");
        if (splitInput.length > 2) {
            throw new DukeException("Sorry, I don't understand that help command.");
        } else if (splitInput.length == 1) {
            return HELP_MESSAGE;
        } else {
            switch (splitInput[1]) {
            case "list":
                return "list: Lists the current tasks in your list.\n"
                        + "Tasks are ordered based on the time\n"
                        + "it was entered into the system.";
            case "bye":
                return "bye: Saves any tasks in the list and quits the program.\n"
                        + "If GUI version is running, an additional command is\n"
                        + "required to exit the program after this bye command.";
            case "todo":
                return "todo: Add a To-Do task.\n"
                        + "Usage: todo [description]";
            case "event":
                return "event: Add an event task.\n"
                        + "Usage: event [description] /at [date]\n"
                        + "Date can be in the following formats:\n"
                        + "dd/MM/yyyy HHmm, yyyy/MM/dd HHmm, MM/dd/yyyy HHmm,\n"
                        + "HHmm dd/MM/yyyy, HHmm yyyy/MM/dd, HHmm MM/dd/yyyy,\n"
                        + "dd-MM-yyyy HHmm, yyyy-MM-dd HHmm, MM/dd/yyyy HHmm\n"
                        + "HHmm dd-MM-yyyy, HHmm yyyy-MM-dd, HHmm MM-dd-yyyy\n"
                        + "It is fine to omit the hours and minutes. If your\n"
                        + "input does not match the date time format, then\n"
                        + "Duke will simply enter the date as whatever you\n"
                        + "input as [date].";
            case "deadline":
                return "deadline: Add a deadline task.\n"
                        + "Usage: deadline [description] /by [date]\n"
                        + "Date can be in the following formats:\n"
                        + "dd/MM/yyyy HHmm, yyyy/MM/dd HHmm, MM/dd/yyyy HHmm,\n"
                        + "HHmm dd/MM/yyyy, HHmm yyyy/MM/dd, HHmm MM/dd/yyyy,\n"
                        + "dd-MM-yyyy HHmm, yyyy-MM-dd HHmm, MM/dd/yyyy HHmm\n"
                        + "HHmm dd-MM-yyyy, HHmm yyyy-MM-dd, HHmm MM-dd-yyyy\n"
                        + "It is fine to omit the hours and minutes. If your\n"
                        + "input does not match the date time format, then\n"
                        + "Duke will simply enter the date as whatever you\n"
                        + "input as [date].";
            case "done":
                return "done: Mark task as done based on the task's number.\n"
                        + "Usage: done [task number]\n"
                        + "The task number corresponds to the number when list\n"
                        + "called. Numbers outside of the range will be rejected.";
            case "delete":
                return "delete: Deletes task based on the task's number.\n"
                        + "Usage: delete [task number]\n"
                        + "The task number corresponds to the number when list\n"
                        + "called. Numbers outside of the range will be rejected.";
            case "find":
                return "find: Find a task which matches your description.\n"
                        + "Usage: find [keyword(s)]\n"
                        + "Duke only finds task that contain\n"
                        + "all of the keywords specified";
            default:
                return "I don't understand that command.\n"
                        + HELP_MESSAGE;
            }
        }
    }

    @Override
    public boolean continueDuke() {
        return true;
    }
}
