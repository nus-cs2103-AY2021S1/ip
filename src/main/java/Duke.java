import main.java.Task;
import main.java.Deadline;
import main.java.Event;
import main.java.Todo;

import java.util.*;

public class Duke {

    private List<Task> taskList = new ArrayList<>();

    private void printGreeting() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    private void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println((i + 1) + "." + this.taskList.get(i).toString());
        }
    }

    private void addTask(String input) {
        Task newTask = getTaskType(input);
        this.taskList.add(newTask);

        System.out.println("Got it. I've added this task:");
        System.out.println(newTask.toString());
        System.out.println("Now you have " + this.taskList.size() + " tasks in the list.");
    }

    private Task getTaskType(String input) {
        if (input.startsWith("deadline") && input.contains(" /by ")) {
            String description = input.substring("deadline ".length(), input.indexOf(" /by "));
            String endTime= input.substring(input.indexOf(" /by ") + " /by ".length());
            return new Deadline(description, endTime);
        } else if (input.startsWith("event") && input.contains(" /at ")) {
            String description = input.substring("event ".length(), input.indexOf(" /at "));
            String time= input.substring(input.indexOf(" /at ") + " /at ".length());
            return new Event(description, time);
        } else if (input.startsWith("todo")) {
            String description = input.substring("todo ".length());
            return new Todo(description);
        } else {
            return new Task(input);
        }
    }

    private void markTaskAsDone(int index) {
        this.taskList.get(index).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.taskList.get(index).toString());
    }

    private boolean isDoneCommand(String input) {
        String[] words = input.split(" ");
        boolean checkBackNumber = words.length == 2 &&
                words[1].chars().allMatch(Character::isDigit);
        if (checkBackNumber) {
            int index = Integer.parseInt(words[1]) - 1;
            boolean isValidNumber = index < this.taskList.size();
            return words[0].equals("done") && isValidNumber;
        } else {
            return false;
        }
    }

    private void handleUserInput() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                listTasks();
            } else if (isDoneCommand(input)) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                markTaskAsDone(index);
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
