import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String divider = "____________________________________________________________";
        String intro = "Hello! I'm Duke\n" +
                "What can I do for you?\n";

        System.out.println(divider + "\n" + intro + "\n" + divider);
        String input = sc.next();

        while(!input.equals("bye")) {
            System.out.println(divider + "\n" + input + "\n" + divider);
            input = sc.next();
        }

        String message = "Bye. Hope to see you again soon!";
        System.out.println(divider + "\n" + message + "\n" + divider);
    }
}

