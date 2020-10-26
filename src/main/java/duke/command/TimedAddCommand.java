package duke.command;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import duke.DukeException;
import duke.task.Task;
import duke.util.AliasMap;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents an add timed task command in the Duke program.
 */
public class TimedAddCommand extends AddCommand {

    private final String type;
    private final LocalDate date;
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private final Optional<LocalTime> time;

    /**
     * Initializes a newly created add timed task command with a type, description, and date
     *
     * @param type type of task.
     * @param desc description of task.
     * @param date date of task.
     */
    public TimedAddCommand(String type, String desc, LocalDate date) {
        super(desc);
        this.type = type;
        this.date = date;
        this.time = Optional.empty();
    }

    /**
     * Initializes a newly created add timed task command with a type, description, date, and time
     *
     * @param type type of task.
     * @param desc description of task.
     * @param date date of task.
     * @param time time of task.
     */
    public TimedAddCommand(String type, String desc, LocalDate date, LocalTime time) {
        super(desc);
        this.type = type;
        this.date = date;
        this.time = Optional.ofNullable(time);
    }

    /**
     * Executes the add timed task command.
     *
     * @param tasks TaskList of the program.
     * @param ui user interface of the program.
     * @param storage storage of the program.
     * @param aliasMap alias mapping.
     * @return the execution message.
     * @throws DukeException if there is a saving issue.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, AliasMap aliasMap) throws DukeException {
        assert tasks != null && ui != null && storage != null;
        int previousSize = tasks.getSize();
        Task newTask = this.time.map(
            time -> tasks.addTimedTask(this.type, this.desc, this.date, time, false))
            .orElseGet(() -> tasks.addTimedTask(this.type, this.desc, this.date, false));
        assert tasks.getSize() == previousSize + 1;
        storage.save(tasks.getList());
        return ui.printAddMessage(newTask, tasks.getSize());
    }

    /**
     * Checks whether an object equals this add timed task command.
     *
     * @param obj object to be compared with this command.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof TimedAddCommand) {
            TimedAddCommand ac = (TimedAddCommand) obj;
            return this.desc.equals(ac.desc)
                    && this.type.equals(ac.type)
                    && this.date.equals(ac.date)
                    && this.time.equals(ac.time);
        } else {
            return false;
        }
    }
}
