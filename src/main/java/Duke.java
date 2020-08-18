public class Duke {

    private final String[] args;
    private static final String dash = "    ____________________________________________________________";


    public Duke(String[] args) {
        this.args = args;
    }

    private void printWelcome() {
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";
        System.out.println(dash);
        System.out.println(logo);
        System.out.println("     Hello I'm Duke\n     What can I do for you");
        System.out.println(dash);

    }

    private void printResult(String... arg) {
        printWelcome();
        for (String str : arg) {
            respond(str);
            if (str.toLowerCase().equals("bye")) {
                System.exit(0);
            }
        }
    }

    private void run() {
        printResult(this.args);
    }

    private void respond(String str) {
        System.out.println(str);
        System.out.println(dash);
        if (str.toLowerCase().equals("bye")) {
            System.out.println("     Bye. Hope to see you again!");
        } else {
            System.out.println("     " + str);
        }
        System.out.println(dash);
    }

    public static void main(String[] args) {
        new Duke(args).run();
    }
}
