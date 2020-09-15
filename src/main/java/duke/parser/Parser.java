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
        String cmd = taskInfo.trim().toLowerCase();
        if (cmd.equals("bye")) {
            return new Command(CommandType.BYE, taskInfo);
        } else if (cmd.equals("list")) {
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
        } else if (taskInfo.startsWith("find")) {
            return new Command(CommandType.FINDMATCHINGTASK, taskInfo);
        } else if (taskInfo.startsWith("duplicate")) {
            return new Command(CommandType.DUPLICATE, taskInfo);
        } else if (taskInfo.startsWith("remove duplicates")) {
            return new Command(CommandType.REMOVEDUPLICATES, taskInfo);
        } else if (taskInfo.startsWith("clear please")) {
            return new Command(CommandType.CLEAR, taskInfo);
        } else if (taskInfo.startsWith("help")) {
            return new Command(CommandType.HELP , taskInfo);
        } else {
            return new Command(CommandType.ERROR , taskInfo);
        }
    }
}
