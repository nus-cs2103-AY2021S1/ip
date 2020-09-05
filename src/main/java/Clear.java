public class Clear extends Command {

    /**
     * Adds 20 newline Characters to the screen for better User Experience.
     */
    Clear() {
        this.name = "clear";
        this.usage = "clear";
        this.description = "Clears the screen with newline characters";
    }

    public String response() {
        return "Dont you get it Morty? We're in a GUI you idiot, you cant" +
                " just clear all the stuff I've said away.";
    }
}
