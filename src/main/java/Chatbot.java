public class Chatbot {
    private int lengthOfLine = 45;
    String[] tasks = new String[100];
    int numOfTasks = 0;

    public String getHorizontalLine() {
        String line = "";
        for (int i = 0; i < lengthOfLine; i++) {
            line = line + "-";
        }
        return line;
    }
}
