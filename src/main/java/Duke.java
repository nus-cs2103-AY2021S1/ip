import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    protected List<Task> tasks;
    protected Scanner sc;

    public Duke() {
        this.tasks = new ArrayList<>();
        this.sc = new Scanner(System.in);
    }

    public void sayHello() {
        System.out.println("Hello! I'm Clara! :D How may I help you? :)");
    }

    public void sayBye() {
        System.out.println("\tBye! Have a great day and hope to see you soon! :D");
    }

    public void addTask(String description) {
        this.tasks.add(new Task(description, false));
        System.out.println("\tadded: " + description);
    }

    public void markTaskAsDone(int index) {
        String description = this.tasks.get(index).description;
        Task updatedTask = new Task(description, true);

        this.tasks.set(index, updatedTask);

        System.out.println("Nice! I've marked this task as done: \n\t" + updatedTask);
    }

    public void displayTasks() {
        if (tasks.size() > 0) {
            System.out.println("These are the tasks in your list. Jiayous! :)");
        } else {
            System.out.println("You have no task in your list. :D");
        }

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(String.format("\t%d. %s", i+1, this.tasks.get(i).toString()));
        }
    }

    public void serveClient() {
        sayHello();
        while (sc.hasNext()) {
            String command = sc.nextLine();
            String[] splitCommand = command.split(" ");
            String keyword = splitCommand[0];

            switch(keyword) {
                case "bye":
                    sayBye();
                    break;
                case "list":
                    displayTasks();
                    break;
                case "done":
                    int index = Integer.parseInt(splitCommand[1])-1;
                    markTaskAsDone(index);
                    break;
                default:
                    addTask(command);
            }

            if (command.equals("bye")) {
                break;
            }
        }


    }
}
