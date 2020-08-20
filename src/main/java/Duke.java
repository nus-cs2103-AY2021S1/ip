import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String horizontalLine = "\t-------------------------------------------------------";
        System.out.println(horizontalLine);
        System.out.println("\tHello! I'm Duke\n" + "\t" + "What can I do for you?");
        System.out.println(horizontalLine);
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        while (scanner.hasNext()) {
            System.out.println(horizontalLine);
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println(horizontalLine);
                scanner.close();
                break;
            }
            if (userInput.equalsIgnoreCase("list")) {
                int index = 1;
                for (Task task : list) {
                    System.out.println("\t" + index++ + ". " + task.toString());
                }
            } else {
                Scanner scanner2 = new Scanner(userInput);
                if (scanner2.next().equalsIgnoreCase("done")) {
                    int index = scanner2.nextInt();
                    Task task = list.get(index - 1);
                    task.markAsDone();
                    System.out.println("\tNice! I've marked this as done:");
                    System.out.println("\t  " + task.toString());
                } else{
                    list.add(new Task(userInput));
                    System.out.println("\tadded: " + userInput);
                }
            }
            System.out.println(horizontalLine);
        }
    }

}

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}