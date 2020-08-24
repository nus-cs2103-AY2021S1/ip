package commands;

import duke.Storage;
import duke.Ui;
import tasks.TaskList;

public class HelpCommand extends Command {

    @Override
    public void exec(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Welcome to Duke! Here is a list of commands you can use: \n" +
                "exit - shuts down the bot\n" +
                "todo <name> - adds a Todo task to your list\n" +
                "deadline <name> <time> - adds a Deadline task to your list\n" +
                "event <name> <time> - adds an Event task to your list\n" +
                "done <number> - marks a task as done\n" +
                "delete <number> - deletes a task from your list\n" +
                "list - displays the current list of your tasks\n" +
                "help - displays this helpful message\n");
    }
}
