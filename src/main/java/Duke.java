import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________\n");

        Scanner sc = new Scanner(System.in);
        String userCommand = sc.nextLine();

        while (!userCommand.equals("bye")) {
            System.out.println("\t____________________________________________________________");
            System.out.println("\t"+ userCommand);
            System.out.println("\t____________________________________________________________\n");
            userCommand = sc.nextLine();
        }

        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }
}
