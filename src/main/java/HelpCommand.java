public class HelpCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String help = "These are the available commands:\n" +
                "bye - exits the program\n" +
                "deadline <description> /by <due date and time> - " +
                "adds a deadline with the given description and due date to the task list\n" +
                "delete <task number> - deletes the task corresponding to the number from the task list\n" +
                "done <task number> - marks the task corresponding to the number as done\n" +
                "event <description> /at <due date and time> - " +
                "adds an event with the given description and due date to the task list\n" +
                "help - shows this list of commands\n" +
                "list - shows the contents of the task list\n" +
                "todo <description> - adds a todo task with the given description to the task list";
        ui.say(help);
    }
}
