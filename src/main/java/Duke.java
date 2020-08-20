import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Greet();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

       while (!input.equals("bye")) {
           Echo(input);
           input = sc.next();
       }
       Bye();

    }

    public static void Greet() {
        System.out.println("Hello, I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void Echo(String input) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     " + input);
        System.out.println("    ____________________________________________________________");
    }

    public static void Bye() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
