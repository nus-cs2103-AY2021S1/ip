package ultron.commands;

import ultron.Storage;
import ultron.TaskList;
import ultron.exceptions.ExceptionType;
import ultron.exceptions.UltronException;
import ultron.ui.UI;

public final class ListCommand extends Command {

    /**
     * Constructor for List command.
     *
     * @param arguments Arguments needed for list command.
     */
    public ListCommand(final String arguments) {
        super(false, arguments);
    }

    /**
     * Execute the list command.
     *
     * @param taskList List of tasks.
     * @param ui       UI for Ultron.
     * @param storage  Storage for Ultron.
     * @throws UltronException if there are > 0 arguments.
     */
    @Override
    public void execute(final TaskList taskList,
                        final UI ui,
                        final Storage storage) throws UltronException {

        //Check if there are arguments
        if (this.getArgument().trim().length() > 0) {
            throw new UltronException("list", ExceptionType.TOO_MUCH_ARGUMENTS);
        }

        //If the list is empty
        if (taskList.size() == 0) {

            //When there is no task
            ui.setMessage("You have no business with me\n");

        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Heh, you cant even remember what you had\n");

            //Iterate through the task and print it
            for (int i = 0; i < taskList.size(); ++i) {

                //Print out each item on the list
                stringBuilder.append(String.format("%d.%s\n", i + 1, taskList.get(i)));

            }

            ui.setMessage(stringBuilder.toString());

        }

    }

}
