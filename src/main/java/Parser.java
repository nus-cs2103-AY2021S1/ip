public class Parser {
    protected static Command parse(String input) {
        Command command;
        if (input.startsWith("todo")) { // add todo tasks
            command = new AddCommand("todo");
        } else if (input.startsWith("deadline")) { // add deadline tasks
            command = new AddCommand("deadline");
        } else if (input.startsWith("event")) { // add event tasks
            command = new AddCommand("event");
        } else if (input.startsWith("delete")) { // delete tasks
            command = new DeleteCommand();
        } else if (input.startsWith("done")) { // mark tasks done
            command = new DoneCommand();
        } else if (input.equals("list")) { // list out the tasks
            command = new ListCommand();
        } else if (input.equals("bye")) { // exit the bot
            return new ExitCommand();
        } else { // handle invalid inputs
            command = new InvalidCommand();
        }
        return command;
    }
}
