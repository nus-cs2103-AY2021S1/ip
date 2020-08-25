package duke;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a command in the Duke program.
 */
public class Command {

    private String type;
    private Integer idx;
    private String desc;
    private LocalDate date;
    private LocalTime time;

    /**
     * Initializes a newly created Command with a type.
     *
     * @param type type of command.
     */
    public Command(String type) {
        this.type = type;
        this.idx = null;
        this.desc = null;
        this.date = null;
        this.time = null;
    }

    /**
     * Initializes a newly created Command with a type and index.
     *
     * @param type type of command.
     * @param idx index of task.
     */
    public Command(String type, int idx) {
        this.type = type;
        this.idx = idx;
        this.desc = null;
        this.date = null;
        this.time = null;
    }

    /**
     * Initializes a newly created Command with a type and description.
     *
     * @param type type of command.
     * @param desc description of task.
     */
    public Command(String type, String desc) {
        this.type = type;
        this.idx = null;
        this.desc = desc;
        this.date = null;
        this.time = null;
    }

    /**
     * Initializes a newly created Command with a type, description, and date.
     *
     * @param type type of command.
     * @param desc description of task.
     * @param date date of task.
     */
    public Command(String type, String desc, LocalDate date) {
        this.type = type;
        this.idx = null;
        this.desc = desc;
        this.date = date;
        this.time = null;
    }

    /**
     * Initializes a newly created Command with a type, description, date, and time.
     *
     * @param type type of command.
     * @param desc description of task.
     * @param date date of task.
     * @param time time of task.
     */
    public Command(String type, String desc, LocalDate date, LocalTime time) {
        this.type = type;
        this.idx = null;
        this.desc = desc;
        this.date = date;
        this.time = time;
    }

    /**
     * Checks whether the program is quitting.
     *
     * @return true if the command type is bye, false otherwise.
     */
    public boolean isQuitting() {
        return this.type.equals("bye");
    }

    /**
     * Executes the command.
     *
     * @param tasks TaskList of the program
     * @param ui user interface of the program
     * @param storage storage of the program
     * @throws DukeException if a task doesn't exist.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        switch (this.type) {
        case "bye":
            ui.farewell();
            break;
        case "list":
            ui.onList(tasks.getList());
            break;
        case "find":
            ui.onFind(tasks.find(this.desc));
            break;
        case "done":
            if (this.idx >= tasks.size()) {
                throw new DukeException("Oh dear! That task doesn't exist!");
            }
            Task doneTask = tasks.setDone(this.idx);
            storage.save(tasks.getList());
            ui.onDone(doneTask);
            break;
        case "delete":
            if (this.idx >= tasks.size()) {
                throw new DukeException("Oh dear! That task doesn't exist!");
            }
            Task rmTask = tasks.remove(this.idx);
            storage.save(tasks.getList());
            ui.onDelete(rmTask, tasks.size());
            break;
        case "todo":
            Task newTodo = tasks.addTodo(this.desc, false);
            storage.save(tasks.getList());
            ui.onAdd(newTodo, tasks.size());
            break;
        case "deadline":
        default:
            Task newTask = this.time == null
                    ? tasks.addTimedTask(this.type, this.desc, this.date, false)
                    : tasks.addTimedTask(this.type, this.desc, this.date, this.time, false);
            storage.save(tasks.getList());
            ui.onAdd(newTask, tasks.size());
            break;
        }
    }

    /**
     * Checks whether a given command equals this command.
     *
     * @param o object to be compared with this command.
     * @return true if they are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Command) {
            Command c = (Command) o;
            return this.type.equals(c.type);
        } else {
            return false;
        }
    }
}
