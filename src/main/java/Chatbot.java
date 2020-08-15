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
        if (str.contains(" ")) {
            String[] split1 = str.split(" ", 2);
            String type = split1[0];
            String temp = split1[1];
            if (temp.contains("/")) {
                String[] split2;
                if (temp.contains("/by")) {
                    split2 = temp.split("/by", 2);
                } else {
                    split2 = temp.split("/at", 2);
                }
                String taskDescription = split2[0];
                String time = split2[1];
                String[] res = {type, taskDescription, time};
                return res;
            } else {
                String taskDescription = temp;
                String[] res = {type, taskDescription};
                return res;
            }
        } else {
            String[] res = {str};
            return res;
        }
    }
}
