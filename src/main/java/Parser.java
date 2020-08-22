import java.util.List;

public class Parser {

    private String userCommand;

    public Parser(String userCommand) {
        this.userCommand = userCommand;
    }

    private void parseCommand() throws InvalidCommandException {
        String[] command = this.userCommand.split(" ");
        if (command[0].equals("done")) {
            DoneCommand newDoneCommand = new DoneCommand(userCommand);
        } else if (command[0].equals("todo")) {
            TodoCommand newTodoCOmmand = new TodoCommand(userCommand);
        } else if (command[0].equals("event")) {
            EventCommand newEventCommand = new EventCommand(userCommand);
        } else if (command[0].equals("deadline")) {
            DeadlineCommand newDeadlineCommand = new DeadlineCommand(userCommand);
        } else if (command[0].equals("delete")) {
            DeleteCommand newDeleteCommand = new DeleteCommand(userCommand);
        } else if (command[0].equals("list")) {
            ListCommand newListCommand = new ListCommand(userCommand);
        } else if (command[0].equals("bye")) {
            ByeCommand newByeCommand = new ByeCommand();
        } else {
            throw new InvalidCommandException();
        }
    }
}
