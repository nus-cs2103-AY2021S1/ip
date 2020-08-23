import java.util.ArrayList;

public class UI {
    private String separationLine = "     _____________________________________________________\n";
    private String indentation = "      ";
    private String topPartOfBotReplyMessage = separationLine + indentation;
    private String botPartOfBotReplyMessage = "\n" + separationLine.substring(0, separationLine.length() - 1);

    public void printMessage(String message) {
        System.out.println(topPartOfBotReplyMessage + message + botPartOfBotReplyMessage);
    }

    public ArrayList<String> botReplyHeading(String userInput) {
        ArrayList<String> botReply = new ArrayList<>();
        botReply.add("");
        if (userInput.trim().equals("list")) {
             botReply.set(0, "Checking the whole list doesn't make you finish anything faster... \n");
        } else if (userInput.trim().startsWith("done")) {
            botReply.set(0, "Wah finally. Wondering how long more I need to wait... \n");
        } else if (userInput.trim().startsWith("delete")) {
            botReply.set(0, "Good good... Okay removed! Looks more apt for a lazy ass like you. \n");
        } else if (userInput.trim().startsWith("find")) {
            botReply.set(0,  "Sorry can't find any tasks with such keyword.");
            botReply.add("Found 'em. But at what cost... \n");
        } else {
            botReply.set(0, "Wow, another task. Added. You sure you can finish them all? \n");
        }
        return botReply;
    }

    public String formatBotReplyBody(String body) {
        return indentation + body;
    }
}
