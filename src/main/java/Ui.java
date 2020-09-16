import java.util.Scanner;
import java.util.ArrayList;

public class Ui {
    public void showWelcome() {
        String logo = "      ___                __     \n"
                + "     |  _ \\   ____      |  |    \n"
                + "     | |_| | / __  \\  __|  |__  \n"
                + "     |  _ / | |  |  ||__    __| \n"
                + "     | |    | |_/   |   |  |    \n"
                + "     |_|     \\___/|_|   |__|    \n";
        String[] greetingTexts = {"Hello! I'm Pat", "What can I do for you?"};
        Response greeting = new Response(greetingTexts);
        System.out.println("     Hello from\n" + logo);
        System.out.println(greeting.getResponse());
        showLine();
    }
    
    public void showLine() {
        System.out.println("    __________________________________________________________ \n");
    }
    
    public String readCommand() {
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }
    
    public void showAddMessage(Task task, TaskList tasks) {
        Response msg = new Response(new Task[]{task}, Response.Tag.ADD, tasks.size());
        System.out.println(msg.getResponse());
    }
    
    public void showDeleteMessage(Task task, TaskList tasks) {
        Response msg = new Response(new Task[]{task}, Response.Tag.REMOVE, tasks.size());
        System.out.println(msg.getResponse());
    }
    
    public void listTasks(TaskList tasks) {
        Response list = new Response(tasks.getArray(), Response.Tag.LIST);
        System.out.println(list.getResponse());
    }
    
    public void showDoneMessage(Task task) {
        Response msg = new Response(new String[]{"Nice! I've marked this task as done:", "  " + task});
        System.out.println(msg.getResponse());
    }
    
    public void showFindMessage(ArrayList<Task> tasks) {
        Response msg = new Response(tasks.toArray(new Task[0]), Response.Tag.FIND);
        System.out.println(msg.getResponse());
    }
    
    public void showLoadingError() {
        Response msg = new Response(new String[]{"Folder or file does not exist yet! Please make sure you have data/duke.txt in ip directory. "});
        System.out.println(msg.getResponse());
    }
    
    public void showError(String errMessage) {
        Response msg = new Response(new String[]{errMessage});
        System.out.println(msg.getResponse());
    }
    
    public void showByeMessage() {
        Response msg = new Response(new String[]{"Bye. Hope to see you again soon!"});
        System.out.println(msg.getResponse());
    }
    
}
