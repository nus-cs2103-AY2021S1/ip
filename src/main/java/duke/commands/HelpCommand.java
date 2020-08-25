package src.main.java.duke.commands;

public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        String[] stringArray = {AddTodoCommand.MESSAGE_USAGE
                ,AddEventCommand.MESSAGE_USAGE
                ,AddDeadlineCommand.MESSAGE_USAGE
                ,DeleteCommand.MESSAGE_USAGE
                ,MarkDoneCommand.MESSAGE_USAGE
                ,HelpCommand.MESSAGE_USAGE
                ,ExitCommand.MESSAGE_USAGE};

        String resultString = "";

        // Loop through the string array
        for (int i = 0; i < stringArray.length; i++ ) {
            resultString += "\n" +  stringArray[i] + "\n";
            ;
        }

        return new CommandResult(resultString);
    }
}
