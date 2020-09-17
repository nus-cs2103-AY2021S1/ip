/**
 * interactions between user and duke
 */
public class Ui {
<<<<<<< HEAD
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private String reply;
=======
>>>>>>> master


    public Ui(){
        reply = "";
    }
    /**
     * shows hello message
     */
<<<<<<< HEAD
    public static String showWelcome(){
        return "Hello from\n" + logo + "Hello! I'm Duke" + "\n" + "What can I do for you?";
    }

    public void addResponse(String message){
        reply = (message + "\n");
    }

    public String getReply(){
        return reply;
    }

    public void clearReply(){
        reply = "";
=======
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
>>>>>>> master
    }

    /**
     * says goodbye to user
     */
    public static void showGoodbye(){
        System.out.println("Bye. Hope to see you again soon!");
    }

}
