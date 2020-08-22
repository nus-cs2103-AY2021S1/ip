import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates the operations related to the I/O of the log file.
 */
public class Storage {
    String pathname;
    List<Task> data = new ArrayList<>();

    /**
     * Constructs a storage object.
     * @param path the file path.
     */
    public Storage(String path) {
        this.pathname = path;
    }

    /**
     * Reads data from the log file.
     * @return data that represented by a list of Task objects.
     * @throws IOException Handle the exception when reading files.
     */
    public List<Task> readFile() throws IOException {
        String pathname = "./log.txt";
        File filename = new File(pathname);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);
        String line;
        line = br.readLine();
        while (line != null) {
            char type = line.charAt(8);
            boolean isDone = line.charAt(11) == '✓';
            if(type == 'T') {
                Todo t = new Todo(line.substring(14));
                if(isDone) {
                    t.done();
                }
                data.add(t);
            } else if(type == 'D') {
                String[] parts = line.substring(5).split(" ");
                String description = "";
                int length = parts.length;
                for(int i = 1; i < length - 5; i++) {
                    description += parts[i] + " ";
                }
                Deadline d = new Deadline(description, LocalDateTime.of(Integer.parseInt(parts[length - 2]),
                        getMonth(parts[length - 4]), Integer.parseInt(parts[length - 3]),
                        Integer.parseInt(parts[length - 1].split(":")[0]),
                        Integer.parseInt(parts[length - 1].split(":")[1].substring(
                                0, parts[length - 1].split(":")[1].length() - 1))));
                if(isDone) {
                    d.done();
                }
                data.add(d);
            } else {
                String[] parts = line.substring(5).split(" ");
                String description = "";
                int length = parts.length;
                for(int i = 1; i < length - 5; i++) {
                    description += parts[i] + " ";
                }
                Event e = new Event(description, LocalDateTime.of(Integer.parseInt(parts[length - 2]),
                        getMonth(parts[length - 4]), Integer.parseInt(parts[length - 3]),
                        Integer.parseInt(parts[length - 1].split(":")[0]),
                        Integer.parseInt(parts[length - 1].split(":")[1].substring(
                                0, parts[length - 1].split(":")[1].length() - 1))));
                if(isDone) {
                    e.done();
                }
                data.add(e);
            }
            line = br.readLine();
        }
        return this.data;
    }

    /**
     * Writes the data into the log file.
     * @param data the data that will be stored in the file.
     * @throws FileNotFoundException handle the exception when writing files.
     */
    public void writeFile(List<Task> data) throws FileNotFoundException {
        final PrintStream oldStdout = System.out;
        PrintStream ps = new PrintStream("./log.txt");
        System.setOut(ps);
        for (int i = 0; i < data.size(); i++) {
            System.out.printf("     %d.%s\n", i + 1, data.get(i).toString());
        }
        System.setOut(oldStdout);
    }

    /**
     * Converts the words of month into corresponding numbers.
     * @param month word representation of months.
     * @return number representation of months.
     */
    public static int getMonth(String month) {
        return switch (month) {
            case "JAN" -> 1;
            case "FEB" -> 2;
            case "MAR" -> 3;
            case "APR" -> 4;
            case "MAY" -> 5;
            case "JUN" -> 6;
            case "JUL" -> 7;
            case "AUG" -> 8;
            case "SEP" -> 9;
            case "OCT" -> 10;
            case "NOV" -> 11;
            case "DEC" -> 12;
            default -> 0;
        };
    }
}
