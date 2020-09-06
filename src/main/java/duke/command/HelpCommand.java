package duke.command;

import java.util.List;
import java.util.stream.Collectors;

import duke.Storage;
import duke.task.TaskList;

public class HelpCommand extends Command {
    /**
     * Executes the Help command by printing out a guide on how to use the chat bot.
     *
     * @param tasks   The list of tasks known by the chat bot.
     * @param storage The storage that is used by the chat bot.
     * @return A string detailing the outcome of the execution.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        String listHelpMessage = "To list out all existing tasks, type 'list'.\n";
        String byeHelpMessage = "To exit the program, type 'bye'.\n";
        String todoHelpMessage = "To add a new Todo item, type 'todo <description>'.\n";
        String deadlineHelpMessage =
                "To add a new Deadline item, type 'deadline <description> /by <date in yyyy-MM-dd"
                        + " format>'.\n";
        String eventHelpMessage = "To add a new Event item, type 'event <description> /at <date "
                + "in yyyy-MM-dd format>'.\n";
        String doneHelpMessage = "To mark any item as complete, type 'done <taskId>'.\n";
        String findHelpMessage =
                "To find all items containing a certain keyword, type 'find <keyword>'.\n";

        List<String> messages =
                List.of(listHelpMessage, byeHelpMessage, todoHelpMessage, deadlineHelpMessage,
                        eventHelpMessage, doneHelpMessage, findHelpMessage).stream()
                        .map((message) -> "\u2022 " + message).collect(Collectors.toList());

        return String.format("This is a list of functionalities that I support:\n\n%s",
                String.join("", messages));
    }
}
