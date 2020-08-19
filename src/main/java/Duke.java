import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String input;

        printPart("Hello! I'm Duke\n" + "What can I do for you?");
        input = s.next();

        while(!input.equals("bye")) {
            printPart(input);
            input = s.next();
        }

        printPart("Bye. Hope to see you again soon!");
    }

    public static void printPart(String str) {
        System.out.println("\n" + "===================================================");
        System.out.println(str);
        System.out.println("===================================================" + "\n");
    }
}
