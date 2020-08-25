package commands;

// Represents an add command.
public abstract class AddCommand extends Command {

    public static final String MESSAGE_SUCCESS = "Got it. I've added this task:\n" +
            "\t%1$s\n" +
            "Now you have %2$d tasks in the list.";

}
