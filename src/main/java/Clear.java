public class Clear extends Command{

    /**
     * Adds 20 newline Characters to the screen for better User Experience.
     */
    Clear(){
        this.name = "clear";
        this.usage = "clear";
        this.description = "Clears the screen with newline characters";
    }

    public String response(){
        return "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
    }
}
