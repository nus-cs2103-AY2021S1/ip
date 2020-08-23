import java.util.Scanner;

public class Ui {
    Parser parser;
    String divider = "____________________________________________________________";

    public Ui(Parser parser) {
        this.parser = parser;
        this.hello();
        this.run();
    }

    void hello() {
        String intro = "Hello! I'm Bob\n" +
                "What can I do for you?\n";
        System.out.println(divider + "\n" + intro + "\n" + divider);
    }

    void run() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            this.parser.run(input);
            input = sc.nextLine();
        }
        this.goodbye();
    }

    void goodbye() {
        String message = "Bye. Hope to see you again soon! :)";
        System.out.println(divider + "\n" + message + "\n" + divider);
    }
}