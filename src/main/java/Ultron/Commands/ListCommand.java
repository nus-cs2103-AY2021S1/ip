package ultron.commands;

import ultron.Storage;
import ultron.TaskList;
import ultron.UI;
import ultron.exceptions.ExceptionType;
import ultron.exceptions.UltronException;

public final class ListCommand extends Command {

    public ListCommand(final String arguments) {
        super(false, arguments);
    }

    @Override
    public void execute(final TaskList taskList,
                        final UI ui,
                        final Storage storage) throws UltronException {

        //Check if there are arguments
        if (this.getArguments().trim().length() > 0) {
            throw new UltronException("list", ExceptionType.TOO_MUCH_ARGUMENTS);
        }

        //If the list is empty
        if (taskList.size() == 0) {

            //When there is no task
            ui.print("You have no business with me\n");

        } else {

            //Print the starting list
            ui.print("Heh, you cant even remember what you had\n");

            //Iterate through the task and print it
            for (int i = 0; i < taskList.size(); ++i) {

                //Print out each item on the list
                ui.print(String.format("%d.%s\n", i + 1, taskList.get(i)));

            }

        }

    }

}
