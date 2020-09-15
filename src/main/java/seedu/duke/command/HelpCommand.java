package seedu.duke.command;

import seedu.duke.TaskList;
import seedu.duke.ui.Ui;

public class HelpCommand extends Command {
    public HelpCommand() {
        super();
    }

    /**
     * Returns an instruction list of possible commands in Duke.
     * @param ls The current list of tasks.
     * @param ui The ui that takes of printing output.
     */
    @Override
    public void execute(TaskList ls, Ui ui) {
        String str = "Did you seriously forget all the commands? Wow. Fine, here it is: \n"
                + "1. list: to get your list of tasks \n"
                + "2. sort by date/sort by done: to sort your list by deadlines or stuff you've done already \n"
                + "3. done [task number]: to mark your stuff as done. eg. done 1 \n"
                + "4. todo [task description]: to add a todo. eg. todo read book \n"
                + "5. deadline [task description] /by [time]:"
                        + "to add a deadline. eg. deadline read book /by 25 Mar 2020 1200 \n"
                + "6. event [task description] /at [time] to [time]: \n"
                        + "to add an event that happens at a certain time."
                        + "eg. event read book /at 25 Mar 2020 1200 to 25 Mar 2020 1400 \n"
                + "7. find [keyword]: finds a certain word in your task list. eg. find book \n"
                + "8. bye: you're finally done and want to bid farewell to me, thank goodness."
                ;
        ui.printResult(str);
    }
}
