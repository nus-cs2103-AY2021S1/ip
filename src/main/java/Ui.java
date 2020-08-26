import java.util.Scanner;

public class Ui {

    static String line = "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-";
    static Scanner input = new Scanner(System.in);
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";


    public static void processInput(TaskList taskList) {

        printWithLines("Hello! My name is Duketh Puketh III, but you can call me\n" + logo +
                "\n How may I help you today? :)\n");

        String nextInput = input.nextLine();
        String[] inputParts = nextInput.split(" ", 2);
        String inputPrefix = inputParts[0];
        String inputSuffix = inputParts.length == 1 ? "" : inputParts[1];

        while (!nextInput.equals("bye")) {

            try {
                Parser.handleInput(inputPrefix, inputSuffix, taskList);
            } catch (DukeException dukeException) {
                printWithLines(dukeException + "\n");
            }


            nextInput = input.nextLine();
            inputParts = nextInput.split(" ", 2);
            inputPrefix = inputParts[0];
            inputSuffix = inputParts.length == 1 ? "" : inputParts[1];

        }

        printWithLines("Bye! I'll see you again next time!\n");

    }

    public static void printWithLines(String output) {
        System.out.println(line + "\n" + output + line);
    }





    public static boolean isBlankString(String string) {
        if (string.length() != 0) {
            for (char c : string.toCharArray()) {
                if (c != ' ') {
                    return false;
                }
            }
        }
        return true;
    }

}
