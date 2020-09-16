package duke.command;

import duke.Statistics;
import duke.Storage;
import duke.TaskList;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    private static final String MESSAGE_HELP_GUIDE = "Here are the available commands:\n"
            + "1. list → lists all your current tasks\n"
            + "2. todo {task description} → adds a todo task\n"
            + "3. deadline {task description} /by {date} → adds a deadline task\n"
            + "4. event {task description} /at {date} → adds an event task\n"
            + "5. stats → show stats\n"
            + "6. done {task number} → marks the specified task as done\n"
            + "7. delete {task number} → removes the specified task\n"
            + "8. find {keyword} → search for tasks containing the keyword\n"
            + "9. owo → interact with me! :)\n"
            + "10. uwu → this is our secret form of communication shh\n"
            + "11. exit → exit the app and leave me :(\n"
            + "12. help → to see all the available commands";
    @Override
    public String execute(TaskList tasks, Storage storage, Statistics stats) {
        return MESSAGE_HELP_GUIDE;
    }
}
