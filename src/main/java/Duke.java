import main.java.Task;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private ArrayList<Task> listOfTaskEntered;

    public Duke() {
        this.listOfTaskEntered = new ArrayList<>(100);
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

    //mark a task as done
    public void markAsDone(int num) {
        listOfTaskEntered.get(num - 1).markAsDone();
        String msgForDone = "    ____________________________________________________________\n"
                + "    Nice! I 've marked this task as done: \n"
                + "       [" + listOfTaskEntered.get(num - 1).getStatusIcon() + "] "
                + listOfTaskEntered.get(num - 1).getTaskDescription() + "\n"
                + "    ____________________________________________________________\n";
        System.out.println(msgForDone);
    }

    //store user input and respond to different input
    public void run() {
        this.printWelcomeMessage();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String instruction = sc.nextLine();
            if (instruction.equals("list")) {
                String msgForList = "    ____________________________________________________________\n";
                msgForList += "    Here are the tasks in your list: \n";
                for (int i = 0; i < listOfTaskEntered.size(); i++) {
                    msgForList += "    " + (i + 1) + ". ["
                            + listOfTaskEntered.get(i).getStatusIcon() + "] "
                            + listOfTaskEntered.get(i).getTaskDescription() + "\n";
                }
                msgForList += "    ____________________________________________________________\n";
                System.out.println(msgForList);
            } else if (instruction.equals("bye")) {
                String msgForBye = "    ____________________________________________________________\n"
                        + "    Bye. Hope to see you again soon! \n"
                        + "    ____________________________________________________________\n";
                System.out.println(msgForBye);
                break;
            } else if (instruction.substring(0, 5).equals("done ")){
                int num = Integer.parseInt(instruction.substring(5));
                this.markAsDone(num);
            } else {
                Task newTask = new Task(instruction);
                listOfTaskEntered.add(newTask);
                String msgForAdd = "    ____________________________________________________________\n"
                        + "    added: "
                        + instruction + "\n"
                        + "    ____________________________________________________________\n";
                System.out.println(msgForAdd);
            }
        }
        sc.close();
    }

    //run bot
    public static void main(String[] args) {
        Duke myBot = new Duke();
        //myBot.respond();
        myBot.run();
    }
}
