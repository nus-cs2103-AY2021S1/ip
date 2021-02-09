package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.todo.Task;

/**
 * Represents a list of tasks. A task list supports various modifications of the task list.
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    private void checkTaskList(int taskNo) throws DukeException {
        if (tasks.size() <= 0) {
            throw new DukeException(Ui.EMPTY_TASK_LIST_MSG);
        }

        if (taskNo > tasks.size()) {
            throw new DukeException(Ui.INVALID_TASK_NO_MSG);
        }
    }

    private void checkTaskList() throws DukeException {
        if (tasks.size() <= 0) {
            throw new DukeException(Ui.EMPTY_TASK_LIST_MSG);
        }
    }
    /**
     * Lists out all the tasks in the list.
     * @return A string that shows all tasks.
     */
    public String listTasks() {
        assert tasks.size() >= 0 : "Number of tasks cannot be negative";
        try {
            checkTaskList();
            String response = Ui.LIST_TASK_MSG + "\n";
            for (int i = 0; i < tasks.size(); i++) {
                int taskNo = i + 1;
                Task task = tasks.get(i);
                response = response + taskNo + "." + task + "\n";
            }
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Lists out all the tasks on a specific date.
     * @param date The specific date.
     * @return A string that shows the tasks on the specific date.
     */
    public String listTasksOn(LocalDate date) {
        try {
            checkTaskList();
            ArrayList<Task> taskList = new ArrayList<>();
            for (Task task : tasks) {
                LocalDate taskDate = task.getDate();
                if (taskDate != null) {
                    if (taskDate.isEqual(date)) {
                        taskList.add(task);
                    }
                }
            }

            String response;
            if (taskList.size() > 0) {
                response = "Here are the tasks happening on: " + date.format(formatter) + "\n";
                for (int i = 0; i < taskList.size(); i++) {
                    int taskNo = i + 1;
                    Task task = taskList.get(i);
                    response = response + taskNo + "." + task + "\n";
                }
            } else {
                response = "You don't have anything on: " + date.format(formatter) + " :)))\n";
            }
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Marks the task as done.
     * @param taskNo The task number in the list.
     * @return A string that shows the done task.
     */
    public String doneTask(int taskNo) {
        assert taskNo >= 0 : "taskNo cannot be negative";
        try {
            checkTaskList(taskNo);
            Task completedTask = tasks.get(taskNo - 1);
            completedTask.markAsDone();
            String response = Ui.DONE_MSG + "\n"
                    + " " + " "
                    + completedTask + "\n";
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Adds a task to the task list.
     * @param newTask The new task to add.
     * @return A string that shows the new task.
     */
    public String addTask(Task newTask) {
        assert newTask != null : "newTask cannot be null";
        tasks.add(newTask);
        String response = Ui.ADD_MSG + "\n"
                + newTask + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.\n";
        return response;
    }

    /**
     * Deletes a task on the task list.
     * @param taskNo The task number of the to be deleted task on the list.
     * @return A string that shows the deleted task.
     */
    public String deleteTask(int taskNo) {
        assert taskNo >= 0 : "taskNo cannot be negative";
        try {
            checkTaskList(taskNo);
            if (tasks.size() <= 0) {
                throw new DukeException("Nothing to delete.");
            } else if (taskNo > tasks.size()) {
                throw new DukeException(Ui.INVALID_TASK_NO_MSG);
            } else {
                String response;
                Task taskToBeDeleted = tasks.get(taskNo - 1);
                tasks.remove(taskNo - 1);
                response = Ui.DELETE_MSG + "\n"
                        + " " + " "
                        + taskToBeDeleted + "\n"
                        + "Now you have " + tasks.size() + " tasks in the list.\n";
                return response;
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Searches tasks that match the keyword.
     * @param keyword The keyword provided by the user.
     * @return A string that shows the matching task.
     */
    public String searchKeyword(String keyword) {
        assert keyword != "" : "keyword cannot be empty";
        try {
            checkTaskList();
            ArrayList<Task> taskList = new ArrayList<>();
            for (Task task : tasks) {
                if (task.getDescription().contains(keyword)) {
                    taskList.add(task);
                }
            }

            String response;
            if (taskList.size() > 0) {
                response = "Here are the matching tasks in your list:\n";
                for (int i = 0; i < taskList.size(); i++) {
                    int taskNo = i + 1;
                    Task task = taskList.get(i);
                    response = response + taskNo + "." + task + "\n";
                }
            } else {
                response = "You don't have anything related to " + "\"" + keyword + "\"" + "\n";
            }
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Update the date of a task specified by taskNo.
     * @param taskNo The task number.
     * @param date The new date.
     * @return A string that shows the updated task.
     */
    public String updateDate(int taskNo, LocalDate date) {
        assert taskNo >= 0 : "taskNo cannot be negative";
        try {
            checkTaskList(taskNo);
            String response;
            Task taskToBeUpdated = tasks.get(taskNo - 1);
            boolean flag = taskToBeUpdated.setDate(date);
            if (flag) {
                response = "Date is updated successfully" + "\n"
                        + " " + " "
                        + taskToBeUpdated + "\n";
            } else {
                response = "Update is unsuccessful T.T. Todo tasks have no date";
            }
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Update the description of a task specified by taskNo.
     * @param taskNo The task number.
     * @param content The new description.
     * @return A string that shows the updated task.
     */
    public String updateTaskDescription(int taskNo, String content) {
        assert taskNo >= 0 : "taskNo cannot be negative";
        try {
            checkTaskList(taskNo);
            String response;
            Task taskToBeUpdated = tasks.get(taskNo - 1);
            taskToBeUpdated.setDescription(content);
            response = "Description is updated successfully" + "\n"
                    + " " + " "
                    + taskToBeUpdated + "\n";
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Mark a done task as undone.
     * @param taskNo The task number.
     * @return A string that shows the updated task.
     */
    public String undoTask(int taskNo) {
        assert taskNo >= 0 : "taskNo cannot be negative";
        try {
            checkTaskList(taskNo);
            String response;
            Task taskToBeUpdated = tasks.get(taskNo - 1);
            taskToBeUpdated.undo();
            response = "The following task is marked as undone:" + "\n"
                    + " " + " "
                    + taskToBeUpdated + "\n";
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
