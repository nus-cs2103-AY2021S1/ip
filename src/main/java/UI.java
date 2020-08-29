import java.util.ArrayList;

/**
 * <p>The UI class deals with interactions with the user.</p>
 */
public class UI {
    private String separationLine = "     _____________________________________________________\n";
    private String indentation = "      ";
    private String topPartOfBotReplyMessage = separationLine + indentation;
    private String botPartOfBotReplyMessage = "\n" + separationLine.substring(0, separationLine.length() - 1);

    /**
     * Prints a formatted message.
     * @param message A String that represents the message to print
     */
    public void printMessage(String message) {
        System.out.println(topPartOfBotReplyMessage + message + botPartOfBotReplyMessage);
    }

    /**
     * Returns the a list that contains the bot's reply to different user inputs:
     * <li>If the user input is "find", returns a list of 2 strings, the first one is the
     * reply if nothing a found, the second one is the reply if at least 1 element is found.</li>
     * <li>Else, returns a list of 1 string responding to the user input.</li>
     * @param userInput A String to represent user input
     * @return A list of 1 or 2 strings to represent the bot's reply to the respective input
     */
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
            botReply.set(0, "Sorry can't find any tasks with such keyword.");
            botReply.add("Found 'em. But at what cost... \n");
        } else {
            botReply.set(0, "Wow, another task. Added. You sure you can finish them all? \n");
        }
        return botReply;
    }

    /**
     * Formats the body of the bot's reply.
     * @param body A String to represent the body of bot's reply
     * @return A String to represent the formatted body
     */
    public String formatBotReplyBody(String body) {
        return indentation + body;
    }
}
