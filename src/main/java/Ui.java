import java.util.Scanner;

public class Ui {

    public void start(TaskList currList) {
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser(currList);
        while (sc.hasNext()) {
            String inputMsg = sc.nextLine();
            if (inputMsg.equals("bye")) { // ends the bot
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            parser.processMsg(inputMsg);
        }
        sc.close();
    }
}
