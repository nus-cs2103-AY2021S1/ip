/**
 * interactions between user and duke
 */
public class Ui {

    /**
     * shows hello message
     */
    public static void showWelcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String hello = "Hello! I'm Duke";
        String question = "What can I do for you?";
        System.out.println(hello + "\n" + question);
    }

    /**
     * says goodbye to user
     */
    public static void showGoodbye(){
        System.out.println("Bye. Hope to see you again soon!");
    }

}
