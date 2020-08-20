import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String start = "Hello! I'm Duke \nWhat can I do for you?";
        System.out.println(start);
        Scanner sc = new Scanner(System.in);
        while (true) {
            String in = sc.next();
            if (in.equals("bye")) {
                break;
            } else {
                System.out.println(in);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
