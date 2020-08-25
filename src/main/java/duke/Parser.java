package duke;
import duke.task.TaskList;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Todo;
import duke.task.Event;
import duke.task.TimeParser;
public class Parser {
    public static TaskList taskList;

    public static void setTaskList(TaskList list) {
        taskList = list;
    }

    public static boolean stopProgram(String command) {
        return command.equals("bye");
    }

    public static void parseCommand(String command) throws DukeException {
        if (command.equals("list")) {
            taskList.printList();
        } else {
            String[] strArr = command.split(" ", 2);
            String taskType = strArr[0];
            if (taskType.equals("done")) {
                int taskNumber = Integer.parseInt(strArr[1]);
                taskList.markTaskAsDone(taskNumber);
            } else if (taskType.equals("delete")) {
                int taskNumber = Integer.parseInt(strArr[1]);
                if (taskNumber <= taskList.getNumOfTask()) {
                    taskList.deleteTask(taskNumber);
                } else {
                    throw new DukeException("You only have " + taskList.getNumOfTask() + " tasks in your task list.");
                }
            } else if (taskType.equals("todo")) {
                if (strArr.length == 1) {
                    throw new DukeException("The description of a todo cannot be empty.");
                } else {
                    Todo todo = new Todo(strArr[1]);
                    taskList.addTask(todo);
                    taskList.printAddedTask(todo);
                }
            } else if (taskType.equals("deadline")) {
                if (strArr.length == 1) {
                    throw new DukeException("The description of a deadline cannot be empty.");
                } else {
                    String[] deadlineArr = strArr[1].split(" /by ", 2);
                    if (deadlineArr.length != 2) {
                        throw new DukeException("Wrong format when describing a deadline.");
                    } else {
                        if (TimeParser.isValidTime(deadlineArr[1])) {
                            String deadlineTime = TimeParser.parseTime(deadlineArr[1]);
                            Deadline deadline = new Deadline(deadlineArr[0], deadlineTime);
                            taskList.addTask(deadline);
                            taskList.printAddedTask(deadline);
                        } else {
                            throw new DukeException("Wrong format when describing a date.");
                        }
                    }
                }
            } else if (taskType.equals("event")) {
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
                            taskList.printAddedTask(event);
                        } else {
                            throw new DukeException("Wrong format when describing a date.");
                        }
                    }
                }
            } else {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
