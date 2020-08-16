import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        String separator = "   -------------------------------------------------";
        while (sc.hasNextLine()) {
            String next = sc.nextLine();
            if (next.equals("bye")) {
                break;
            }
            System.out.println(separator);
            System.out.println("   " + next);
            System.out.println(separator);
            System.out.println();
        }
        sc.close();
        System.out.println(separator);
        System.out.println("   Bye. Hope to see you again soon!");
        System.out.println(separator);
    }
}
