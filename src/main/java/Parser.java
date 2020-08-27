public class Parser {
    public static Command parse(String input) throws DukeException {
        input = input.toLowerCase();
        String[] spaceParse = input.split(" ");
        if (spaceParse.length == 0) {
            throw new DukeException("Sorry, I don't understand what you are saying! D=\n" +
                    "Type \"help\" to view the list of commands you can use!");
        }

        String command = spaceParse[0];

        if (command.equals("bye") && spaceParse.length == 1) {
            return new ExitCommand();
        }

        if (command.equals("help") && spaceParse.length == 1) {
            return new HelpCommand();
        }

        if (command.equals("list") && spaceParse.length == 1) {
            return new ListCommand();
        }

        if (command.equals("done")) {
            if (spaceParse.length != 2) {
                throw new DukeException("You need to specify which task to mark done! \n" +
                        "eg done 1");
            }

            int i = input.charAt(5) - 48;
            return new DoneCommand(i);
        }

        if (command.equals("delete")) {
            if (spaceParse.length != 2) {
                throw new DukeException("You need to specify which task to delete!\n" +
                        "eg delete 1");
            }

            int i = input.charAt(7) - 48;
            return new DeleteCommand(i);
        }

        if (command.equals("todo")) {
            if (spaceParse.length == 1) {
                throw new DukeException("You need to specify your todo task!\n"
                        + "eg todo read book");
            }

            String taskDesc = input.substring(5);
            String[] taskParse = {taskDesc};
            return new AddCommand(AddCommand.TODO, taskParse);
        }

        if (command.equals("deadline")) {
            if (input.split(" ").length == 1) {
                throw new DukeException("You need to specify your deadline!\n"
                        + "eg deadline return book /by 2019-10-15 2359");
            }

            String taskDesc = input.substring(9);
            String[] taskParse = taskDesc.split(" /by ");

            if (taskParse.length == 1) {
                throw new DukeException("You need to use the proper format!\n"
                        + "eg deadline return book /by 2019-10-15 2359");
            }

            return new AddCommand(AddCommand.DEADLINE, taskParse);
        }

        if (command.equals("event")) {
            if (input.split(" ").length == 1) {
                throw new DukeException("You need to specify your event!\n"
                        + "eg event project meeting /at 2019-10-15 1200");
            }

            String taskDesc = input.substring(6);
            String[] taskParse = taskDesc.split(" /at ");

            if (taskParse.length == 1) {
                throw new DukeException("You need to use the proper format!\n"
                        + "eg event project meeting /at 2019-10-15 1200");
            }

            return new AddCommand(AddCommand.EVENT, taskParse);
        }

        throw new DukeException("Sorry, I don't understand what you are saying! D=\n"
                + "Type \"help\" to view the list of commands you can use!");

    }
}
