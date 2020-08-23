public class Parser {

    public static Command parse(String fullCommand) throws InvalidFirstDukeException {
        String[] commandArr = fullCommand.split(" "); // split input into string array
        Command command;

        if (commandArr[0].equals("bye")) {
            command = new Command(CommandType.EXITDUKE, null);
        } else if (commandArr[0].equals("list")) {
            command = new Command(CommandType.PRINTALLTASKS, null);
        } else if (commandArr[0].equals("done")) {
            command = new Command(CommandType.MARKASDONE, commandArr);
        } else if (commandArr[0].equals("delete")) {
            command = new Command(CommandType.DELETETASK, commandArr);
        } else if (commandArr[0].equals("todo")) {
            command = new Command(CommandType.ADDTODO, commandArr);
        } else if (commandArr[0].equals("deadline")) {
            command = new Command(CommandType.ADDDEADLINE, commandArr);
        } else if (commandArr[0].equals("event")) {
            command = new Command(CommandType.ADDEVENT, commandArr);
        } else {
            throw new InvalidFirstDukeException();
        }
        return command;
    }
}
