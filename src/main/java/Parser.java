public class Parser {
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
            if (input.length() <= 5) {
                throw new ToDoException();
            } else {
                return new AddToDoCommand(input.substring(5));
            }
        } else if (input.startsWith("deadline")) {
            if (input.length() <= 9) {
                throw new DeadlineException();
            }
            int indexOfSeparator = input.indexOf("/");
            String taskDescription = input.substring(9, indexOfSeparator - 1);
            String by = input.substring(indexOfSeparator + 4);
            return new AddDeadlineCommand(taskDescription, by);
        } else if (input.startsWith("event")) {
            if (input.length() <= 6) {
                throw new EventException();
            }
            int indexOfSeparator = input.indexOf("/");
            String taskDescription = input.substring(6, indexOfSeparator - 1);
            String at = input.substring(indexOfSeparator + 4);
            return new AddEventCommand(taskDescription, at);
        } else {
            throw new CommandException();
        }
    }
}
