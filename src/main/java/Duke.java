public class Duke {
    private static String formatReply(String text) {
        String line = "\t_______________________________________________________________";
        return line + "\n\t\t" + text.replaceAll("\\n", "\n\t\t") + "\n" + line;
    }
    public static void main(String[] args) {
        System.out.println(formatReply("Hello! I'm Duke\nWhat can I do for you?"));
    }
}
