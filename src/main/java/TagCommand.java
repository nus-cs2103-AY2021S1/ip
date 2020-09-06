public class TagCommand extends Command {
    private String command;

    TagCommand(String command) {
        this.command = command;
    }

    @Override
    public String execute(TaskList tasklist, UI ui) throws DukeEmptyTagException {
        String message = "";
        String[] tagCommand = this.command.split(" ");
        if (tagCommand.length == 1 || tagCommand.length == 2) {
            throw new DukeEmptyTagException(command);
        } else {
            message = ui.printTag(this.command, tasklist.getTasks());
        }
        return message;
    }
}
