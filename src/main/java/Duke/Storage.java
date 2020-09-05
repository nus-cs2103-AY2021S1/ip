package Duke;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates the operations related to the I/O of the log file.
 */
public class Storage {
    private String pathname;
    private List<Task> data = new ArrayList<>();

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
            boolean isDone = line.charAt(11) == 'Y';
            if(type == 'T') {
                Todo t = new Todo(line.substring(14), isDone);
                data.add(t);
            } else if(type == 'U') {
                String[] parts = line.substring(5).split(" ");
                String description = "";
                int length = parts.length;
                for(int i = 1; i < length - 2; i++) {
                    description += parts[i] + " ";
                }
                DurationTask u = new DurationTask(description,
                        Integer.parseInt(parts[parts.length - 2].substring(1)), isDone);
                data.add(u);
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
                                0, parts[length - 1].split(":")[1].length() - 1))), isDone);
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
                                0, parts[length - 1].split(":")[1].length() - 1))), isDone);
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
        int res = 0;
        if(month.equals("JAN")) {
            res = 1;
        } else if(month.equals("FEB")) {
            res = 2;
        } else if(month.equals("MAR")) {
            res = 3;
        }else if(month.equals("APR")) {
            res = 4;
        }else if(month.equals("MAY")) {
            res = 5;
        }else if(month.equals("JUN")) {
            res = 6;
        } else if(month.equals("JUL")) {
            res = 7;
        } else if(month.equals("AUG")) {
            res = 8;
        } else if(month.equals("SEP")) {
            res = 9;
        } else if(month.equals("OTC")) {
            res = 10;
        } else if(month.equals("NOV")) {
            res = 11;
        } else if(month.equals("DEC")) {
            res = 12;
        }
        return res;
    }
}
