public class Duke {
    public static void main(String[] args) {
        Chatbot bot = new Chatbot();
        try {
            bot.chat();
        } catch (DukeException ex) {
            System.out.println(ex);
        }
    }
}
