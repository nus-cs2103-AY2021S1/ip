import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);    // Create a Scanner object
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        String input = sc.nextLine();
        List<String> list = new ArrayList<>();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println(i + ". " + list.get(i - 1));
                }
                input = sc.nextLine();
            } else {
                list.add(input);
                System.out.println("added: " + input);
                input = sc.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}