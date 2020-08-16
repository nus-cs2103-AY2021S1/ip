import java.util.Scanner;

public class Duke {

    public static String line = "_______________________________________________";
    Scanner sc = new Scanner(System.in);

    void echo() {
        greetings();
        while(sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                goodBye();
                break;
            } else {
                messageFormatter(() -> System.out.println(input));
            }
        }
    }

    void greetings() {
        messageFormatter(() -> {
            System.out.println("Hello! I'm Duke ^.^");
            System.out.println("What can I do for you?");
        });
    }

    void goodBye() {
        messageFormatter(() -> System.out.println("Bye ^.^, Hope to see you again soon!!!"));
    }

    void messageFormatter(Runnable func) {
        System.out.println(line);
        func.run();
        System.out.println(line);
    }

}
