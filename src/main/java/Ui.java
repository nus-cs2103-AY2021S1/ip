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
    private static String lowerLine = "\n(¯`·._.·(¯`·._.· Zoroark ·._.·´¯)·._.·´¯)\n";
    private static String upperLine = "    ♫♪.ılılıll|̲̅̅●̲̅̅|̲̅̅=̲̅̅|̲̅̅●̲̅̅|llılılı.♫♪\n\n";
    private static String goodbye = " __   __   __   __   __       ___ \n"
            + "/ _` /  \\ /  \\ |  \\ |__) \\ / |__  \n" + "\\__> \\__/ \\__/ |__/ |__)  |  |___ \n"
            + "                                  ";

    public static void showWelcome() {
        System.out.println("Hello I am\n" + logo + "How can I help you?");
        return;
    }

    public static void showMessage(String message) {
        System.out.println(message);
    }

    public static void showUpperLine() {
        System.out.println(upperLine);
    }

    public static void showLowerLine() {
        System.out.println(lowerLine);
    }

    public static void showGoodbye() {
        System.out.println(upperLine + goodbye + "\n" + lowerLine);
    }
}
