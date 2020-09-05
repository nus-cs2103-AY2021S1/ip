import javafx.fxml.FXML;

/**
 * Main Class here.
 */
public class Duke{
    public static void main(String[] args) {
        ChatBot.start();
    }

    public String init(){
        return ChatBot.start();
    }

    public String getResponse(String input){
        return ChatBot.getResponse(input);
    }

}