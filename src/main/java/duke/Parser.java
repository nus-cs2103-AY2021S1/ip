package duke;

public class Parser {

    public static Command parseCommands(String userInput) throws DukeException {
        CommandEnum command;
        try {
            String[] input = userInput.split(" ");
            int len = userInput.length();
            String extractCommand = input[0];
            command = CommandEnum.valueOf(extractCommand.toUpperCase());
        } catch (NullPointerException | IllegalArgumentException ex) {
            System.out.println(Ui.line);
            System.out.println(Ui.bot);
            throw new DukeException(
                    "You have keyed in an invalid command or formatting!\n" +
                            "(Valid commands: todo, deadline, event, list, delete, bye, done)");
        }

        switch (command) {
            case BYE:
                return new ByeCommand();
            case LIST:
                return new ListCommand();
            case DONE:
                return new DoneCommand(userInput);
            case TODO:
                return new AddCommand(CommandEnum.TODO, userInput);
            case DEADLINE:
                return new AddCommand(CommandEnum.DEADLINE, userInput);
            case EVENT:
                return new AddCommand(CommandEnum.EVENT, userInput);
            case DELETE:
                return new DeleteCommand(userInput);
            case FIND:
                return new FindCommand(userInput);
            default:
                System.out.println(Ui.line);
                System.out.println(Ui.bot);
                throw new DukeException(
                        "You have keyed in an invalid command!\n" +
                                "(Valid commands: todo, deadline, event, list, delete, bye, done)");
        }

    }
}
