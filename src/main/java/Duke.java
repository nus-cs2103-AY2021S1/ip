import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Rawr! I'm Dino ><\n" + "What can I do for you?"
                + "\n____________________________________________________________");
        Scanner scanner = new Scanner(System.in);
        boolean isBye = false;
        List<String> tasks = new ArrayList<>();

        while (!isBye) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Rawr. Hope to see you again soon! ><"
                        + "\n____________________________________________________________");
                isBye = true;
            } else if (input.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    int index = i + 1;
                    System.out.println(index + ". " + tasks.get(i));
                }
                System.out.println("____________________________________________________________");
            } else {
                tasks.add(input);
                System.out.println("Dino has added: "
                        + input + "\n____________________________________________________________");
            }
        }
        scanner.close();
    }
}