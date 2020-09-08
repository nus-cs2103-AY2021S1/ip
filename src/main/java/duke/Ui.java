package duke;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Ui {
    private PrintStream out;
    
    public Ui() {
        try {
            out = new PrintStream(System.out, true, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            out = new PrintStream(System.out, true);
        }
    }

    /**
     * Renders the welcome message onto the console.
     * To be called on programme startup.
     */
    public String showWelcomeScreen() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "Hello, my name is\n" 
                + "What can I do for you?";
        return logo;
    }

    /**
     * Renders the goodbye message onto the console.
     * To be called on programme exit.
     */
    public String showGoodbyeScreen() {
        return "See you space cowboy!";
    }

    /**
     * Prints the error message from an exception.
     * @param e the exception that contains the error message to be printed.
     */
    public void printExceptionMessage(Exception e) {
        out.println(e.getMessage());
    }

    /**
     * Renders a confirmation message to the console to notify the user of the successful 
     * addition of a Task to the TaskList, and displays all lists in the TaskList.
     * @param task The Task that has been added to the task list.
     * @param taskList The TaskList which the task has been added to.
     */
    public String printAddTaskConfirmation(Task task, TaskList taskList) {
        return "Got it. I've added this task: \n" 
                + task 
                + "\nNow you have " + taskList.numOfTasks() + " tasks in the list.";
    }

    /**
     * Renders a confirmation message to the console to notify the user of the successful 
     * addition of a Task to the TaskList, and displays all lists in the TaskList.
     * @param task The Task that has been removed from the task list.
     * @param taskList The TaskList which the task has been removed from.
     */
    public String printRemoveTaskConfirmation(Task task, TaskList taskList) {
        return "Noted. I've removed this task: \n"
                + task 
                + "\nNow you have " + taskList.numOfTasks() + " tasks in the list.";
    }

    /**
     * Renders a confirmation message to the console to notify the user that the specified 
     * task has been successfully marked as complete.
     * @param task The Task that has been marked as complete.
     */
    public String printMarkTaskCompleteConfirmation(Task task) {
        return "Nice! I've marked this task as done: \n" 
                + task;
    }

    /**
     * Renders the all tasks from the TaskList onto the console.
     * @param taskList The TaskList which contains all the tasks to be displayed on the console.
     */
    public String printAllTasks(TaskList taskList) {
        return taskList.getAllTasksAsString();
    }
    
    public void printMessage(String message) {
        out.println(message);
    }

    public static class Duke {
        private TaskList taskList;
        private Ui ui;
        private Parser parser;
        
        // UI variables
        public Duke() {
            taskList = new TaskList();
            ui = new Ui();
            parser = new Parser(taskList, ui);
            Storage.loadTasksFrom("data/duke.txt", taskList);
        }
        
        public void LoadTasks() {
            Storage.loadTasksFrom("data/duke.txt", taskList);
        }
        
        public void saveTasks() {
            Storage.saveTasksTo("data/duke.txt", taskList);
        }
        
        public String parseInput(String input) {
            String output = parser.parse(input);
            // System.out.println(output);
            return output;
        }
    
    
    }
}
