public class Parser {
    public Parser() {

    }

    public Command parse(String input) throws DukeException {
        String[] chunks = input.split(" ", 2);
        String action = chunks[0];
        Constants.CommandType command;
        try {
            command = Constants.CommandType.valueOf(action);
        } catch (IllegalArgumentException e) {
            throw new DukeException(">> Oh no!!! I don't understand this input.");
        }
        switch(command) {
            case bye:
                return new ByeCommand();
            case list:
                return new ListCommand();
            case delete:
                return new DeleteCommand(chunks);
            case done:
                return new DoneCommand(chunks);
            case todo:
                return new AddTodoCommand(chunks);
            case deadline:
                return new AddDeadlineCommand(chunks);
            case event:
                return new AddEventCommand(chunks);
            default:
                throw new DukeException(">> Oh no!!! I don't understand this input.");
        }
    }
}
