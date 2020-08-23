import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList;
    Storage store;

    public TaskList(Storage store) {
        this.taskList = store.load();
        this.store = store;
    }

    private int taskListLen() {
        return this.taskList.size();
    }

    public String readList() {
        if(this.taskListLen() == 0) {
            return "MUG don't have any of your task \"_\"";
        } else {
            int taskId = 1;
            StringBuilder results = new StringBuilder("Here is your tasks:\n");
            for (Task tsk : this.taskList) {
                results.append(taskId);
                results.append(". ");
                results.append(tsk);
                if (taskId != this.taskListLen()) {
                    results.append("\n");
                }
                taskId++;
            }
            return results.toString();
        }
    }

    public String addTask(Command command, String info) {
        try {
            Task task;
            switch (command) {
                case TODO:
                    task = new Todo(info);
                    break;
                case DEADLINE:
                    String[] dInfo = info.split(" /by ");
                    Parser.input(command, dInfo.length, true);
                    Parser.info(command, dInfo[1], true);
                    String deadlineEvent = dInfo[0];
                    LocalDate deadlineTime = Parser.date(dInfo[1]);
                    task = new Deadline(deadlineEvent, deadlineTime);
                    break;
                case EVENT:
                    String[] eInfo = info.split(" /at ");
                    Parser.input(command, eInfo.length, true);
                    Parser.info(command, eInfo[1], true);
                    String eventEvent = eInfo[0];
                    LocalDate eventTime = Parser.date(eInfo[1]);
                    task = new Event(eventEvent, eventTime);
                    break;
                default:
                    task = new Task(info);
                    break;
            }
            this.taskList.add(task);
            store.appendTask(command, info);

            return "Got it. MUG has added this task:\n"
                    + task;

        } catch (DukeException ex) {
            return ex.getMessage();
        }
    }

    public String deleteTask(int taskId) {
        if ( taskId > this.taskListLen()) {
            return "MUG don't have this task to Delete @_@";
        } else {
            try {
                int taskIndex = taskId - 1;
                Task deletedTask = this.taskList.get(taskIndex);
                this.taskList.remove(taskIndex);
                this.store.deleteTask(taskId);

                return "Noted. MUG has removed this task:\n"
                        + deletedTask;
            } catch (DukeException ex) {
                return ex.getMessage();
            }
        }
    }

    public String taskDone(int taskId) {
        if (taskId > this.taskListLen()) {
            return "MUG don't have this task to mark as Done :>";
        } else {
            int taskIndex = taskId - 1;
            Task doneTask = this.taskList.get(taskIndex);
            if (doneTask.isDone) {
                return "MUG had marked this task as done:\n"
                        + doneTask;
            } else {
                try {
                    doneTask = doneTask.markAsDone();
                    this.taskList.set(taskIndex, doneTask);
                    this.store.doneTask(taskId);
                    return "Congratz! MUG has marked this task as done:\n"
                            + doneTask;
                } catch (DukeException ex) {
                    return ex.getMessage();
                }
            }
        }
    }
}
