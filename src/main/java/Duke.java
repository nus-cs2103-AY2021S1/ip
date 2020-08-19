import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //To check if the conversation has ended.
        boolean running = true;
        //Greetings.
        System.out.println("\tHello!\n\tI am Baymax, your personal idle time companion." +
                "\n\tHow may I help you?");
        //Echo
        Scanner scanner = new Scanner(System.in);
        while (running) {
            String text = scanner.nextLine();
            if (text.equals("bye")) {
                text = "It was my pleasure assisting you.\n\tSee you next Time!";
                running = false;
            }
            System.out.println("\t" + text);
        }
    }
}
