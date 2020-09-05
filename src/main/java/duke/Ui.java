package duke;

import duke.tasks.Task;

public class Ui {
    
    private String outputMessage;
    
    public Ui() {
        this.outputMessage = showWelcomeMessage();
    }
    
    public void setOutputMessage(String newOutputMessage) {
        this.outputMessage = newOutputMessage + "\n";
    }
    
    private void appendMessage (String message) {
        this.outputMessage += (message + "\n");
    }
    
    public String getOutputMessage (){
        return this.outputMessage;
    }

    /**
     * Shows welcome message upon starting Duke
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
     * Shows bye message when user enters bye
     */
    public void byeMessage() {
        setOutputMessage("Bye!! See you again :)");
    }
    
    /**
     * Changes output message when a task is added to the list
     * @param t task added to the list
     * @param number number of tasks in the list
     */
    public void addTaskMessage(Task t, int number) {
        assert number >= 0;
        setOutputMessage ("Got it! I've added this task:");
        appendMessage (t.toString());
        appendMessage(String.format("Now you have %d tasks in the list", number));
    }

    /**
     * Shows message when a task is removed from the task list
     * @param t task removed from list
     * @param number number of tasks in the list
     */
    public void removeTaskMessage (Task t, int number) {
        assert number >= 0;
        setOutputMessage("Noted. I've removed this task");
        appendMessage(t.toString());
        appendMessage(String.format("Now you have %d tasks in the list", number));
    }

    /**
     * Shows message when a task is marked as done
     * @param t task that is marked done
     */
    public void doneMessage (Task t) {
        setOutputMessage("Nice! I've marked this task as done:");
        appendMessage(t.toString());
    }

    /**
     * Prints out all the tasks in the list
     * @param taskList list containing all the tasks
     */
    public void listMessage(TaskList taskList) {
        setOutputMessage("Here are the tasks in your list:");         
        for (int i = 0; i < taskList.size(); i++) {
            appendMessage(String.format("%d. %s", i + 1, taskList.get(i)));
        }
    }
    
    public void defaultMessage() {
        setOutputMessage("Sorry >_<!! I don't know what you mean...");
    }
}
