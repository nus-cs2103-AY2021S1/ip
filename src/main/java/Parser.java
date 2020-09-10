public class Parser {
    private static final int TO_DO_MIN_LENGTH = 6;
    private static final int DEADLINE_MIN_LENGTH = 10;
    private static final int EVENT_MIN_LENGTH = 7;
    private static final int BY_GAP = 4;
    private static final int AT_GAP = 4;


    public static Command parse(String input) throws ToDoException, DeadlineException, EventException, 
            CommandException {
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("delete")) {
            int indexOfTaskToDelete = Integer.parseInt(input.substring(7));
            return new DeleteCommand(indexOfTaskToDelete);
        } else if (input.startsWith("done")) {
            int indexOfDoneTask = Integer.parseInt(input.substring(5));
            return new DoneCommand(indexOfDoneTask);
        } else if (input.startsWith("find")) {
            return new FindCommand(input.substring(5));
        } else if (input.startsWith("todo")) {
            if (input.length() < TO_DO_MIN_LENGTH) {
                throw new ToDoException();
            } else {
                return new AddToDoCommand(input.substring(TO_DO_MIN_LENGTH - 1));
            }
        } else if (input.startsWith("deadline")) {
            if (input.length() < DEADLINE_MIN_LENGTH) {
                throw new DeadlineException();
            }
            int indexOfSeparator = input.indexOf("/");
            String taskDescription = input.substring(DEADLINE_MIN_LENGTH - 1, indexOfSeparator - 1);
            String by = input.substring(indexOfSeparator + BY_GAP);
            return new AddDeadlineCommand(taskDescription, by);
        } else if (input.startsWith("event")) {
            if (input.length() < EVENT_MIN_LENGTH) {
                throw new EventException();
            }
            int indexOfSeparator = input.indexOf("/");
            String taskDescription = input.substring(EVENT_MIN_LENGTH - 1, indexOfSeparator - 1);
            String at = input.substring(indexOfSeparator + AT_GAP);
            return new AddEventCommand(taskDescription, at);
        } else {
            throw new CommandException();
        }
    }
}
