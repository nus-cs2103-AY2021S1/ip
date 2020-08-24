public class Clear extends Command{

    Clear(){
        this.name = "clear";
        this.usage = "clear";
        this.description = "Clears the screen with newline characters";
    }

    //Currently has 20 newline characters
    public String response(){
        return "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
    }
}
