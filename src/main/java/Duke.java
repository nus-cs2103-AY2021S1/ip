import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String welcomeMsg = "Hello! I'm Duke, some call me a parrot.\n"
                + "What can I do for you?";
        System.out.println(welcomeMsg);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String inputMsg = sc.nextLine();
            if (inputMsg.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println(inputMsg);
            }
        }
        sc.close();
    }
}
