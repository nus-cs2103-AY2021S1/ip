package duke;

import java.time.LocalDate;
import java.time.LocalTime;

public class Command {

    private String type;
    private Integer idx;
    private String desc;
    private LocalDate date;
    private LocalTime time;

    public Command(String type) {
        this.type = type;
        this.idx = null;
        this.desc = null;
        this.date = null;
        this.time = null;
    }

    public Command(String type, int idx) {
        this.type = type;
        this.idx = idx;
        this.desc = null;
        this.date = null;
        this.time = null;
    }

    public Command(String type, String desc) {
        this.type = type;
        this.idx = null;
        this.desc = desc;
        this.date = null;
        this.time = null;
    }

    public Command(String type, String desc, LocalDate date) {
        this.type = type;
        this.idx = null;
        this.desc = desc;
        this.date = date;
        this.time = null;
    }

    public Command(String type, String desc, LocalDate date, LocalTime time) {
        this.type = type;
        this.idx = null;
        this.desc = desc;
        this.date = date;
        this.time = time;
    }

    public boolean isQuitting() {
        return this.type.equals("bye");
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        switch (this.type) {
            case "bye":
                ui.farewell();
                break;
            case "list":
                ui.onList(tasks.getList());
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
            case "event":
                Task newTask = this.time == null
                        ? tasks.addTimedTask(this.type, this.desc, this.date, false)
                        : tasks.addTimedTask(this.type, this.desc, this.date, this.time, false);
                storage.save(tasks.getList());
                ui.onAdd(newTask, tasks.size());
                break;
        }
    }

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
