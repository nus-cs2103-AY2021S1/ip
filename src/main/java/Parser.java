public class Parser {

    public static Command parse(String fullCommand) {
        String[] splitCommand = fullCommand.split(" ");
        if (fullCommand.equals("bye")) { // Exit the program
            return new ExitCommand();
        } else if (fullCommand.equals("list")) { // List out task list
            return new ListCommand();
        } else if (splitCommand[0].equals("done")) { // Done with a task
            return new DoneCommand(splitCommand);
        } else if (splitCommand[0].equals("delete")) { // Delete a task
            return new DeleteCommand(splitCommand);
        } else if (splitCommand[0].equals("todo")) { // Add To-Do task
            return new AddTodoCommand(fullCommand);
        } else if (splitCommand[0].equals("event")) { // Add Event task
            return new AddEventCommand(fullCommand);
        } else if (splitCommand[0].equals("deadline")) { // Add Deadline task
            return new AddDeadlineCommand(fullCommand);
        } else { // Unknown command entered
            return new UnknownCommand();
        }
    }
}
