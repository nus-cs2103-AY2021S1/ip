package duke.task;

import duke.storage.DukeIOException;

import java.util.List;

public class Parser {
    /**
     * Adds task to list of tasks based on type specified.
     *
     * @param taskInfo  Description of task.
     */
    public static Command parse(String taskInfo) {
        switch(taskInfo) {
        case "bye" :
            return new Command(CommandType.BYE, taskInfo);
        case "list" :
            return new Command(CommandType.PRINT, taskInfo);
        default :
                if (taskInfo.startsWith("done")) {
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
}
