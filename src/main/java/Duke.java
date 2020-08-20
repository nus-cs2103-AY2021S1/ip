import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        List<String> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!(input.equals("bye"))) {
            if (input.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                        System.out.println((i+1) + ". " + list.get(i));
                }
                input = sc.nextLine();
            } else {
                System.out.println("added: " + input);
                list.add(input);
                input = sc.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}