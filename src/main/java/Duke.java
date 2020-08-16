import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String chatbot = "Bob: ";
        String skipLine = "\n";
        String user = skipLine + "You: ";


        System.out.println(chatbot + "Hey there! I'm Bob" + skipLine + "What can I do for you today?");
        System.out.println(user);

        Scanner sc = new Scanner(System.in);

        while (true) {
            String echoUser = sc.nextLine();

            boolean exit = echoUser.equals("bye");

            if (exit) {
                break;
            }

            System.out.println(skipLine + chatbot + echoUser);
            System.out.println(user);

        }

        sc.close();
        System.out.println(skipLine + chatbot + "Goodbye! Have a nice day :D");

    }
}
