import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I am Duke\n"
                + "What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String terminate = "bye";

        while (!input.equals(terminate)) {
            System.out.println(input);
            input = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
