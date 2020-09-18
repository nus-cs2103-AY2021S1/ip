/**
 * interactions between user and duke
 */
public class Ui {
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private String reply;


    public Ui(){
        reply = "";
    }
    /**
     * shows hello message
     */
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
    }

    /**
     * says goodbye to user
     */
    public void showGoodbye(){
        reply = "Bye, hope to see you again!";
    }

}
