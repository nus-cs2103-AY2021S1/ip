import java.util.Scanner;
public class Duke {
    public static String duke = "Duke> ";
    public static String cmd = "Input> ";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner sc = new Scanner(System.in);
        String userInput = "";

        System.out.println(logo);
        startupMsg();

        do {
            System.out.print(cmd);
            userInput = sc.nextLine();
            System.out.println(duke + userInput);

        } while(!userInput.equals("bye"));

        System.out.println(duke + "Bye!");
    }

    private static void startupMsg() {
        System.out.println(duke + "Hi I'm Duke!");
        System.out.println(duke + "What can I do for you?");
    }
}
