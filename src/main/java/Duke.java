public class Duke {

    // this field is used when Duke requires a horizontal line
    public static final String LINE = "    ____________________________________________________________";

    // this function greets the user when Duke is started
    public static void greeting(){
        System.out.println(LINE);
        System.out.println("     Hello! I'm Duke and I was designed by Xuan Ming!\n");
        System.out.println("     What can I do for you?");
        System.out.println(LINE);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greeting();
    }
}
