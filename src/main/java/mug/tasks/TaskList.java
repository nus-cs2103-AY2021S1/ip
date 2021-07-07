package mug.tasks;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import mug.command.Command;
import mug.mugexception.MugException;
import mug.storage.UndoStorage;
import mug.validator.Validator;

/**
 * Operation for the list of Task.
 */
public class TaskList {
    /** ArrayList of Task */
    private ArrayList<Task> taskList;

    /**
     * Constructs TaskList object with local Storage given.
     *
     * @param taskList List of Tasks
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

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
            return "Mug don't have any of your task \"_\"";
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
                task = addTodo(info);
                break;
            case DEADLINE:
                task = addDeadline(info);
                break;
            case EVENT:
                task = addEvent(info);
                break;
            default:
                task = new Task(info);
                break;
            }
            this.taskList.add(task);

            return "Got it. Mug has added this task:\n"
                    + task
                    + "\nNow you have "
                    + this.taskListLen()
                    + " tasks in the list.";

        } catch (MugException ex) {
            return ex.getMessage();
        }
    }

    private Task addTodo(String info) {
        return new Todo(info);
    }

    private Task addDeadline(String info) throws MugException {
        String[] deadlineInfo = info.split(" /by ");
        //Validate info
        Validator.input(Command.DEADLINE, deadlineInfo.length, true);
        assert(deadlineInfo.length > 1);
        Validator.info(Command.DEADLINE, deadlineInfo[1], true);
        // info
        String deadlineEvent = deadlineInfo[0];
        LocalDate deadlineTime = Validator.date(deadlineInfo[1]);
        return new Deadline(deadlineEvent, deadlineTime);
    }

    private Task addEvent(String info) throws MugException {
        String[] eventInfo = info.split(" /at ");
        //Validate info
        Validator.input(Command.EVENT, eventInfo.length, true);
        assert(eventInfo.length > 1);
        Validator.info(Command.EVENT, eventInfo[1], true);
        // info
        String eventEvent = eventInfo[0];
        LocalDate eventTime = Validator.date(eventInfo[1]);
        return new Event(eventEvent, eventTime);
    }

    /**
     * Deletes Task.
     *
     * @param taskId Task index.
     * @return Message when task delete.
     */
    public String deleteTask(int taskId) {
        if (taskId > this.taskListLen() || taskId < 1) {
            return "Mug don't have this task to Delete @_@";
        } else {
            //assert
            assert(taskId <= this.taskListLen());
            int taskIndex = taskId - 1;
            Task deletedTask = this.taskList.get(taskIndex);
            this.taskList.remove(taskIndex);

            return "Noted. Mug has removed this task:\n"
                    + deletedTask
                    + "\nNow you have "
                    + this.taskListLen()
                    + " tasks in the list.";
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
            return "Mug don't have this task to mark as Done :>";
        } else {
            //assert
            assert(taskId <= this.taskListLen());
            int taskIndex = taskId - 1;
            Task doneTask = this.taskList.get(taskIndex);
            if (doneTask.isDone()) {
                return "Mug had marked this task as done:\n"
                        + doneTask;
            } else {
                doneTask = doneTask.markAsDone();
                this.taskList.set(taskIndex, doneTask);

                return "Congratz! Mug has marked this task as done:\n"
                        + doneTask;
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
            return "Opps!! Mug don't have the task you searching :)";
        } else {
            return results.toString();
        }
    }

    /**
     * Undo the most recent edited task(add, delete, done).
     * @return Undo Status
     */
    public String undoTask(String mugFile, String undoFile) {
        try {
            UndoStorage store = new UndoStorage(mugFile, undoFile);
            store.undo();
            this.taskList = store.load();
            return "Mug has undo successfully :D";
        } catch (MugException ex) {
            return ex.getMessage();
        }
    }
}
