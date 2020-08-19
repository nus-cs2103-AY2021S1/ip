import java.util.Scanner;

public class Chatterbox {
    public static void main(String[] args) {
        System.out.println("Hello I'm Chatterbox. What can I do for you?");

        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        while (!input.equals("bye")) {
            System.out.println("____________________________________________________________");
            System.out.println(input);
            System.out.println("____________________________________________________________");

            input = s.nextLine();
        }

        System.out.println("____________________________________________________________");
        System.out.println("Goodbye! Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
