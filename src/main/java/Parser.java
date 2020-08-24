public class Parser {

    private static final String LIST_COMMAND = "list";
    private static final String END_COMMAND = "bye";
    private static final String ADD_TODO_COMMAND = "todo";
    private static final String ADD_DEADLINE_COMMAND = "deadline";
    private static final String ADD_EVENT_COMMAND = "event";
    private static final String DONE_COMMAND = "done";
    private static final String DELETE_COMMAND = "delete";
    private static final String FIND_BY_DATE_COMMAND = "find_by_date";

    public static Command parse(String userInput) throws InvalidFunctionException {
        String message = userInput.trim();
        String[] parsedCommand = message.split(" ");
        String function = parsedCommand[0];
        if (message.equals("/commands")) {
            return new ViewFunctionsCommand(parsedCommand);
        } else if (message.isEmpty()) {
            String err = "No input was entered! Please enter something!";
            throw new InvalidFunctionException(err);
        } else if (message.equals("list")) {
            return new ListCommand(parsedCommand);
        } else if (function.equals("done")) {
            return new CompleteTaskCommand(parsedCommand);
        } else if (function.equals("todo")){
            parsedCommand = message.split("todo");
            return new AddTodoCommand(parsedCommand);
        } else if (function.equals("deadline")) {
            parsedCommand = message.split("deadline");
            return new AddDeadlineCommand(parsedCommand);
        } else if (function.equals("event")) {
            parsedCommand = message.split("event");
            return new AddEventCommand(parsedCommand);
        } else if (function.equals("delete")) {
            return new DeleteTaskCommand(parsedCommand);
        } else if (function.equals("find_by_date")) {
            return new FindByDateCommand(parsedCommand);
        } else {
            String err = "Invalid Function! Input '/commands' for a list of all my commands.";
            throw new InvalidFunctionException(err);
        }
    }

}
