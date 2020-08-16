import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello, I'm Duke");
        System.out.println("Right now I'm kind of dumb and can only repeat what you say");
        System.out.println("Type 'bye' if you're fed up with me");
        String next;
        while (true) {
            next = sc.nextLine();
            if (next.equalsIgnoreCase("bye")) {
                System.out.println(":<");
                sc.close();
                System.exit(0);
            } else {
                System.out.println(next);
            }
        }

    }
}
