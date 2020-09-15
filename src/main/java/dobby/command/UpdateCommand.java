package dobby.command;

import dobby.DobbyException;
import dobby.TaskList;
import dobby.task.Task;
import dobby.task.TimedTask;
import dobby.task.Todo;

public class UpdateCommand implements Command {
    protected static final String USAGE = "update _task_number_ desc: _new_description_(optional) "
            + "date: _new_date_(optional) time: _new_time_(optional)";


    @Override
    public String parseInput(TaskList tasks, String text) throws DobbyException {
        String message = "";
        assert text.startsWith("update") : "Update command must start with update";
        System.out.println(text);
        try {
            int commandLen = "update".length();
            text = text.substring(commandLen + 1).trim();

            int taskIndex = (int) (text.charAt(0)) - 48;
            System.out.println("Task index: " + taskIndex);
            if (taskIndex > tasks.getSize()) {
                throw new DobbyException("Incorrect usage of command.\n"
                        + "Task number must be within the correct range. Please try again.");
            }
            Task task = tasks.getTask(taskIndex - 1);
            text = text.substring(2).trim();
            if (task instanceof Todo) {
                System.out.println("updating todo");
                message = updateTodo(tasks, text, (Todo) task, taskIndex);
            } else if (task instanceof TimedTask) {
                System.out.println("updating timed task");
                message = updateTimedTask(tasks, text, (TimedTask) task, taskIndex);
            }

        } catch (StringIndexOutOfBoundsException e) {
            throw new DobbyException("Incorrect usage of command.");
        } catch (DobbyException e) {
            return e.getMessage();
        }
        assert message != null : "Return message to user cannot be empty";
        return message;
    }

    /**
     * Updates the Todo task as required
     * @param tasks TaskList
     * @param text String input
     * @param task Task to be updated
     * @param index index of task in TaskList
     * @return message to reply to user
     * @throws DobbyException
     */
    private String updateTodo(TaskList tasks, String text, Todo task, int index) throws DobbyException {
        if (!text.startsWith("desc:")) {
            throw new DobbyException("Incorrect usage of command.");
        }
        assert text.startsWith("desc:") : "Ensure correct usage of command";

        String description = text.substring("desc:".length()).trim();
        if (description.length() == 0) {
            throw new DobbyException("Incorrect usage of command.\n  "
                    + "Description cannot be empty.");
        }
        assert description.length() > 0 : "Description cannot be empty";
        task.setDescription(description);
        return String.format("Updated task number %d:\n  %s", index, task.getDescription());
    }

    /**
     * Updates the Timed task as required
     * @param tasks TaskList
     * @param text String input
     * @param task Task to be updated
     * @param index index of task in TaskList
     * @return message to reply to user
     */
    private String updateTimedTask(TaskList tasks, String text, TimedTask task, int index) {
        try {
            boolean isUpdatingDescription = false;
            if (text.contains("desc:")) {
                isUpdatingDescription = true;
            }

            boolean isUpdatingDate = false;
            if (text.contains("date:")) {
                isUpdatingDate = true;
            }

            boolean isUpdatingTime = false;
            if (text.contains("time:")) {
                isUpdatingTime = true;
            }


            if (isUpdatingDescription) {
                text = parseDescription(task, text, isUpdatingDate, isUpdatingTime);
            }
            if (isUpdatingDate) {
                text = parseDate(task, text, isUpdatingTime);
            }
            if (isUpdatingTime) {
                parseTime(task, text);
            }

            if (!(isUpdatingDate || isUpdatingDescription || isUpdatingTime)) {
                throw new DobbyException("Incorrect usage of command.");
            }
            return String.format("Updated task number %d:\n  %s", index, task.getDescription());
        } catch (DobbyException e) {
            return e.getMessage();
        }
    }

    /**
     * Updates the description as required. Called only when updating a TimedTask
     * @param task TaskList
     * @param text String input
     * @param isUpdatingDate
     * @param isUpdatingTime
     * @return updated value of variable text
     * @throws DobbyException
     */
    private String parseDescription(TimedTask task, String text,
                                    boolean isUpdatingDate, boolean isUpdatingTime) throws DobbyException {

        String description;
        text = text.substring("desc:".length()).trim();
        if (isUpdatingDate) {
            description = text.substring(0, text.indexOf("date:") - 1);
            text = text.substring(text.indexOf("date:"));
        } else if (isUpdatingTime) {
            description = text.substring(0, text.indexOf("time:") - 1);
            text = text.substring(text.indexOf("time:"));
        } else {
            description = text;
        }
        if (description.length() == 0) {
            throw new DobbyException("Incorrect usage of command.\n  "
                    + "Description cannot be empty.");
        }
        assert description.length() > 0 : "Description cannot be empty";
        task.setDescription(description);
        return text;
    }

    /**
     * Updates the date as required. Called only when updating a TimedTask
     * @param task TaskList
     * @param text String input
     * @param isUpdatingTime
     * @return updated value of variable text
     * @throws DobbyException
     */
    private String parseDate(TimedTask task, String text, boolean isUpdatingTime) throws DobbyException {
        String date;
        text = text.substring("date:".length()).trim();
        if (isUpdatingTime) {
            date = text.substring(0, text.indexOf("time:") - 1);
            text = text.substring(text.indexOf("time:"));
        } else {
            date = text.trim();
        }
        if (date.length() == 0) {
            throw new DobbyException("Incorrect usage of command.\n  "
                    + "Date cannot be empty.");
        }
        assert date.length() > 0 : "Date cannot be empty";
        task.setDate(date);
        return text;
    }

    /**
     * Updates the time as required. Called only when updating a TimedTask
     * @param task TaskList
     * @param text String input
     * @throws DobbyException
     */
    private void parseTime(TimedTask task, String text) throws DobbyException {
        String time;
        text = text.substring("time:".length()).trim();
        time = text;
        if (time.length() == 0) {
            throw new DobbyException("Incorrect usage of command.\n  "
                    + "Time cannot be empty.");
        }
        assert time.length() > 0 : "Time cannot be empty";
        task.setTime(time);
    }
}
