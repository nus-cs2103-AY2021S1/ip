import java.util.Scanner;

public class Duke {

    //print welcome message
    public void printWelcomeMessage() {
        String emoji = new String(Character.toChars(0x1F423));
        String welcomeMessage = "    ____________________________________________________________\n"
                + "    Hello! I'm ByteMe " + emoji + emoji + emoji + "\n"
                + "    What can I do for you? (Don't bite me!)\n"
                + "    ____________________________________________________________\n";

        System.out.println(welcomeMessage);
    }

    //respond to different user input
    public void respond() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String instruction = sc.nextLine();
            if (instruction.equals("list")) {
                String msgForList = "    ____________________________________________________________\n"
                        + "    list \n"
                        + "    ____________________________________________________________\n";
                System.out.println(msgForList);
            } else if (instruction.equals("blah")) {
                String msgForBlah = "    ____________________________________________________________\n"
                        + "    blah \n"
                        + "    ____________________________________________________________\n";
                System.out.println(msgForBlah);
            } else if (instruction.equals("bye")) {
                String msgForBye = "    ____________________________________________________________\n"
                        + "    Bye. Hope to see you again soon! \n"
                        + "    ____________________________________________________________\n";
                System.out.println(msgForBye);
                break;
            }
        }
        sc.close();
    }

    //run bot
    public static void main(String[] args) {
        Duke myBot = new Duke();
        myBot.printWelcomeMessage();
        myBot.respond();
    }
}
