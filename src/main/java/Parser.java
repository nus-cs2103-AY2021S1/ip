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
                if (chunks.length < 2) {
                    throw new DukeException(">> Oh no!!! Delete must have the index of the task you're deleting!");
                }
                return new DeleteCommand(chunks);
            case done:
                if (chunks.length < 2) {
                    throw new DukeException(">> Oh no!!! Done must have the index of the task you're completing!");
                }
                return new DoneCommand(chunks);
            case todo:
                if (chunks.length < 2) {
                    throw new DukeException(">> Oh no!!! A todo must have a description!");
                }
                return new AddTodoCommand(chunks);
            case deadline:
                if (chunks.length < 2) {
                    throw new DukeException(">> Oh no!!! A deadline must have a description and date!");
                }
                return new AddDeadlineCommand(chunks);
            case event:
                if (chunks.length < 2) {
                    throw new DukeException(">> Oh no!!! An event must have a description and date!");
                }
                return new AddEventCommand(chunks);
            default:
                throw new DukeException(">> Oh no!!! I don't understand this input.");
        }
    }
}
