/**
 * Ui class deals with interactions with the user.
 */
public class Ui {
    /**
     * Constructor that creates a Ui object.
     */
    public Ui() {}
    
    /**
     * Returns a greeting message
     * @return returns a String containing a greeting message.
     */
    public static String showGreetingMessage() {
        return "Hello! I'm the Captain" + "\n" + "Who lives in a pineapple under the sea" + "\n";
    }

    /**
     * Returns a goodbye message
     * @return returns a String containing a goodbye message.
     */
    public static String showGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns a message to inform the user that a Task has been added to TaskList.
     * @param task the Task to be added to TaskList.
     * @param taskList the Tasklist to add the Task to.
     * @return returns a String containing information about the task added.
     */
    public String showAddTaskMessage(Task task, TaskList taskList) {
        return "Got it. I've added this task: " + "\n"
                + task.toString() + "\n"
                + String.format("Now you have %s patties to flip.", taskList.size());
    }

    /**
     * Returns a message to inform the user that a Task has been deleted from the TaskList.
     * @param index the index of the Task to be deleted.
     * @param taskList the TaskList to delete the Task from.
     * @return returns a String containing information about the task deleted.
     */
    public String showDeleteTaskMessage(int index, TaskList taskList) {
        return "Noted. I've removed this patty: " + "\n"
                + taskList.getTask(index).toString() + "\n"
                + String.format("Now you have %s patties to flip.", taskList.size() - 1);
    }

    /**
     * Returns a message to inform the user of an error in the application.
     * @param e the Exception that was thrown by the application.
     * @return returns a String containing information about the error.
     */
    public String showErrorMessage(Exception e) {
        return "Arghhhh there seems to be an error: " + e;
    }

    /**
     * Overloaded method which also returns a specified message to inform the user
     * of an error in the application.
     * @param message the message that the application wants to print for the user.
     * @return returns a String containing information about the error.
     */
    public String showErrorMessage(String message) {
        return "Arghhhh there seems to be an error: " + message;
    }

    /**
     * Returns all Tasks currently in the TaskList.
     * @param taskList the TaskList which contain the Tasks to be printed out.
     * @return returns a String containing information about the tasks that exist currently.
     */
    public String showCurrentTasks(TaskList taskList) {
        StringBuilder output = new StringBuilder();
        if(taskList.size() > 0) {
            output.append("Here are the patties on your cooking station:" + "\n");
            for (int i = 1; i <= taskList.size(); i++) {
                //print out task with numbering
                output.append(String.format("%s. %s", i, taskList.getTask(i).toString())).append("\n");
            }
        } else { //no tasks
            output.append("You have no more patties to flip!");
        }
        return output.toString();
    }

    /**
     * Returns a specified message to inform the user that an operation succeeded.
     * @param message the message to be printed out.
     * @return returns a String to inform the user that an operation succeeded.
     */
    public String showSuccessMessage(String message) {
        return "Success: " + message;
    }

    /**
     * Returns a message to inform the user that a specific Task in the TaskList
     * has been marked as completed.
     * @param index the index of the Task in the TaskList.
     * @param taskList the TaskList which contains the Task to be marked as completed.
     * @return returns a String to inform the user that a specific Task in the TaskList
     * has been marked as completed.
     */
    public String showTasksAfterMarkDone(int index, TaskList taskList) {
        assert(index < taskList.size());
        Task task = taskList.getTask(index);
        return "Nice! I've marked this patty as cooked:" +  "\n" +
                task.toString();
    }

    /**
     * Returns a list of Tasks in the current TaskList that matches a specified word.
     * @param word the word to search for in the TaskList.
     * @param taskList the TaskList which contain the Tasks to be filtered
     * according to whether it contains the specified word.
     * @return returns a String containing a list of Tasks in the current
     * TaskList that matches a specified word.
     */
    public String findTask(String word, TaskList taskList) {
        assert(!word.isEmpty());
        assert(taskList.size() > 0);
        TaskList temp = new TaskList();
        for(int i = 1; i <= taskList.size(); i++) {
            Task task = taskList.getTask(i);
            if(task.getName().contains(word)) {
                temp.addTask(task);
            }
        }
        StringBuilder output = new StringBuilder();
        if(temp.size() > 0) {
            output.append("Here these patties match your description:").append("\n");
            for (int i = 1; i <= temp.size(); i++) {
                //print out task with numbering
                output.append(String.format("%s. %s%n", i, temp.getTask(i).toString())).append("\n");
            }
        } else { //no matching tasks
            output.append("You have no matching patties!");
        }
        return output.toString();
    }
}