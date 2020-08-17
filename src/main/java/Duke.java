import java.util.Scanner;

public class Duke {

    public static void greeting() {
        String greeting = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greeting);
    }

    public static void main(String[] args) {
        greeting();
        Scanner sc = new Scanner(System.in);
        String input;
        while(true) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                sc.close();
                break;
            } else {
                System.out.println(input);
            }
        }

    }
}
