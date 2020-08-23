/**
 * deals with making sense of the user command
 */
public class Parser {

    /**
     * Returns whether type is a task modification
     *
     * @param type type of Task
     *
     * @return whether type is a task modification
     */
    public static boolean isTaskModification(String type) {
        return type.equals("done") || type.equals("delete");
    }

    /**
     * Processes line when its a task modification
     *
     * @param line line
     *
     * @return array of parsed strings
     */
    public static String[] processModification(String line) {
        String[] lineData = line.split(" ");
        int i = Integer.parseInt(lineData[1]) - 1;

        String[] processedData = {lineData[0], String.valueOf(i)};

        return processedData;
    }

    /**
     * Processes line when its a task item
     *
     * @param line line
     *
     * @return array of parsed strings
     */
    public static String[] processTaskItem(String line) {
        String[] lineData = line.split(" ");

        return lineData;
    }

    public static String processFind(String line) {
        String[] lineData = line.split(" ");

        return lineData[1];
    }
}
