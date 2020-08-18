public class Duke {
    public static String line = "____________________________________________________________";
    public static String start = "Hello! I'm Duke, your personal assistant. \nWhat can I do for you?";
    public static String end = "Goodbye! Hope to see you again soon. :)";
    public static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {
        System.out.println(line);
        System.out.println(logo + start);
        System.out.println(line);
        System.out.println(end);
        System.out.println(line);
    }
}
