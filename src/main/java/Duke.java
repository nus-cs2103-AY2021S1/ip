import java.util.Scanner;

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

        // logo and greeting
        System.out.println(logo + "\n" + horizontalLine);
        System.out.println(greeting + "\n" + horizontalLine);
        System.out.print(cmdReq);

        // getting command
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals(exitCmd)) {
            System.out.println(horizontalLine);
            System.out.println(input );
            System.out.println(horizontalLine);
            System.out.println(cmdReq);
            input = sc.nextLine();
        }

        // exit
        System.out.println(horizontalLine + "\n" +  exitMsg + "\n" + horizontalLine);
    }
}
