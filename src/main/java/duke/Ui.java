package duke;

import duke.tasks.Task;

/**
 * Ui which sets output message to the user
 */
public class Ui {
    
    private String outputMessage;

    /**
     * Initializes the output message to the welcome message
     */
    public Ui() {
        this.outputMessage = showWelcomeMessage();
    }

    /**
     * Changes the output message to the specified message
     * 
     * @param newOutputMessage specified message
     */
    public void setOutputMessage(String newOutputMessage) {
        this.outputMessage = newOutputMessage + "\n";
    }
    
    private void appendMessage (String message) {
        this.outputMessage += (message + "\n");
    }

    /**
     * Returns the output message
     * 
     * @return output message
     */
    public String getOutputMessage (){
        return this.outputMessage;
    }
    
    /**
     * Returns the welcome message upon starting Duke
     * 
     * @return welcome message
     */
    public String showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcomeMessage = "Hello from\n" + logo + "\n";
        welcomeMessage += "Hello! I'm Duke ^_^\n";
        welcomeMessage += "What can I do for you??\n";
        return welcomeMessage;
    }

    /**
     * Sets output message to the bye message when user enters bye
     */
    public void byeMessage() {
        setOutputMessage("Bye!! See you again :)");
    }
    
    /**
     * Sets output message when a task is added to the list
     * 
     * @param task task added to the list
     * @param number number of tasks in the list
     */
    public void addTaskMessage(Task task, int number) {
        setOutputMessage ("Got it! I've added this task:");
        appendMessage (task.toString());
        appendMessage(String.format("Now you have %d tasks in the list", number));
    }

    /**
     * Sets output message when a task is removed from the list
     * 
     * @param task task removed from list
     * @param number number of tasks in the list
     */
    public void removeTaskMessage (Task task, int number) {
        setOutputMessage("Noted. I've removed this task");
        appendMessage(task.toString());
        appendMessage(String.format("Now you have %d tasks in the list", number));
    }

    /**
     * Sets output message when a task is marked as done
     * 
     * @param task task that is marked done
     */
    public void doneMessage (Task task) {
        setOutputMessage("Nice! I've marked this task as done:");
        appendMessage(task.toString());
    }

    /**
     * Sets output message to the list of all the tasks
     * 
     * @param taskList list containing all the tasks
     */
    public void listMessage(TaskList taskList) {
        setOutputMessage("Here are the tasks in your list:");         
        for (int i = 0; i < taskList.size(); i++) {
            appendMessage(String.format("%d. %s", i + 1, taskList.getTask(i)));
        }
    }

    /**
     * Sets output message to the default message
     */
    public void defaultMessage() {
        setOutputMessage("Sorry >_<!! I don't know what you mean...");
    }
}
