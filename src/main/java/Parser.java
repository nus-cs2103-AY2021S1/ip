public class Parser {
    public static boolean isTaskModification(String type) {
        return type.equals("done") || type.equals("delete");
    }

    public static String[] processModification(String line) {
        String[] lineData = line.split(" ");
        int i = Integer.parseInt(lineData[1]) - 1;

        String[] processedData = {lineData[0], String.valueOf(i)};

        return processedData;
    }

    public static String[] processTaskItem(String line) {
        String[] lineData = line.split(" ");

        return lineData;
    }

    public static String processFind(String line) {
        String[] lineData = line.split(" ");

        return lineData[1];
    }
}
