import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> tasks = new LinkedList<>();

        int number = 1;
        String lines = "____________________\n";
        String farewell = "Sad to see you go!\n";

        System.out.println(lines
                + "Hello, I'm your chatbot!\n"
                + "What can I do for you?\n"
                + lines);

        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(lines + farewell + lines);
                break;
            } else {
                tasks.add(String.format("%d. ", number++) + input);
                System.out.println(lines + "added: " + input + "\n" + lines);
            }
        }
    }
}