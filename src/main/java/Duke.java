import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String lines = "------------------------------------------------\n";
        System.out.println(lines + "Hello! I'm Duke!\n" + "What can I do for you?\n" + lines);

        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();

        while (!userInput.equals("bye")) {
            System.out.println(lines + "    " + userInput + "\n" + lines);
            userInput = sc.nextLine();
        }

        System.out.println(lines + "    Bye! Hope to see you again soon." + "\n" + lines);

    }
}