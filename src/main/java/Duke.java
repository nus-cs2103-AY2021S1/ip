import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        //To check if the conversation has ended.
        boolean running = true;
        //Greetings.
        System.out.println("\tHello!\n\tI am Baymax, your personal idle time companion." +
                "\n\tHow may I help you?");
        //Add List
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        while (running) {
            String text = scanner.nextLine();
            if (text.equals("bye")) {
                System.out.println("\tIt was my pleasure assisting you.\n\tSee you next Time!");
                running = false;
            } else if (text.equals("list")) {
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println("\t" + i + ". " + list.get(i - 1));
                }
            } else {
                list.add(text);
                System.out.println("\tadded: " + text);
            }
        }
    }
}
