public class Ui {
    
    public String showLoadingError() {
        return "ERROR: file does not exist.";
    }
    
    public String showError(String msg) {
        return msg;
    }
    
    public String showWelcome() {
        return "Welcome to Duke - your personal task organizer!";
    }

    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }
    
    public String showLine() {
        return "    ____________________________________________________________\n";
    }
    
    public String showDeletedTask(Task task, int length) {
        assert length >= 0 : "length must not be negative";
        return "Noted. I've removed this task:\n" + task + "\n" 
                + "Now you have " + length + " tasks in the list.\n";
    }
    
    public String showAddedTask(Task task, int length) {
        assert length >= 0 : "length must not be negative";
        return "Got it. I've added this task:\n" + task + "\n"
                + "Now you have " + length + " tasks in the list.\n";
    }
    
    public String showDone(Task task) {
        return "Nice! I've marked this task as done:\n" + task + "\n";
    }
    
    public String showList(TaskList taskList) {
        StringBuilder display = new StringBuilder();
        if (taskList.taskListLength() == 0) {
            return "You have no tasks!";
        } else {
            display.append("Here are the tasks in your list:\n");
            for (int i = 0; i < taskList.taskListLength(); i++) {
                Task task = taskList.getTaskList().get(i);
                display.append(i + 1).append(". ").append(task).append("\n");
            }
        }    
        return display.toString();
    } 
    
    public String showTasksFound(String s) {
        return "Here are the matching tasks in your list:\n" + s;
    }
    
    public String showSortedList(TaskList taskList) {
        return "Your list is now sorted!\n" + showList(taskList);
    }
}
