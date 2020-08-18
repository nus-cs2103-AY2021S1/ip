import java.util.Scanner;

public class Duke {

    static Scanner input = new Scanner(System.in);
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    static String line = "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-";

    public static void main(String[] args) {
        printWithLines("Hello! My name is Duketh Puketh III, but you can call me\n" + logo +
                "\n How may I help you today? :)");
        processInput();
        printWithLines("Bye! I'll see you again next time!");
    }

    private static void processInput() {

        String nextInput = input.nextLine();
        boolean bye = nextInput.equals("bye");

        while (!bye) {
            printWithLines(nextInput);
            nextInput = input.nextLine();
            bye = nextInput.equals("bye");
        }

    }

    private static void printWithLines(String output) {
        System.out.println(line + "\n" + output + "\n" + line);
    }

}