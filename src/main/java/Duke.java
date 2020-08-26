import java.io.IOException;

public class Duke {

    /**
     * Method to initialize a Chatbot instance and start the bot. Catch errors specific to the bot.
     * @param args
     */
    public static void main(String[] args) {
        Chatbot bot = new Chatbot();
        try {
            bot.chat();
        } catch (DukeException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
