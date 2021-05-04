package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

/**
 * The HelpCommand class contains methods pertaining to the HelpCommand.
 */
public class HelpCommand extends Command {
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.print("1. bye --- exit the program\n"
                + "2. list --- see the list of task saved\n"
                + "3. done <x> --- mark task numbered x as done\n"
                + "4. todo <details> --- add a todo with the given details\n"
                + "5. event <details> /at <date> to <date> --- add an event with the given details and time\n"
                + "6. deadline <deadline> /by <date> --- add a deadline with the given details and time\n"
                + "7. delete <x> --- deletes the task numbered x\n"
                + "8. find <keyword> --- find all tasks with the keyword\n"
                + "9. sort event/deadline --- sort events or deadlines in ascending order by date\n"
                + "The format of dates accepted are y/M/d HHmm and y-M-d HHmm\n"
                + "E.g. 2020/09/08 1430, 2020/9/8 1430, 2020-9-8 1430"
        );
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
