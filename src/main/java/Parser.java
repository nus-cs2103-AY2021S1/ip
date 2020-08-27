public class Parser {

    public static Command interpret(String userCommand) {

        String[] commandArgs = userCommand.split(" ");

        switch (commandArgs[0]) {
        case ("bye") :
            return new ByeCommand();
        case ("list") :
            return new ListCommand();
        case ("done") :
            return new DoneCommand(userCommand);
        case ("delete") :
            return new DeleteCommand(userCommand);
        case ("event") :
            return new EventCommand(userCommand);
        case ("deadline") :
            return new DeadlineCommand(userCommand);
        case ("todo") :
            return new ToDoCommand(userCommand);
        default :
            return new UnknownCommand(userCommand);
        }
    }

}
