import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>(100);

        int number = 1;
        String lines = "____________________\n";
        String farewell = "Sad to see you go!\n";

        System.out.println(lines
                + "Hello, I'm your chatbot!\n"
                + "What can I do for you?\n"
                + lines);

        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(lines + farewell + lines);
                break;
            } else if (input.equals("list")) {
                System.out.println(lines);
                System.out.println("Here are the tasks in your list:");
                tasks.forEach(System.out::println);
                System.out.println(lines);
            } else {
                tasks.add(new Task(number++, input));
                System.out.println(lines + "added: " + input + "\n" + lines);
            }
        }
    }
}