import main.java.exceptions.InvalidCommandException;
import main.java.exceptions.InvalidNumberException;
import main.java.tasks.Deadline;
import main.java.tasks.Event;
import main.java.tasks.Task;
import main.java.tasks.Todo;

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
        if (newTask != null) {
            this.taskList.add(newTask);

            System.out.println("Got it. I've added this task:");
            System.out.println(newTask.toString());
            System.out.println("Now you have " + this.taskList.size() + " tasks in the list.");
        }
    }

    private Task getTaskType(String input) {
        try {
            if (input.startsWith("deadline") && input.contains("/by")) {
                String description = input.substring("deadline".length(), input.indexOf("/by")).trim();
                String endTime = input.substring(input.indexOf("/by") + "/by".length()).trim();
                return new Deadline(description, endTime);
            } else if (input.startsWith("event") && input.contains("/at")) {
                String description = input.substring("event".length(), input.indexOf("/at")).trim();
                String time = input.substring(input.indexOf("/at") + "/at".length()).trim();
                return new Event(description, time);
            } else if (input.startsWith("todo")) {
                String description = input.substring("todo".length()).trim();
                return new Todo(description);
            } else {
                throw new InvalidCommandException("Not sure what you mean. Please ensure your command format is correct and try again.");
            }
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private void markTaskAsDone(int index) {
        this.taskList.get(index).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.taskList.get(index).toString());
    }

    private boolean isNumberedCommand(String input) throws InvalidNumberException {
        String[] words = input.split(" ");
        boolean checkBackNumber = words.length == 2 &&
                words[1].chars().allMatch(Character::isDigit);
        if (checkBackNumber) {
            int index = Integer.parseInt(words[1]) - 1;
            boolean isValidNumber = index < this.taskList.size();
            if (!isValidNumber) {
                throw new InvalidNumberException("The number entered is invalid. " +
                            "You have " + this.taskList.size() + " tasks in your list.");
            }
            return true;
        } else {
            return false;
        }
    }

    private void deleteTask(int index) {
        Task removedTask = this.taskList.remove(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println(removedTask.toString());
        System.out.println("Now you have " + this.taskList.size() + " tasks in the list.");
    }

    private void handleUserInput() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            try {
                if (input.equals("list")) {
                    listTasks();
                } else if (isNumberedCommand(input)) {
                    String[] words = input.split(" ");
                    if (words[0].equals("done")) {
                        int index = Integer.parseInt(words[1]) - 1;
                        markTaskAsDone(index);
                    } else if (words[0].equals("delete")) {
                        int index = Integer.parseInt(words[1]) - 1;
                        deleteTask(index);
                    } else {
                        throw new InvalidCommandException("Command is invalid. Try again?");
                    }
                } else {
                    addTask(input);
                }
            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
            }

            if (sc.hasNextLine()) {
                input = sc.nextLine();
            } else {
                break;
            }
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
