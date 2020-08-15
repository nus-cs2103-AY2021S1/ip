import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        String greeting = "Hello human, I'm Duke the supporting chatbot \n"
                + "What can I do for you?";
        String exitCmd = "bye";
        String exitMsg = "Bye human. See you again soon!";
        String horizontalLine = "________________________________________";
        String cmdReq = "Your command: ";
        String lazyHumanBash = "You have nothing in your list. Why are you so lazy human?";

        ArrayList<String> tasks = new ArrayList<>();

        // logo and greeting
        System.out.println(logo + "\n" + horizontalLine);
        System.out.println(greeting + "\n" + horizontalLine);
        System.out.println(cmdReq);

        // getting command
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals(exitCmd)) {
            System.out.println(horizontalLine);
            if (input.equals("list")) {
                if (tasks.size() == 0) {
                    System.out.println(lazyHumanBash);
                } else {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                }
            } else {
                tasks.add(input);
                System.out.println("added: " + input);
            }
            System.out.println(horizontalLine);
            System.out.println(cmdReq);
            input = sc.nextLine();
        }

        // exit
        System.out.println(horizontalLine + "\n" +  exitMsg + "\n" + horizontalLine);
    }
}
