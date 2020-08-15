import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String divider = "----------------------------------------";
        greet();
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();

        while (!message.equals("bye")) {
            System.out.println(divider);
            System.out.println("\t" + message);
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
                "  I am a bot that will replicate anything that you say. \n" +
                "  To end this chat, simply type 'bye' \n" +
                "  How may I assist you today?";
        System.out.println(divider);
        System.out.println(greeting);
        System.out.println(divider);
    }

}
