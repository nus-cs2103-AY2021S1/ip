package duke;

import java.util.List;

import duke.command.Command;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * An agent to process incoming command and return the feedback.
 */
public class CommandAgent {
    private static Storage storage;
    private static TaskList taskList;

    public CommandAgent(Storage storage) {
        CommandAgent.storage = storage;
        CommandAgent.taskList = storage.load();
    }

    public static int listSize() {
        return taskList.getSize();
    }

    /**
     * Handle the command taken from user input, execute it
     * and save the updated task list data to hard disk.
     *
     * @param command the command parsed from user input.
     * @return the feedback generated from executing the command;
     */
    public String handleCommand(Command command) {
        String feedback = executeCommand(command);
        storage.save(taskList);
        return feedback;
    }

    /**
     * Takes in the command and execute it based on the request from the command.
     *
     * @param command the command parsed from user input.
     * @return a String feedback for the user.
     */
    public String executeCommand(Command command) {
        String feedback = "____________________________________________________________\n";
        int taskId;
        switch (command.sendRequest()) {
        case "error":
            List<String> errorMessage = command.getContent();
            feedback += errorMessage.get(0);
            break;
        case "end":
            feedback += "Bye. Hope to see you again soon!";
            break;
        case "create":
            List<String> taskInfo = command.getContent();
            Task newTask = this.createTask(taskInfo);
            taskList = taskList.addTask(newTask);
            feedback += this.generateCreateFeedback();
            break;
        case "retrieval":
            feedback += this.generateRetrievalFeedback();
            break;
        case "update":
            List<String> updateList = command.getContent();
            taskId = Integer.parseInt(updateList.get(0));
            taskList = taskList.markAsDone(taskId);
            feedback += this.generateUpdateFeedback(taskId);
            break;
        case "delete":
            List<String> deleteList = command.getContent();
            taskId = Integer.parseInt(deleteList.get(0));
            Task deletedTask = taskList.getTaskById(taskId);
            taskList = taskList.deleteTask(taskId);
            feedback += this.generateDeleteFeedback(deletedTask);
            break;
        default:
            feedback += "";
        }
        feedback += "\n____________________________________________________________\n";
        return feedback;
    }

    /**
     * Create different types of tasks based on the identifier stored in the taskInfo.
     * Set default option to todo task.
     * The CommandReader has ensured no erroneous keyword will be sent to agent.
     *
     * @param taskInfo a list of String containing all the relevant information for the task.
     * @return a correct type of Task object.
     */
    public Task createTask(List<String> taskInfo) {
        String identifier = taskInfo.get(0);
        String name = taskInfo.get(1);
        String schedule;
        switch (identifier) {
        case "E":
            schedule = taskInfo.get(2);
            return new Event(name, false, schedule);
        case "D":
            schedule = taskInfo.get(2);
            return new Deadline(name, false, schedule);
        default:
            return new Todo(name, false);
        }
    }

    /**
     * Generate the feedback for a task creation.
     * @return a String suggesting the completion of task creation.
     */
    public String generateCreateFeedback() {
        int taskId = taskList.getSize();
        Task currentTask = taskList.getTaskById(taskId);
        String result = "Got it. I've added this task:\n  ";
        result += currentTask;
        result += String.format("\nNow you have %d tasks in the list.", taskId);
        return result;
    }

    /**
     * Generate the feedback for an update of task status.
     *
     * @param taskId the displayed id in the taskList.
     * @return a String suggesting the completion of task update.
     */
    public String generateUpdateFeedback(int taskId) {
        Task currentTask = taskList.getTaskById(taskId);
        return "Nice! I've marked this task as done:\n  " + currentTask;
    }

    /**
     * Generate the feedback for a retrieval of tasks information.
     * @return a String showing all the task information.
     */
    public String generateRetrievalFeedback() {
        return taskList.printTasks();
    }

    /**
     * Generate the feedback for a delete of task from the task list.
     *
     * @param deletedTask the delete task.
     * @return a String suggesting the task has been deleted.
     */
    public String generateDeleteFeedback(Task deletedTask) {
        String result = "Noted. I've removed this task:\n  ";
        result += deletedTask;
        result += String.format("\nNow you have %d tasks in the list.", listSize());
        return result;
    }
}
