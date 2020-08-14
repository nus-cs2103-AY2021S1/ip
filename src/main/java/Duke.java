import java.util.Scanner;

public class Duke {

    private static void botStart() {
        Scanner sc = new Scanner(System.in);
        System.out.println("=========================================="
                + "\nHi, my name is Duke."
                + "\nWhat can I do for you today?"
                + "\n==========================================");
        while (true) {
            String command = sc.next();
            if (command.equals("bye")) {
                System.out.println("Thanks for chatting with me, see you soon!"
                        + "\n==========================================");
                break;
            } else {
                System.out.println(">" + command + "<");
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        Duke.botStart();
    }
}
