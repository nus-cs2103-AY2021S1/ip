public class Parser {

    public static Command parse(String userCommand) throws DukeInputException{
        String[] inputs = userCommand.split(" ", 2);
        String command = inputs[0];
        String params = "";

        if (inputs.length == 1) {
            switch (command) {
            case "bye":
                //Todo: ByeCommand
                break;
            case "list":
                //Todo: ListCommand
                break;
            default:
                //Todo: Add DukeInputException here
                break;
            }
        } else if (inputs.length == 2) {
            params = inputs[1];
            switch (command) {
            case "done":
                //Todo: DoneCommand
                break;
            case "todo":
                //Todo: ToDoCommand
                break;
            case "deadline":
                //Todo: DeadlineCommand
                break;
            case "event":
                //Todo: EventCommand
                break;
            case "delete":
                //Todo: DeleteCommand
                break;
            default:
                //Todo: Raise DukeInputException
                break;
            }
        } else {
            //Todo: Raise DukeInputException
            throw new DukeInputException(""/*fill this up*/);
        }

        return new ByeCommand();

    }


}
