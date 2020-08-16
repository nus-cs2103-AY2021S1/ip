import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static class Task {
        private String description;
        private boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
        }

        public void markDone() {
            isDone = true;
        }

        @Override
        public String toString() {
            return "[" + getStatusIcon() + "] " + description;
        }
    }

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> todoList = new ArrayList<>();

        chatBot:
        while(sc.hasNextLine()) {
            String userInput = sc.nextLine();
            String userOp = userInput.split(" ")[0]; //type of operation
            switch (userOp) {
                case "bye":
                    System.out.println("Bye! Hope to see you again soon!");
                    break chatBot;
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < todoList.size(); i++) {
                        System.out.println((i + 1) + ". " + todoList.get(i));
                    }
                    break;
                case "done":
                    int taskId = Integer.parseInt(userInput.split(" ")[1]);
                    Task task = todoList.get(taskId - 1);
                    task.markDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(task);
                    break;
                default:
                    todoList.add(new Task(userInput));
                    System.out.println("added: " + userInput);
                    break;
            }
        }
    }
}
