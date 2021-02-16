import java.io.IOException;
import java.util.Scanner;

/**
 * This is class to interact with the user.
 */
public class Ui {
    private static final String HOME = System.getProperty("user.home");
    private static final java.nio.file.Path PATH = java.nio.file.Paths.get(HOME, "ip", "data.txt");
    private String enter = "\n";

    private Parser parser = new Parser();

    /**
     * Bids user farewell.
     */
    public String exit() {
        String result = "";

        result += "ok u can leave lmao";

        return result;
    }

    /**
     * List all Tasks in save file.
     *
     * @return Next line that user inputs.
     */
    public String list() {
        String result = "";

        try {
            int counter = 1;
            Scanner myReader = new Scanner(PATH);
            myReader.nextLine(); // skip first line (total)
            myReader.nextLine(); // skip second line (statistics)

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                result += counter + ". " + parser.stringToTask(data) + enter;
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Informs user of completed task.
     *
     * @return Next line that user inputs.
     */
    public String complete(Task t) {
        String result = "";

        result += "gfy youve managed to finish the following..." + enter;
        t = t.completeTask();
        result += t + enter;

        return result;
    }

    /**
     * Informs user of deleted task.
     *
     * @return Next line that user inputs.
     */
    public String delete(Task t, int total) {
        String result = "";

        result += "removed!! ^^" + enter;
        result += t + enter;
        result += "total task: " + total + "\n:o";

        return result;
    }

    /**
     * Informs user of added task.
     *
     * @return Next line that user inputs.
     */
    public String add(Task t, int total) {
        String result = "";

        result += "added!" + enter;
        result += t + enter;
        result += "total task: " + total + "\n:o";

        return result;
    }

    /**
     * Informs user of exception.
     *
     * @return Next line that user inputs.
     */
    public String handleException(Exception e) {
        String result = "";

        result += e.getMessage();

        return result;
    }

    /**
     * Informs user of all results found with specified keyword.
     * @param keyword Keyword user is interested in.
     * @return String of all tasks with user keyword.
     * @throws IOException If Scanner is unable to find file.
     */
    public String find(String keyword) throws IOException {
        String result = "";

        int counter = 1;
        Scanner reader = new Scanner(PATH);

        int total = reader.nextInt(); // to skip the first integer
        String line = reader.nextLine();

        while (reader.hasNextLine()) {
            if (line.contains(keyword)) {
                result += counter + ". " + parser.stringToTask(line) + enter;
                counter++;
            }
            line = reader.nextLine();
        }

        if (line.contains(keyword)) {
            result += counter + ". " + parser.stringToTask(line) + enter;
        }

        return result;
    }
}
