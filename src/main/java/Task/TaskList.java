package task;

import java.util.ArrayList;
import exceptions.DukeException;
import exceptions.InvalidCommandException;
import exceptions.MissingSpecifiedDeleteError;
import exceptions.NoDateException;
import exceptions.NoTaskException;
import exceptions.WrongDateTimeFormatException;
import exceptions.WrongIndexError;

public class TaskList {

    /**
     * The list of tasks
     **/
    private final ArrayList<Task> tasks;

    /**
     * The task that have just been added or deleted or done from the list
     **/
    private final Task addedOrDeletedTask;

    /**
     * Initialize TaskList
     **/
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.addedOrDeletedTask = null;
    }

    /**
     * Initializes the tasklist
     *
     * @param tasks
     * @throws DukeException
     */
    public TaskList(ArrayList<Task> tasks) throws DukeException {

        this.tasks = tasks;
        this.addedOrDeletedTask = null;
    }

    /**
     * Initializes the tasklist
     *
     * @param tasks
     * @param addedOrDeletedTask
     */
    public TaskList(ArrayList<Task> tasks, Task addedOrDeletedTask) {
        this.tasks = tasks;
        this.addedOrDeletedTask = addedOrDeletedTask;
    }

    private static Task assignTask(String type, String name)
        throws NoTaskException, InvalidCommandException, NoDateException, WrongDateTimeFormatException {
        try {
            if (type.equals("todo")) {
                try {
                    return new Todo(name.substring(5), false);
                } catch (IndexOutOfBoundsException e) {
                    throw new NoTaskException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
            } else {
                int indexOfCommand = name.indexOf("/");
                String deadline = name.substring(indexOfCommand + 4);
                if (type.equals("deadline")) {
                    try {
                        String currname = name.substring(9);
                        if (indexOfCommand > -1) {
                            try {
                                return new Deadline(name.substring(9, indexOfCommand - 1), false, deadline);
                            } catch (WrongDateTimeFormatException e) {
                                throw new WrongDateTimeFormatException(e.getMessage());
                            }
                        } else {
                            throw new NoDateException("☹ OOPS!!! Please specify the deadline!");
                        }
                    } catch (IndexOutOfBoundsException e) {
                        throw new NoTaskException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                } else {
                    try {
                        String currname = name.substring(6);
                        if (indexOfCommand > -1) {
                            return new Event(name.substring(6, indexOfCommand - 1), false, deadline);
                        } else {
                            throw new NoDateException("☹ OOPS!!! Please specify when the event is going to be held!");
                        }
                    } catch (IndexOutOfBoundsException e) {
                        throw new NoTaskException("☹ OOPS!!! The description of a event cannot be empty.");
                    }
                }
            }
        } catch (NullPointerException e) {
            throw new InvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static int decideIndexDelete(String word) throws MissingSpecifiedDeleteError {
        int index = 0;
        try {
            index = Integer.parseInt(word.substring(7));
        } catch (IndexOutOfBoundsException e) {
            throw new MissingSpecifiedDeleteError("☹ OOPS!!! Please specify which task you want to delete.");
        }
        return index;
    }

    private static Task deletedTask(int index, ArrayList<Task> tasks) throws WrongIndexError {
        Task curr = null;
        try {
            curr = tasks.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new WrongIndexError("☹ OOPS!!! You only have " + tasks.size() + " tasks in your list. " +
                "Please select a valid task to be deleted.");
        }
        return curr;
    }

    /**
     * Get the arraylist of tasks inside the TaskList
     **/
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Get the number of tasks currently from the list
     **/
    public int taskSize() {
        return this.tasks.size();
    }

    /**
     * Get the task that has just been deleted or added or done from the list
     **/
    public Task getAddedOrDeletedTask() {
        return this.addedOrDeletedTask;
    }

    /**
     * Delete the task from the list
     *
     * @param task
     * @return
     * @throws WrongIndexError
     * @throws MissingSpecifiedDeleteError
     */
    public TaskList delete(String task) throws WrongIndexError, MissingSpecifiedDeleteError {
        int num = 0;
        ArrayList<Task> tempTasks = this.tasks;
        Task removed = null;
        try {
            num = decideIndexDelete(task);
            try {
                removed = deletedTask(num, tasks);
                tempTasks.remove(num - 1);
            } catch (WrongIndexError e) {
                throw new WrongIndexError(e.getMessage());
            }
        } catch (MissingSpecifiedDeleteError e) {
            throw new MissingSpecifiedDeleteError(e.getMessage());
        }
        return new TaskList(tempTasks, removed);

    }

    /**
     * Add the specified task to the list
     *
     * @param task
     * @return
     * @throws NoTaskException
     * @throws InvalidCommandException
     * @throws NoDateException
     * @throws WrongDateTimeFormatException
     */
    public TaskList add(String task)
        throws NoTaskException, InvalidCommandException, NoDateException, WrongDateTimeFormatException {
        String firstWord = task.toLowerCase().contains("todo") ? "todo"
            : task.toLowerCase().contains("deadline") ? "deadline"
            : task.toLowerCase().contains("event") ? "event"
            : null;

        Task curr = null;
        ArrayList<Task> tempTasks = this.tasks;
        try {
            curr = assignTask(firstWord, task);
            tempTasks.add(curr);
        } catch (NoTaskException e) {
            throw new NoTaskException(e.getMessage());
        } catch (InvalidCommandException e) {
            throw new InvalidCommandException(e.getMessage());
        } catch (NoDateException e) {
            throw new NoDateException(e.getMessage());
        } catch (WrongDateTimeFormatException e) {
            throw new WrongDateTimeFormatException(e.getMessage());
        }
        return new TaskList(tempTasks, curr);
    }

    /**
     * Set the specified task to done
     *
     * @param done
     * @return
     * @throws WrongIndexError
     */
    public TaskList done(String done) throws WrongIndexError {

        try {
            int num = Integer.parseInt(done.substring(5));
            ArrayList<Task> tempTask = this.tasks;
            Task curr = tempTask.get(num - 1).setToTrue();
            tempTask.set(num - 1, curr);
            curr = tasks.get(num - 1);

            return new TaskList(tempTask, curr);
        } catch (IndexOutOfBoundsException e) {
            throw new WrongIndexError("☹ OOPS!!! You only have " + tasks.size() + " tasks in your list. " + "\n"
                + "Please select a valid task to be done.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof TaskList) {
            TaskList temp = (TaskList) o;
            if (this.taskSize() == temp.taskSize()) {
                boolean isEqual = true;
                for (int i = 0; i < this.taskSize(); i++) {
                    if (!this.tasks.get(i).equals(temp.tasks.get(i))) {
                        return false;
                    }
                }
                return this.addedOrDeletedTask.equals(temp.addedOrDeletedTask);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
