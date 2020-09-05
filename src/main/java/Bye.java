/**
 * Ends the ChatBot.
 */
public class Bye extends Command {

    Bye() {
        this.name = "bye";
        this.usage = "bye";
        this.description = "I will go to sleep";
    }

    public String response() {
        return "I-I'm gonna go sleep Morty, d-dont disturb me anymore unless you " +
                " wanna get blasted by my *BURRRRRPPP* lazers";
    }

    /**
     * Sets end parameter as true.
     */
    public void endBot() {
        ChatBot.hasEnded = true;
    }
}
