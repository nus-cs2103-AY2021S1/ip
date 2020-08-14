import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    final static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        speak("Hello! I'm Duke\nWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);

        String userInput;
        while (!(userInput = scanner.nextLine()).equals("bye")) {
            if (userInput.equals("list")) {
                StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");

                for (int i = 0; i < Duke.tasks.size(); i++) {
                    sb.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
                }

                Duke.speak(sb.toString().trim());
            } else if (userInput.startsWith("done")) {
                String[] words = userInput.split(" ");

                if (words.length < 2) {
                    throw new IllegalArgumentException("taskId not found!");
                }

                int taskId = Integer.parseInt(userInput.split(" ")[1]);
                Task task = tasks.get(taskId - 1);
                task.markAsDone();

                Duke.speak(String.format("Nice! I've marked this task as done:\n%s", task));
            } else {
                tasks.add(new Task(userInput));

                Duke.speak(String.format("added: %s", userInput));
            }
        }

        Duke.speak("Bye. Hope to see you again soon!");

        scanner.close();

    }

    public static void speak(String message) {
        String horizontalLine = "____________________________________________________________";
        System.out.printf("%s\n%s\n%s\n", horizontalLine, message, horizontalLine);
    }

}
