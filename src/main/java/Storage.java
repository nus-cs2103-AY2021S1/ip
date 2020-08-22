import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    String pathname;
    List<Task> data = new ArrayList<>();

    public Storage(String path) {
        this.pathname = path;
    }

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
                Deadline d = new Deadline(parts[1], LocalDateTime.of(Integer.parseInt(parts[5]), getMonth(parts[3]),
                        Integer.parseInt(parts[4]), Integer.parseInt(parts[6].split(":")[0]),
                        Integer.parseInt(parts[6].split(":")[1].substring(
                                0, parts[6].split(":")[1].length() - 1))));
                if(isDone) {
                    d.done();
                }
                data.add(d);
            } else {
                String[] parts = line.substring(5).split(" ");
                Event e = new Event(parts[1], LocalDateTime.of(Integer.parseInt(parts[5]), getMonth(parts[3]),
                        Integer.parseInt(parts[4]), Integer.parseInt(parts[6].split(":")[0]),
                        Integer.parseInt(parts[6].split(":")[1].substring(
                                0, parts[6].split(":")[1].length() - 1))));
                if(isDone) {
                    e.done();
                }
                data.add(e);
            }
            line = br.readLine();
        }
        return this.data;
    }

    public void writeFile(List<Task> data) throws FileNotFoundException {
        final PrintStream oldStdout = System.out;
        PrintStream ps = new PrintStream("./log.txt");
        System.setOut(ps);
        for (int i = 0; i < data.size(); i++) {
            System.out.printf("     %d.%s\n", i + 1, data.get(i).toString());
        }
        System.setOut(oldStdout);
    }

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
