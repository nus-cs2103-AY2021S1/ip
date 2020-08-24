package tasks;

import command.Command;
import mugexception.MugException;
import parser.Parser;
import storage.Storage;

import java.time.LocalDate;

import java.util.ArrayList;

/**
 * Operation for the list of Task.
 */
public class TaskList {
    /** ArrayList of Task */
    private final ArrayList<Task> taskList;
    /** Local storage that store list of task */
    private final Storage store;

    /**
     * Constructs TaskList object with local Storage given.
     *
     * @param store Local Storage that store list of task
     */
    public TaskList(Storage store) {
        this.taskList = store.load();
        this.store = store;
    }

    /**
     * Length of task's list
     *
     * @return Length of task's list
     */
    private int taskListLen() {
        return this.taskList.size();
    }

    /**
     * Reads list of Task.
     *
     * @return list of Task in String.
     */
    public String readList() {
        if (this.taskListLen() == 0) {
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

    /**
     * Adds Task.
     *
     * @param command User command.
     * @param info Task descriptions.
     * @return Message when task add.
     */
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
                    + task
                    + "\nNow you have "
                    + this.taskListLen()
                    + " tasks in the list.";

        } catch (MugException ex) {
            return ex.getMessage();
        }
    }

    /**
     * Deletes Task.
     *
     * @param taskId Task index.
     * @return Message when task delete.
     */
    public String deleteTask(int taskId) {
        if (taskId > this.taskListLen()) {
            return "MUG don't have this task to Delete @_@";
        } else {
            try {
                int taskIndex = taskId - 1;
                Task deletedTask = this.taskList.get(taskIndex);
                this.taskList.remove(taskIndex);
                this.store.deleteTask(taskId);

                return "Noted. MUG has removed this task:\n"
                        + deletedTask
                        + "\nNow you have "
                        + this.taskListLen()
                        + " tasks in the list.";
            } catch (MugException ex) {
                return ex.getMessage();
            }
        }
    }

    /**
     * Updates Task done.
     *
     * @param taskId Task index
     * @return Message when task update done.
     */
    public String taskDone(int taskId) {
        if (taskId > this.taskListLen()) {
            return "MUG don't have this task to mark as Done :>";
        } else {
            int taskIndex = taskId - 1;
            Task doneTask = this.taskList.get(taskIndex);
            if (doneTask.isDone()) {
                return "MUG had marked this task as done:\n"
                        + doneTask;
            } else {
                try {
                    doneTask = doneTask.markAsDone();
                    this.taskList.set(taskIndex, doneTask);
                    this.store.doneTask(taskId);
                    return "Congratz! MUG has marked this task as done:\n"
                            + doneTask;
                } catch (MugException ex) {
                    return ex.getMessage();
                }
            }
        }
    }

    /**
     * Searches the Task using the keyword provide.
     *
     * @param keyword Keyword to search.
     * @return List of Task with the search keyword
     */
    public String searchTask(String keyword) {
        int taskId = 0;
        StringBuilder results = new StringBuilder("Here is your results:");

        for (int i = 0; i < this.taskListLen(); i++) {
            Task task = this.taskList.get(i);
            boolean hasKeyword = task.getDescription().contains(keyword);
            if(hasKeyword) {
                taskId++;
                results.append("\n");
                results.append(taskId);
                results.append(". ");
                results.append(task);
            }
        }
        if (taskId == 0) {
            return "Opps!! MUG don't have the task you searching :)";
        } else {
            return results.toString();
        }
    }
}
