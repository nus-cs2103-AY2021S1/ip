/**
 * Ends the ChatBot.
 */
public class Bye extends Command{
    Bye(){
        this.name = "bye";
        this.usage = "bye";
        this.description = "Duke Chatbot will power down";
    }

    public String response(){
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Sets end parameter as true.
     */
    public void endBot(){
        ChatBot.hasEnded = true;
    }
}
