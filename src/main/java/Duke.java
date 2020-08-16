import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String logo = "Hello I'm Verzachtend \n" +
                "What can I do for you?\n" +
                "BE YOURSELF, NEVER SURRENDER AND KEEP A SMILE ON YOUR FACE";
        System.out.println(logo);

        String echo = scan.next();

        while (!echo.equals("bye")) {
            System.out.println(echo);
            echo = scan.next();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
