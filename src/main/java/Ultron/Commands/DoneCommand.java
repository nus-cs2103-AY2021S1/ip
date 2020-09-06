package ultron.commands;

import ultron.Parser;
import ultron.Storage;
import ultron.TaskList;
import ultron.exceptions.ExceptionType;
import ultron.exceptions.UltronException;
import ultron.tasks.Task;
import ultron.ui.UI;

public final class DoneCommand extends Command {

    /**
     * Constructor for Done Command.
     *
     * @param arguments Arguments for the command.
     */
    public DoneCommand(final String arguments) {
        super(false, arguments);
    }

    /**
     * Execute the done command.
     *
     * @param taskList List of tasks.
     * @param ui       UI for Ultron.
     * @param storage  Storage for Ultron.
     * @throws UltronException if there are too much or too little arguments.
     */
    @Override
    public void execute(final TaskList taskList,
                        final UI ui,
                        final Storage storage) throws UltronException {
        int index = Parser.parseInteger(this.getArguments());
        if (getArguments().trim().length() < 1) {
            throw new UltronException("done",
                ExceptionType.NO_ARGUMENTS_SUPPLIED);
        } else if (getArguments().trim().length() > 1) {
            throw new UltronException("done",
                ExceptionType.TOO_MUCH_ARGUMENTS);
        } else if (index >= taskList.size()) {
            throw new UltronException(Integer.toString(index + 1),
                "done",
                ExceptionType.INVALID_ARGUMENT);
        }
        Task task = taskList.get(index);
        if (task.isDone()) {
            throw new UltronException(task.toString(), ExceptionType.ALREADY_DONE);
        }
        assert getArguments().trim().length() == 1;
        task.markDone();
        ui.setMessage(String.format("Finally! Making yourself useful\n"
            + "\t%s%n", taskList.get(index)));
    }
}
