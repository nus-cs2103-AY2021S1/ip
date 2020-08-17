import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input;

        String lines = "____________________________________________________________";

        String greeting = "____________________________________________________________\n" +
                "Hello! I'm Duke\n" +
                "What can I do for you?\n" +
                "____________________________________________________________";

        System.out.println(greeting);
        input = sc.next();

        while (!input.equals("bye")) {
            System.out.println(lines);
            System.out.println(input);
            System.out.println(lines);
            input = sc.next();
        }

        System.out.println(lines);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(lines);


    }

}
