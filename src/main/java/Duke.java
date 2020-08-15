import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        String divider = "----------------------------------------";
        greet();
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();

        while (!message.equals("bye")) {
            System.out.println(divider);
            if (!message.equals("list")) {
                list.add(message);
                System.out.println("\t" + "added: " + message);
            } else {
                System.out.println("Here is a list of all texts that you have entered:");
                for (int i = 0; i < list.size(); i ++) {
                    int index = i + 1;
                    System.out.println("\t" + String.format("%d. %s", index, list.get(i)));
                }
            }
            System.out.println(divider);
            message = sc.nextLine();
        }

        System.out.println(divider);
        System.out.println("\t" + "GoodBye and I hope to see you soon! Have a fantastic day! ");
        System.out.println(divider);

    }

    public static void greet() {
        String divider = "----------------------------------------";
        String greeting = "  Hello! I am JonasBot! Nice to meet you :) \n" +
                "  I am a bot that will store all texts entered. \n" +
                "  To display all texts that have been entered, input 'list' \n" +
                "  To end this chat, input 'bye' \n" +
                "  How may I assist you today?";
        System.out.println(divider);
        System.out.println(greeting);
        System.out.println(divider);
    }

}
