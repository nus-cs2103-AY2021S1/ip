package duke;

public class Ui {
    private static String logo = "\n"
            + " oooooooooooo                                                 oooo        \n"
            + "d'\"\"\"\"\"\"d888'                                                 `888        \n"
            + "      .888P    .ooooo.  oooo d8b  .ooooo.   .oooo.   oooo d8b  888  oooo  \n"
            + "     d888'    d88' `88b `888\"\"8P d88' `88b `P  )88b  `888\"\"8P  888 .8P'   \n"
            + "   .888P      888   888  888     888   888  .oP\"888   888      888888.    \n"
            + "  d888'    .P 888   888  888     888   888 d8(  888   888      888 `88b.  \n"
            + ".8888888888P  `Y8bod8P' d888b    `Y8bod8P' `Y888\"\"8o d888b    o888o o888o \n"
            + "                                                                          \n"
            + "                                                                          \n"
            + "                                                                          \n";
    private String lowerLine = "\n(¯`·._.·(¯`·._.· Zoroark ·._.·´¯)·._.·´¯)\n";
    private String upperrLine = "\n(¯`·._.·(¯`·._.· (0 _ 0) ·._.·´¯)·._.·´¯)\n";
    private String goodbye = " __   __   __   __   __       ___ \n"
            + "/ _` /  \\ /  \\ |  \\ |__) \\ / |__  \n" + "\\__> \\__/ \\__/ |__/ |__)  |  |___ \n"
            + "                                  ";
    private String response;

    /**
     * Ui constructor.
     */
    public Ui() {
        response = "";
    }

    /**
     * Gets Duke's welcome.
     *
     * @return duke's welcome.
     */
    public static String getWelcome() {
        return "Hello I am\n" + logo + "How can I help you?";
    }

    /**
     * Adds message to response.
     *
     * @param message message to be added.
     */
    public void addMessage(String message) {
        response += message + '\n';
    }

    /**
     * Gets the current response.
     *
     * @return the current response.
     */
    public String getResponse() {
        return upperrLine + '\n' + response + lowerLine;
    }

    /**
     * Clears the current response.
     */
    public void clearResponse() {
        response = "";
    }

    /**
     * Sets response to be Duke's goodbye.
     */
    public void addGoodbye() {
        response = goodbye;
    }
}
