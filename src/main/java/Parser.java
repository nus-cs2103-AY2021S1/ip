public class Parser {

    public static Command interpret(String userCommand) {

        String[] commandArgs = userCommand.split(" ");

        switch (commandArgs[0]) {
        case ("b"):
        case ("bye") :
            return new ByeCommand();
        case ("l"):
        case ("list") :
            return new ListCommand();
        case ("done") :
            return new DoneCommand(userCommand);
        case ("del"):
        case ("delete") :
            return new DeleteCommand(userCommand);
        case ("e"):
        case ("event") :
            return new EventCommand(userCommand);
        case("d"):
        case ("deadline") :
            return new DeadlineCommand(userCommand);
        case ("t"):
        case ("todo") :
            return new ToDoCommand(userCommand);
        case ("f"):
        case ("find") :
            return new FindCommand(userCommand);
        default :
            return new UnknownCommand(userCommand);
        }
    }

}
