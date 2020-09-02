/**
 * Ui class deals with interactions with the user.
 */
public class Ui {
    private static final String LINE = "______________________________________________" + "\n";
    
    /**
     * Constructor that creates a Ui object.
     */
    public Ui() {}
    
    /**
     * Returns a greeting message
     * @return returns a String containing a greeting message.
     */
    public String printHi() {
        String greeting = "Hello! I'm the Captain" + "\n" + "Who lives in a pineapple under the sea" + "\n";
        return LINE + greeting + LINE;
    }

    /**
     * Returns a goodbye message
     * @return returns a String containing a goodbye message.
     */
    public String printBye() {
        String goodbye = "Bye. Hope to see you again soon!";
        return LINE + goodbye + "\n" + LINE;
    }

    /**
     * Returns a message to inform the user that a Task has been added to TaskList.
     * @param task the Task to be added to TaskList.
     * @param taskList the Tasklist to add the Task to.
     * @return returns a String containing information about the task added.
     */
    public String printAdd(Task task, TaskList taskList) {
        return LINE
                + "Got it. I've added this task: " + "\n"
                + task.toString() + "\n"
                + String.format("Now you have %s patties to flip.", taskList.size()) + "\n"
                + LINE;
    }

    /**
     * Returns a message to inform the user that a Task has been deleted from the TaskList.
     * @param index the index of the Task to be deleted.
     * @param taskList the TaskList to delete the Task from.
     * @return returns a String containing information about the task deleted.
     */
    public String printDelete(int index, TaskList taskList) {
        return LINE
                + "Noted. I've removed this patty: " + "\n"
                + taskList.getTask(index).toString() + "\n"
                + String.format("Now you have %s patties to flip.", taskList.size() - 1) + "\n"
                + LINE;
    }

    /**
     * Returns a message to inform the user of an error in the application.
     * @param e the Exception that was thrown by the application.
     * @return returns a String containing information about the error.
     */
    public String printError(Exception e) {
        return LINE + "Arghhhh there seems to be an error: " + e + "\n" + LINE;
    }

    /**
     * Overloaded method which also returns a specified message to inform the user
     * of an error in the application.
     * @param message the message that the application wants to print for the user.
     * @return returns a String containing information about the error.
     */
    public String printError(String message) {
        return LINE + "Arghhhh there seems to be an error: " + message + "\n" + LINE;
    }

    /**
     * Returns all Tasks currently in the TaskList.
     * @param taskList the TaskList which contain the Tasks to be printed out.
     * @return returns a String containing information about the tasks that exist currently.
     */
    public String showTasks(TaskList taskList) {
        StringBuilder output = new StringBuilder();
        output.append(LINE);
        if(taskList.size() > 0) {
            output.append("Here are the patties on your cooking station:" + "\n");
            for (int i = 1; i <= taskList.size(); i++) {
                //print out task with numbering
                output.append(String.format("%s. %s", i, taskList.getTask(i).toString())).append("\n");
            }
        } else { //no tasks
            output.append("You have no more patties to flip!" + "\n" + LINE);
        }
        return output.toString();
    }

    /**
     * Returns a message to inform the user that a save file is being read.
     * @return returns a String informing the user that a save file is being read.
     */
    public String loadTasks() {
        return LINE + "Retrieving patties from the freezer..." + "\n" + LINE;
    }

    /**
     * Returns a specified message to inform the user that an operation succeeded.
     * @param message the message to be printed out.
     * @return returns a String to inform the user that an operation succeeded.
     */
    public String printSuccess(String message) {
        return LINE + "Success: " + message + "\n" + LINE;
    }

    /**
     * Returns a message to inform the user that a specific Task in the TaskList
     * has been marked as completed.
     * @param index the index of the Task in the TaskList.
     * @param taskList the TaskList which contains the Task to be marked as completed.
     * @return returns a String to inform the user that a specific Task in the TaskList
     * has been marked as completed.
     */
    public String markDone(int index, TaskList taskList) {
        Task task = taskList.getTask(index);
        return LINE + "Nice! I've marked this patty as cooked:" +  "\n" +
                task.toString() + "\n" + LINE;
    }

    /**
     * Returns a list of Tasks in the current TaskList that matches a specified word.
     * @param word the word to search for in the TaskList.
     * @param taskList the TaskList which contain the Tasks to be filtered
     * according to whether it contains the specified word.
     * @return returns a String containing a list of Tasks in the current
     * TaskList that matches a specified word.
     */
    public String find(String word, TaskList taskList) {
        TaskList temp = new TaskList();
        for(int i = 1; i <= taskList.size(); i++) {
            Task task = taskList.getTask(i);
            if(task.getName().contains(word)) {
                temp.addTask(task);
            }
        }
        StringBuilder output = new StringBuilder();
        if(temp.size() > 0) {
            output.append(LINE + "Here these patties match your description:");
            for (int i = 1; i <= temp.size(); i++) {
                //print out task with numbering
                output.append(String.format("%s. %s%n", i, temp.getTask(i).toString()) + "\n");
            }
        } else { //no matching tasks
            output.append("You have no matching patties!");
        }
        output.append("\n" + LINE);
        return output.toString();
    }
    
}
