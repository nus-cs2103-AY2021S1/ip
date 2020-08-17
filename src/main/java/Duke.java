import java.util.Scanner;

public class Duke {

    public static String dashedLineBreak() {
        String dashedLine = "- ";
        return dashedLine.repeat(24);
    }

    public static void init() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm your loyal Duke.");
        System.out.println("How may I serve you, my Queen?");
        System.out.println();
    }

    public static void main(String[] args) {
        init();
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String userInput = sc.nextLine();
            if (!userInput.equalsIgnoreCase("bye")) {
                System.out.println(userInput);
                System.out.println(dashedLineBreak());
            } else {
                System.out.println("Your wish is my command, milady. Till I see you again.");
                sc.close();
                System.exit(0);
            }
        }
    }
}
