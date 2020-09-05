package duke.parser;

import duke.command.Command;
import duke.command.CommandType;

public class Parser {
    /**
     * Returns a command based on the task information provided.
     *
     * @param taskInfo  Description of task.
     * @Return Command based on task information.
     */
    public static Command parse(String taskInfo) {
        String cmd = taskInfo.trim();
        if (cmd.equalsIgnoreCase("bye")) {
            return new Command(CommandType.BYE, taskInfo);
        } else if (cmd.equalsIgnoreCase("list")) {
            return new Command(CommandType.PRINT, taskInfo);
        } else if (taskInfo.startsWith("done")) {
            return new Command(CommandType.MARKTASKDONE, taskInfo);
        } else if (taskInfo.startsWith("todo")) {
            return new Command(CommandType.HANDLETODO, taskInfo);
        } else if (taskInfo.startsWith("deadline")) {
            return new Command(CommandType.HANDLEDEADLINE, taskInfo);
        } else if (taskInfo.startsWith("event")) {
            return new Command(CommandType.HANDLEVENT, taskInfo);
        } else if (taskInfo.startsWith("delete")) {
            return new Command(CommandType.DELETETASK, taskInfo);
        } else {
            return new Command(CommandType.FINDMATCHINGTASK, taskInfo);
        }
    }
}
