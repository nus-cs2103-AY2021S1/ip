import java.util.Scanner;

public class Duke {

    // Constants
    private static final String LINE = "____________________________________________________________";

    // INSTRUCTIONS
    private static final String BYE = "bye";
    private static final String HELP = "help";

    // User Interaction Text
    // Standardise by using only LINE here. NEWLINE to be used for functions
    // Maybe can include Username in introductions
    private static final String INTRODUCTION =
            LINE + "\n" +
                    "Hello! I am Duke, your Personal Assistant!\n" + // START OF INTRODUCTIONS
                    "What can I do for you today?\n" +
                    "* Type \"help\" to see the available instructions!\n" +
                    LINE; // END OF INTRODUCTIONS

    private static final String INSTRUCTIONS =
            "AVAILABLE INSTRUCTIONS:\n" +
                    " help - Display Available Instructions\n" +
                    " bye - Terminate Duke\n" +
                    LINE; // END OF INSTRUCTIONS

    private static final String OUTRO = "Goodbye. Hope to see you soon!\n" + LINE;

    public static void main(String[] args) {
        // Initialisation of Duke
        System.out.println(INTRODUCTION);

        Scanner sc = new Scanner(System.in);

        // Echo User Instructions
        instructionLoop: while (sc.hasNext()) { // labelling of while-loop
            String instruction = sc.next();

            switch (instruction.toLowerCase()) {
                case BYE:
                    break instructionLoop;
                case HELP:
                    System.out.println(INSTRUCTIONS);
                    break;
                default:
                    System.out.println(instruction + "\n" + LINE);
            }
        }

        // OUTRO
        System.out.println(OUTRO);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }
}