import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("______________________");

        String command;
        String[] inputs;
        ArrayList<Task> tasks = new ArrayList<Task>();
        Scanner sc = new Scanner(System.in);
        inputs = sc.nextLine().split(" ",2);
        command = inputs[0];

        while(!command.equals("bye")) {
            switch(command) {
                case "list":
                    System.out.println("Task list");
                    int i = 1;
                    for (Task task : tasks) {
                        System.out.println(i++ + ". " + task);
                    }
                    break;
                case "done":
                    tasks.get(Integer.parseInt(inputs[1]) - 1).markDone();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(tasks.get(Integer.parseInt(inputs[1]) - 1));
                    break;
                default:
                    tasks.add(new Task(String.join(" ",inputs)));
                    System.out.println("added: " + String.join(" ",inputs));
            }
            inputs = sc.nextLine().split(" ",2);
            command = inputs[0];
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("--------------------------------");
    }
}

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone(){
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    @Override
    public String toString(){
        return getStatusIcon() + " " + description;
    }

}
