import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        Scanner fetch = new Scanner(System.in);
        DukeBot myDukeBot = new DukeBot();
        myDukeBot.greeting();

        myDukeBot.listener();

        System.out.println("End");

        String userInput;
        do {
            userInput = fetch.nextLine();
            myDukeBot.echoWithExit(userInput);
        }while (!userInput.equals("bye"));



    }
}
