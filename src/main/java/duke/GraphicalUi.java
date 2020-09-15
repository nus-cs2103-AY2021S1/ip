package duke;

import java.util.List;

public class GraphicalUi extends Ui {
    private static String greetingMessage = GREETING_MESSAGE;
    private String responseMessage;
    private boolean hasErrorMessage;

    /**
     * Initializes a Graphical Ui for Duke.
     */
    public GraphicalUi() {
        this.responseMessage = "";
        hasErrorMessage = false;
    }

    public String getGreetingMessage() {
        return LINE + greetingMessage + LINE;
    }

    public void setErrorGreeting(String errorMessage) {
        greetingMessage += "\n Note that a new file was loaded due to this error: " + errorMessage + "\n";
    }

    @Override
    public void processError(String errorMessage) {
        responseMessage = errorMessage + "\n";
        hasErrorMessage = true;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public boolean hasErrorMessage() {
        return hasErrorMessage;
    }

    /**
     * Adds display message for task count to response message.
     *
     * @param numOfTasks Number of tasks.
     */
    public void displayTaskCount(int numOfTasks) {
        if (numOfTasks == 1) {
            responseMessage += "My duck senses tell me you have 1 task in the list." + "\n";
        } else {
            responseMessage += "My duck senses tell me you have " + numOfTasks + " tasks in the list." + "\n";
        }
    }

    @Override
    public void processAddMessage(Task task, int count) {
        responseMessage = "Quack! I have added: " + task + "\n";
        displayTaskCount(count);
        hasErrorMessage = false;
    }

    @Override
    public void processDeleteMessage(Task taskToDelete, int count) {
        responseMessage = "Quack! I have deleted this task: \n" + taskToDelete + "\n";
        displayTaskCount(count);
        hasErrorMessage = false;

    }

    @Override
    public void processDoneMessage(Task task) {
        responseMessage = "Quack! I have marked this task as done: \n" + task + "\n";
        hasErrorMessage = false;
    }

    @Override
    public void processClose() {
    }

    @Override
    public void processResultTaskList(List<Task> resultTaskList) {
        if (resultTaskList.isEmpty()) {
            responseMessage = "No tasks matched..." + "\n";
        } else {
            responseMessage = "Quack! Here are the tasks in your list that match:" + "\n";
            int count = 1;
            for (Task task : resultTaskList) {
                responseMessage += count + ". " + task + "\n";
                count++;
            }
        }
        hasErrorMessage = false;
    }

    @Override
    public void listStoredTasks(List<Task> storedTasks) {
        if (storedTasks.isEmpty()) {
            responseMessage = "No tasks stored..." + "\n";
        } else {
            responseMessage = "Quack! Here are the tasks in your list:" + "\n";
            int count = 1;
            for (Task task : storedTasks) {
                responseMessage += count + ". " + task + "\n";
                count++;
            }
        }
        hasErrorMessage = false;
    }
}
