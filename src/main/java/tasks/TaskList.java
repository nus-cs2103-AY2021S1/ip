package tasks;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import command.Command;
import mugexception.MugException;
import validator.Validator;
import storage.Storage;
import storage.UndoStorage;

/**
 * Operation for the list of Task.
 */
public class TaskList {
    /** ArrayList of Task */
    private ArrayList<Task> taskList;
    /** Local storage that store list of task */
    private final Storage store;
    /** undo Storage action */
    private final UndoStorage undoStore;

    /**
     * Constructs TaskList object with local Storage given.
     *
     * @param store Local Storage that store list of task
     */
    public TaskList(Storage store) {
        this.taskList = store.load();
        this.store = store;
        this.undoStore = new UndoStorage("mug.txt", "undo.txt");
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
            StringBuilder results = new StringBuilder("Here is your tasks:\n");
            IntStream.range(0, this.taskListLen())
                    .forEach(taskIndex -> {
                        results.append(taskIndex + 1);
                        results.append(". ");
                        results.append(this.taskList.get(taskIndex));
                        if (taskIndex != this.taskListLen()) {
                            results.append("\n");
                        }
                    });
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
                String[] deadlineInfo = info.split(" /by ");
                //Validate info
                Validator.input(command, deadlineInfo.length, true);
                assert(deadlineInfo.length > 1);
                Validator.info(command, deadlineInfo[1], true);
                // info
                String deadlineEvent = deadlineInfo[0];
                LocalDate deadlineTime = Validator.date(deadlineInfo[1]);
                task = new Deadline(deadlineEvent, deadlineTime);
                break;
            case EVENT:
                String[] eventInfo = info.split(" /at ");
                //Validate info
                Validator.input(command, eventInfo.length, true);
                assert(eventInfo.length > 1);
                Validator.info(command, eventInfo[1], true);
                // info
                String eventEvent = eventInfo[0];
                LocalDate eventTime = Validator.date(eventInfo[1]);
                task = new Event(eventEvent, eventTime);
                break;
            default:
                task = new Task(info);
                break;
            }
            this.taskList.add(task);
            // append task to local storage
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
        if (taskId > this.taskListLen() || taskId < 1) {
            return "MUG don't have this task to Delete @_@";
        } else {
            //assert
            assert(taskId <= this.taskListLen());
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
        if (taskId > this.taskListLen() || taskId < 1) {
            return "MUG don't have this task to mark as Done :>";
        } else {
            //assert
            assert(taskId <= this.taskListLen());
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
        AtomicInteger taskNum = new AtomicInteger();
        StringBuilder results = new StringBuilder("Here is the result:");
        IntStream.range(0, this.taskListLen())
                .forEach(taskIndex -> {
                    Task task = this.taskList.get(taskIndex);
                    boolean hasKeyword = task.getDescription().contains(keyword);
                    if (hasKeyword) {
                        taskNum.getAndIncrement();
                        results.append("\n");
                        results.append(taskNum.get());
                        results.append(". ");
                        results.append(task);
                    }
                });
        if (taskNum.get() == 0) {
            return "Opps!! MUG don't have the task you searching :)";
        } else {
            return results.toString();
        }
    }

    /**
     * Undo the most recent edited task(add, delete, done).
     * @return Undo Status
     */
    public String undoTask() {
        try {
            this.undoStore.undo();
            this.taskList = this.store.load();
            return "Mug has undo successfully :D";
        } catch (MugException ex) {
            return ex.getMessage();
        }
    }
}
