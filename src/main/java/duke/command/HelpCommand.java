package duke.command;

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
        String listHelpMessage = "To list out all existing tasks, type `list`.\n";
        String byeHelpMessage = "To exit the program, type `bye`.\n";
        String todoHelpMessage = "To add a new Todo item, type `todo {description}`.\n  - For "
                + "example, typing `todo CS2103T tutorial` will create a new Todo item for "
                + "CS2103T tutorial.\n";
        String deadlineHelpMessage =
                "To add a new Deadline item, type `deadline {description} /by {date in yyyy-MM-dd"
                        + " format}`.\n  - For example, typing `deadline CS2103T tutorial /by "
                        + "2020-10-30` will create a new Deadline item for CS2103T tutorial with "
                        + "deadline on 30 October 2020.\n";
        String eventHelpMessage = "To add a new Event item, type `event {description} /at {date "
                + "in yyyy-MM-dd} format.\n  - For example, typing `event CS2103T tutorial /at "
                + "2020-10-30` will create a new Event item for CS2103T tutorial at 30 October "
                + "2020.\n";
        String doneHelpMessage = "To mark any item as complete, type `done {taskId}`.\n  - For "
                + "example, typing `done 1` would mark the first item (as per what is "
                + "shown when you type the `list` command) as complete.\n";
        String findHelpMessage = "To find all items containing a certain keyword, type `find "
                + "{keyword}`.\n -- For example, typing `find CS2103T` would display all items "
                + "that contains CS2103T in their description.\n";

        return String
                .format("This is a list of functionalities that I support.\n\n" + "%s%s%s%s%s%s%s",
                        listHelpMessage, byeHelpMessage, todoHelpMessage, deadlineHelpMessage,
                        eventHelpMessage, doneHelpMessage, findHelpMessage);
    }
}
