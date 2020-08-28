package duke.command;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import duke.Task;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeException;

/**
 * Represents an add timed task command in the Duke program.
 */
public class AddTimedCommand extends AddCommand {

    private String type;
    private LocalDate date;
    private Optional<LocalTime> time;

    /**
     * Initializes a newly created add timed task command with a type, description, and date
     *
     * @param type type of task.
     * @param desc description of task.
     * @param date date of task.
     */
    public AddTimedCommand(String type, String desc, LocalDate date) {
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
    public AddTimedCommand(String type, String desc, LocalDate date, LocalTime time) {
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
     * @throws DukeException if there is a saving issue.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task newTask = this.time.map(
                time -> tasks.addTimedTask(this.type, this.desc, this.date, time, false))
                .orElseGet(() -> tasks.addTimedTask(this.type, this.desc, this.date,false));
        storage.save(tasks.getList());
        ui.onAdd(newTask, tasks.size());
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
        } else if (obj instanceof AddTimedCommand) {
            AddTimedCommand ac = (AddTimedCommand) obj;
            return this.desc.equals(ac.desc)
                    && this.type.equals(ac.type)
                    && this.date.equals(ac.date)
                    && this.time.equals(ac.time);
        } else {
            return false;
        }
    }
}
