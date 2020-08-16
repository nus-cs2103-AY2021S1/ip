import main.java.Task;

import java.util.*;

public class Duke {

    private String introduction = "Hello! I'm Duke";
    private String greeting = "What can I do for you?";
    private String goodbye = "Bye. Hope to see you again soon!";

    private List<Task> taskList = new ArrayList<>();

    private void printGreeting() {
        System.out.println(this.introduction);
        System.out.println(this.greeting);
    }

    private void printGoodbye() {
        System.out.println(this.goodbye);
    }

    private void listTasks() {
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println((i + 1) + ". " + this.taskList.get(i).getDescription());
        }
    }

    private void addTask(String input) {
        Task newTask = new Task(input);
        this.taskList.add(newTask);
        System.out.println("added: " + input);
    }

    private void handleUserInput() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                listTasks();
            } else {
                addTask(input);
            }
            input = sc.nextLine();
        }
        sc.close();
        printGoodbye();
    }

    public void run() {
        printGreeting();
        handleUserInput();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
