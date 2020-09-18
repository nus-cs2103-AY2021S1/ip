package clippy.ui;

import clippy.task.Task;

public class Ui {
    private final static String MSG_GREET = "Hello! I'm Clippy \n" +
            "What can I do for you today?\n";
    private final static String MSG_EXIT = "Bye! Hope to see you again soon!";
    private final static String MSG_ADDED_TASK = "Got it. I've added this task:\n";
    private final static String MSG_LIST_TASK_NO_TASK = "You have no tasks in your list.";
    private final static String MSG_LIST_TASK_HAVE_TASK = "Here are the tasks in your list:";
    private final static String MSG_MARK_DONE = "Nice! I've marked this task as done:\n";
    private final static String MSG_DELETED_TASK = "Noted. I've removed this task:\n";
    private final static String MSG_FOUND_MATCHING_TASK = "Here are the matching tasks in your list:";
    private final static String MSG_NO_MATCHING_TASK = "No matching tasks are found in your list.";
    private final static String MSG_UPDATED_TASK = "Ok! I have updated the task as follows:\n";
    private final static String MSG_NOW_NO_TASK = "Now you have no tasks in the list.";
    private final static String MSG_NOW_ONE_TASK = "Now you have 1 task in the list.";
    private final static String MSG_HELP = "Refer to the user guide at https://https://wang-jun-hao.github.io/ip/";
    
    public static String showWelcome() {
        return MSG_GREET;
    }

    public String showExit() {
        return MSG_EXIT;
    }
    
    public String showError(Exception e) {
        return e.getMessage();
    }

    public String showHelp() {
        return MSG_HELP;
    }

    public String showListWithTasksHeader() {
        return MSG_LIST_TASK_HAVE_TASK;
    }
    
    public String showListNoTasks() {
        return MSG_LIST_TASK_NO_TASK;
    }
    
    public String showTaskWithIndex(int i, Task task) {
        return i + "." + task;
    }
    
    public String showDeleted(Task task, int numOfRemainingTasks) {
        return MSG_DELETED_TASK + task + "\n" + generateNumOfTasksRemainingSentence(numOfRemainingTasks);
    }
    
    private String generateNumOfTasksRemainingSentence(int numOfRemainingTasks) {
        assert numOfRemainingTasks >= 0 : "Negative number of remaining tasks";
        switch (numOfRemainingTasks) {
        case 0:
            return MSG_NOW_NO_TASK;
        case 1:
            return MSG_NOW_ONE_TASK;
        default:
            return "Now you have " + numOfRemainingTasks + " tasks in the list.";
        }
            
    }
    
    public String showDone(Task task) {
        return MSG_MARK_DONE + task;
    }
    
    public String showAdded(Task task, int numOfTasks) {
        assert numOfTasks >= 0 : "Negative number of tasks";
        String numOfTaskDescription;
        if (numOfTasks == 0) {
            numOfTaskDescription = "Now you have no tasks in the list.";
        } else if (numOfTasks == 1) {
            numOfTaskDescription = "Now you have 1 task in the list.";
        } else {
            numOfTaskDescription = "Now you have " + numOfTasks + " tasks in the list.";
        }
        return MSG_ADDED_TASK + task + "\n" + numOfTaskDescription;
    }
    
    public String showNoMatch() {
        return MSG_NO_MATCHING_TASK;
    }
    
    public String showMatchingTaskHeader() {
        return MSG_FOUND_MATCHING_TASK;
    }
    
    public String showUpdated(int indexOfUpdatedTask, Task updatedTask) {
        return MSG_UPDATED_TASK + showTaskWithIndex(indexOfUpdatedTask, updatedTask);
    }
    
}
