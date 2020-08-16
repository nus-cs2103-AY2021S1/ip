import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hi there, I'm TARS!\nWhat can I do for you?");

        ArrayList<String> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String input = "start";

        while (!input.equals("bye")) {
            input = scanner.nextLine();
            if (input.equals("list")) {
                int counter = 1;
                for (String item : tasks) {
                    System.out.println(counter + ". " + item);
                    counter++;
                }
                input = scanner.nextLine();
            }
            if (!input.equals("bye")) {
                tasks.add(input);
                System.out.println("added: " + input);
            }
        }
        System.out.println("Bye bye. See you again soon!");
    }
}
