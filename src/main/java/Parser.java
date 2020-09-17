public class Parser {

    public static Command interpret(String userCommand) throws DukeException{

        String[] commandArgs = userCommand.split(" ");

        switch (commandArgs[0]) {
        case ("b"):
        case ("bye") :
            return new ByeCommand();
        case ("l"):
        case ("list") :
            return new ListCommand();
        case ("done") :
            if (commandArgs.length < 2) {
                throw new MissingNumberFromCommandException();
            } else {
                try {
                    int taskNumber = Integer.parseInt(commandArgs[1]) - 1;
                    return new DoneCommand(taskNumber);
                } catch (NumberFormatException e) {
                    throw new InvalidNumberFromCommandException();
                }
            }
        case ("del"):
        case ("delete") :
            if (commandArgs.length < 2) {
                throw new MissingNumberFromCommandException();
            } else {
                try {
                    int taskNumber = Integer.parseInt(commandArgs[1]) - 1;
                    return new DeleteCommand(taskNumber);
                } catch (NumberFormatException e) {
                    throw new InvalidNumberFromCommandException();
                }
            }
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
