public class PrintDuke {
    private static void printDashes() {
        int length = 60;
        System.out.println("_".repeat(length));
    }

    protected static void printWithDashes(String str) {
        printDashes();
        System.out.println(str);
        printDashes();
    }

    protected static void printLogo() {
        String logo = "  ____        _        \n"
                + " |  _ \\ _   _| | _____ \n"
                + " | | | | | | | |/ / _ \\\n"
                + " | |_| | |_| |   <  __/\n"
                + " |____/ \\__,_|_|\\_\\___|\n\n";
        String greeting = logo + " Hello! I'm Duke\n" + " What can I do for you?";
        printWithDashes(greeting);
    }

    protected static void printExitMessage() {
        String bye = "Bye. Hope to see you again soon!";
        printWithDashes(" " + bye);
    }
}
