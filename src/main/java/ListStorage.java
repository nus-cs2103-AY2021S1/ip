import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListStorage {
    static final String dirPath = "../data";
    static final String filePath = "../data/duke.txt";

    public static List<Task> readFromFile() {
        Task temp;
        List<Task> tasks = new ArrayList<>();

        try {
            File dir = new File(dirPath);
            File file = new File(filePath);
            dir.mkdir();
            file.createNewFile();

            Scanner sc = new Scanner(file);


            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] instructions = line.split("\\|");
                int num = instructions.length;
                String type = instructions[0].strip();
                switch (type) {
                case "T":
                    if (num != 3) {
                        throw new DukeException("The data format of the file is incorrect\n");
                    }
                    temp = new ToDo(instructions[2].strip());
                    if (instructions[1].strip().equals("1")) {
                        temp.markAsDone();
                    }
                    tasks.add(temp);
                    break;
                case "D":
                    if (num != 4) {
                        throw new DukeException("The data format of the file is incorrect\n");
                    }
                    temp = new Deadlines(instructions[2].strip(), instructions[3].strip());
                    if (instructions[1].strip().equals("1")) {
                        temp.markAsDone();
                    }
                    tasks.add(temp);
                    break;
                case "E":
                    if (num != 4) {
                        throw new DukeException("The data format of the file is incorrect\n");
                    }
                    temp = new Events(instructions[2].strip(), instructions[3].strip());
                    if (instructions[1].strip().equals("1")) {
                        temp.markAsDone();
                    }
                    tasks.add(temp);
                    break;
                }
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return tasks;

    }

    public static void storeToFile(List<Task> tasks) {
        try {
            String breaker = " \\| ";
            FileWriter fileWriter = new FileWriter(filePath);
            for (int i = 0; i < tasks.size(); i++) {
                Task temp = tasks.get(i);
                String state = "0";
                if (temp instanceof ToDo) {
                    if (temp.isDone()) {
                        state = "1";
                    }
                    fileWriter.write("T" + breaker + state + breaker + temp.getDescription() + "\n");
                } else if (temp instanceof Deadlines) {
                    Deadlines deadline = (Deadlines) temp;
                    if (deadline.isDone()) {
                        state = "1";
                    }
                    fileWriter.write("D" + breaker + state + breaker + deadline.getDescription()
                            + breaker + deadline.getBy() + "\n");
                } else if (temp instanceof Events) {
                    Events event = (Events) temp;
                    if (event.isDone()) {
                        state = "1";
                    }
                    fileWriter.write("E" + breaker + state + breaker + event.getDescription()
                            + breaker + event.getStart() + "\n");
                }
            }
            fileWriter.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}
