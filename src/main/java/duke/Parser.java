package duke;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.TimeParser;
import duke.task.Todo;

public class Parser {
    private static TaskList taskList;

    /**
     * Sets taskList for Parser.
     * @param list TaskList that manages tasks.
     */
    public static void setTaskList(TaskList list) {
        taskList = list;
    }

    /**
     * Returns a boolean to indicate whether program should be stopped.
     * @param command Command from user input.
     * @return Returns a boolean.
     */
    public static boolean stopProgram(String command) {
        return command.equals("bye");
    }

    /**
     * Processes commands and add the tasks to taskList.
     * @param command Command from user input.
     * @throws DukeException DukeException if command is not in legal form.
     */
    public static String parseCommand(String command) throws DukeException {
        if (command.equals("bye")) {
            Storage.writeToFile(taskList);
            return Ui.getExitMessage();
        } else if (command.equals("list")) {
            return taskList.taskListToString();
        } else {
            String[] strArr = command.split(" ", 2);
            String taskType = strArr[0];
            switch (taskType) {
            case "done": {
                int taskNumber = Integer.parseInt(strArr[1]);
                return taskList.markTaskAsDone(taskNumber);
            }
            case "delete": {
                int taskNumber = Integer.parseInt(strArr[1]);
                if (taskNumber <= taskList.getNumOfTask()) {
                    return taskList.deleteTask(taskNumber);
                } else {
                    throw new DukeException("You only have " + taskList.getNumOfTask() + " tasks in your task list.");
                }
            }
            case "find":
                String keyword = strArr[1];
                return taskList.findTaskByKeyword(keyword);
            case "todo":
                if (strArr.length == 1) {
                    throw new DukeException("The description of a todo cannot be empty.");
                } else {
                    Todo todo = new Todo(strArr[1]);
                    taskList.addTask(todo);
                    return taskList.addedTaskToString(todo);
                }
            case "deadline":
                if (strArr.length == 1) {
                    throw new DukeException("The description of a deadline cannot be empty.");
                } else {
                    String[] deadlineArr = strArr[1].split(" /by ", 2);
                    if (deadlineArr.length != 2) {
                        throw new DukeException("Wrong format when describing a deadline.");
                    } else {
                        if (TimeParser.isValidTime(deadlineArr[1])) {
                            System.out.println(deadlineArr[1]);
                            String deadlineTime = TimeParser.parseTime(deadlineArr[1]);
                            Deadline deadline = new Deadline(deadlineArr[0], deadlineTime);
                            taskList.addTask(deadline);
                            return taskList.addedTaskToString(deadline);
                        } else {
                            throw new DukeException("Wrong format when describing a date.");
                        }
                    }
                }
            case "event":
                if (strArr.length == 1) {
                    throw new DukeException("The description of an event cannot be empty.");
                } else {
                    String[] eventArr = strArr[1].split(" /at ", 2);
                    if (eventArr.length != 2) {
                        throw new DukeException("Wrong format when describing an event.");
                    } else {
                        if (TimeParser.isValidTime(eventArr[1])) {
                            String eventTime = TimeParser.parseTime(eventArr[1]);
                            Event event = new Event(eventArr[0], eventTime);
                            taskList.addTask(event);
                            return taskList.addedTaskToString(event);
                        } else {
                            throw new DukeException("Wrong format when describing a date.");
                        }
                    }
                }
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    /**
     * Returns the taskList.
     * @return TaskList.
     */
    public static TaskList getTaskList() {
        return taskList;
    }
}
