/**
 * interactions between user and duke
 */
public class Ui {
    private String reply;

    /**
     * Ui constructor
     */
    public Ui(){
        reply = "";
    }

    /**
     * shows hello message
     */
    public static String showWelcome(){
        return "Hello! I'm Duke" + "\n" + "What can I do for you?";
    }

    /**
     * Update reply
     * @param message the message that duke replies
     */
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
