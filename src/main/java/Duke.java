import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void say(String text) {
        System.out.println("Duke: " + text);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> todoList = new ArrayList<Task>();

        String greetingText = "Hey There! What can I do for you?";
        say(greetingText);

        String newResponse = scanner.nextLine();

        while (!newResponse.equals("bye")) {
            if (newResponse.equals("list")) {
                int count = 1;
                for (Task task : todoList) {
                    System.out.println(count + ". " + task);
                    count++;
                }
            } else if (newResponse.substring(0, 4).equals("done")) {
                int number = Integer.parseInt(newResponse.substring(5));
                Task task = todoList.get(number - 1);
                task.markDone();
                say("I have marked it as done!");
                System.out.println(task);
            } else {
                say("Added '" + newResponse + "' to the to-do list.");
                Task newTask = new Task(newResponse);
                todoList.add(newTask);
            }
            newResponse = scanner.nextLine();
        }

        String byeText = "Bye! Hope to see you again.";
        say(byeText);
    }
}
