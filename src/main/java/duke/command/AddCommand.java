package duke.command;

import java.time.LocalDate;
import duke.ActionType;
import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

public class AddCommand extends Command {
    private final String input;
    private final ActionType actionType;
    public AddCommand(String input, ActionType actionType) {
        super(false);
        this.input = input;
        this.actionType = actionType;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task newTask = null;
        switch(actionType) {
        case ADD_TODO:
            if (input.length() < 6) {
                throw new DukeException("duke.tasks.Task cannot be empty _(´ཀ`」 ∠)_");
            } else {
                newTask = new ToDo(input.substring(5), false);
            }
            break;
        case ADD_EVENT:
            if (input.length() < 7) {
                throw new DukeException("duke.tasks.Event cannot be empty _(´ཀ`」 ∠)_");
            } else {
                String[] split = input.substring(6).split(" /at ");
                String eventDesc = split[0];
                LocalDate eventTime = LocalDate.parse(split[1]);
                newTask = new Event(eventDesc, eventTime, false);
            }
            break;
        case ADD_DEADLINE:
            if (input.length() < 10) {
                throw new DukeException("duke.tasks.Deadline cannot be empty _(´ཀ`」 ∠)_");
            } else {
                String[] split = input.substring(9).split(" /by ");
                String deadlineDesc = split[0];
                LocalDate deadline = LocalDate.parse(split[1]);
                newTask = new Deadline(deadlineDesc, deadline, false);
            }
            break;
        }
        tasks.addTask(newTask);
        storage.updateFile(tasks);
        ui.printTask(newTask, actionType);
        ui.printTotalTasks(tasks);
    }
}