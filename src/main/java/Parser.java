public class Parser {

    public static boolean isList(String line) {
        return line.equals("list");
    }

    public static boolean isExit(String line) {
        return line.toLowerCase().contains("bye");
    }

    public static boolean isDone(String line) {
        return line.length() >= 4 && line.substring(0, 4).equals("done");
    }

    public static int taskType(String line) {
        if (line.length() >= 8 && line.substring(0, 8).equals("deadline")) {
            return 2;
        }
        else if (line.length() >= 5 && line.substring(0, 5).equals("event")) {
            return 3;
        }
        else if (line.length() >= 4 && line.substring(0, 4).equals("todo")) {
            return 1;
        }
        else return -1;
    }

    public static String getName(String line) {
        if (taskType(line) == 1) {
            return line.substring(5);
        }
        else if (taskType(line) == 2) {
            return line.split(" /by ")[0].substring(9);
        }
        else {
            return line.split(" /at ")[0].substring(6);
        }
    }

    public static String getTime(String line) {
        if (taskType(line) == 2) {
            return line.split(" /by ")[1];
        }
        else {
            return line.split(" /at ")[1];
        }
    }

}
