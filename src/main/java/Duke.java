import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private ArrayList<String> listOfTextEntered;

    Duke() {
        this.listOfTextEntered = new ArrayList<>(100);
    }

    // store user input and respond to different input
    public void addAndRespond() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String instruction = sc.nextLine();
            if (instruction.equals("list")) {
                String msgForList = "    ____________________________________________________________\n";
                for (int i = 0; i < listOfTextEntered.size(); i++) {
                    msgForList += "    " + (i + 1) + ". " + listOfTextEntered.get(i) + "\n";
                }
                msgForList += "    ____________________________________________________________\n";
                System.out.println(msgForList);
            } else if (instruction.equals("bye")) {
                String msgForBye = "    ____________________________________________________________\n"
                        + "    Bye. Hope to see you again soon! \n"
                        + "    ____________________________________________________________\n";
                System.out.println(msgForBye);
                break;
            } else {
                listOfTextEntered.add(instruction);
                String msgForAdd = "    ____________________________________________________________\n"
                        + "    added: "
                        + instruction + "\n"
                        + "    ____________________________________________________________\n";
                System.out.println(msgForAdd);
            }
        }
        sc.close();
    }

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
        //myBot.respond();
        myBot.addAndRespond();
    }
}
