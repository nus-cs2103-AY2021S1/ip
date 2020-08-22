public class Parser {

    public static Command parse(String userCommand) throws DukeInputException{
        String[] inputs = userCommand.split(" ", 2);
        String command = inputs[0];
        String params = "";

        if (inputs.length == 1) {
            switch (command) {
            case "bye":
                return new ByeCommand();
            case "list":
                return new ListCommand();
            default:
                break;
            }
        } else if (inputs.length == 2) {
            params = inputs[1];
            switch (command) {
            case "done":
                return new DoneCommand(params);
            case "todo":
                return new ToDoCommand(params);
            case "deadline":
                return new DeadlineCommand(params);
            case "event":
                return new EventCommand(params);
            case "delete":
                return new DeleteCommand(params);
            default:
                break;
            }
        }

        throw new DukeInputException("Invalid command <" + userCommand + "> given.");
    }


}
