package duke;

/**
 * Command to show help tutorial
 */
public class HelpCommand extends Command {

    /**
     * HelpCommand constructor
     *
     * @param
     */
    public HelpCommand() {
        super(false);
    }

    /**
     * Generates help message
     *
     * @param tasks List of tasks
     * @param ui User interface to print task
     * @param storage File storage object
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Hello, I'm DukeQ:) \n" + "this is a help for you~ \n"
                + "The valid command formats are as follows and the case is non-sensitive. \n"
                + "\n"
                + "todo then your instructions e.g. todo read book\n"
                + "deadline then your instructions e.g. deadline return book /by 2019-10-15\n"
                + "type 'event' followed by the description,\n"
                + " then'/at', then the time, the time format should be like 2020-09-01\n"
                + "done followed by the task number to marked it as done \n"
                + "type list to see the task lists\n"
                + "type find followed by keywords to search for tasks \n"
                + "type help to get a tutorial \n"
                + "type bye to exit DukeQ, hope you have a good day! \n";
    }
}
