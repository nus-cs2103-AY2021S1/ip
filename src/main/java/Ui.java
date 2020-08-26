import javax.sound.sampled.Line;

public class Ui {
    private static final String LINE = "____________________________________________________________" + "\n";
    
    public void printLine() {
        System.out.println(LINE);
    }
    
    public void printHi() {
        String greeting = "Hello! I'm Duke" + "\n" + "What can I do for you?" + "\n";
        System.out.println(LINE + greeting + LINE);
    }
    
    public void printBye() {
        String goodbye = "Bye. Hope to see you again soon!";
        System.out.println(LINE + goodbye + "\n" + LINE);
    }
    
    public void printAdd(Task task, TaskList taskList) {
        System.out.println(LINE
                + "Got it. I've added this task: " + "\n"
                + task.toString() + "\n"
                + String.format("Now you have %s tasks in the list.", taskList.size()) + "\n"
                + LINE);
    }
    
    public void printDelete(int index, TaskList taskList) {
        System.out.println(LINE
                + "Noted. I've removed this task: " + "\n"
                + taskList.getTask(index).toString() + "\n"
                + String.format("Now you have %s tasks in the list.", taskList.size() - 1) + "\n"
                + LINE);
    }
    
    public void printError(Exception e) {
        System.out.println(LINE + "Error: " + e + "\n" + LINE);
    }
    
    public void printError(String message) {
        System.out.println(LINE + "Error: " + message + "\n" + LINE);
    }

    public void showTasks(TaskList taskList) {
        printLine();
        if(taskList.size() > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i <= taskList.size(); i++) {
                //print out task with numbering
                System.out.println(String.format("%s. %s", i, taskList.getTask(i).toString()));
            }
        } else { //no tasks
            System.out.println("You have no tasks!");
        }
        printLine();
    }
    
    public void loadTasks() {
        System.out.println(LINE + "Reading saved file..." + "\n" + LINE);
    }
    
    public void markDone(int index, TaskList taskList) {
        Task task = taskList.getTask(index);
        System.out.println(LINE + "Nice! I've marked this task as done:" +  "\n" +
                task.toString() + "\n" + LINE);
    }
}
