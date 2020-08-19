import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        greet();

        echo();
    }

    private static void greet() {
        System.out.println("Hello! I am Wish\n" + "What can I do for you?");
    }

    private static void echo() {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        while (!s.equals("bye")) {
            System.out.println(s);
            s = sc.nextLine();
        }

        System.out.println("Goodbye! All the best and see you again soon!");
    }
}
