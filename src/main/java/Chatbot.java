public class Chatbot {
    private int lengthOfLine = 45;
    Task[] tasks = new Task[100];
    int numOfTasks = 0;

    public String getHorizontalLine() {
        String line = "";
        for (int i = 0; i < lengthOfLine; i++) {
            line = line + "-";
        }
        return line;
    }

    public String[] parseStringBySpace(String str) {
        String[] res = str.split(" ");
        return res;
    }
}
