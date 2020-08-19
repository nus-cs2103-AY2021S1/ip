import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("____________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________\n");

        Scanner sc = new Scanner(System.in);
        boolean active = true;
        while (active) {
            String input = sc.nextLine();
            if (input.equals("list")) {
                System.out.println("____________________________________________________");
                System.out.println("list");
                System.out.println("____________________________________________________\n");
            } else if (input.equals("blah")) {
                System.out.println("____________________________________________________");
                System.out.println("blah");
                System.out.println("____________________________________________________\n");
            } else if (input.equals("bye")) {
                active = false;
                System.out.println("____________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________\n");
            }
        }
        sc.close();
    }
}