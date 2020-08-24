import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;

public class Storage {

    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
//        String currentDir = System.getProperty("user.dir");
//        String path = currentDir + File.separator + "data" + File.separator + "duke.txt";
        ArrayList<Task> tasks = new ArrayList<>();
//        int total = 0;
        File f = new File(filePath);
        if (f.exists() && !f.isDirectory()) {
            try {
                for (String line : Files.readAllLines(Paths.get(filePath))) {
                    String[] data = line.split(" \\| ");
                    String taskType = data[0];

                    switch (taskType) {
                        case "T":
                            Todo todo = new Todo(data[2]);
                            if (data[1].equals("1")) {
                                todo.markAsDone();
                            }
                            tasks.add(todo);
//                            total++;
                            break;
                        case "D":
                            Deadline deadline = new Deadline(data[2], data[3]);
                            if (data[1].equals("1")) {
                                deadline.markAsDone();
                            }
                            tasks.add(deadline);
//                            total++;
                            break;
                        case "E":
                            Event event = new Event(data[2], data[3]);
                            if (data[1].equals("1")) {
                                event.markAsDone();
                            }
                            tasks.add(event);
//                            total++;
                            break;
                    }
                }
                return tasks;
            } catch (IOException ex) {
                System.out.println("Error reading file" + ex);
                throw new DukeException("Error reading file");
//                return new ArrayList<>();
            }
        } else {
            try {
                f.getParentFile().mkdirs();
                f.createNewFile();
//                return new ArrayList<>();
            } catch (IOException ex) {
                System.out.println("Error creating file" + ex);
                throw new DukeException("Error reading file");
            }
        }
        return new ArrayList<>();
    }

    public void writeNewDataToFile(String taskType, String done, String description, String deadline) {
//        String currentDir = System.getProperty("user.dir");
//        String path = currentDir + File.separator + "data" + File.separator + "duke.txt";
        try {
            FileWriter myWriter = new FileWriter(filePath, true);
            switch (taskType) {
                case "T":
                    myWriter.write("\n" + taskType + " | " + done + " | " + description);
                    myWriter.close();
                    break;
                case "D":
                case "E":
                    myWriter.write("\n" + taskType + " | " + done + " | " + description + " | " + deadline);
                    myWriter.close();
            }
        } catch (IOException ex) {
            System.out.println("Problem writing to file" + ex);
        }
    }

    public void editCurrentDataInFile(int taskNumber, String taskType, String done,
                                             String description, String deadline, int total) {
        try {
//            String currentDir = System.getProperty("user.dir");
//            String pathToFile = currentDir + File.separator + "data" + File.separator + "duke.txt";
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            //String buffer to store contents of the file
            StringBuffer sb = new StringBuffer("");

            //Keep track of the line number
            int linenumber = 1;
            String line;
            System.out.println("total: " + total);
            System.out.println("tasknumer: " + taskNumber);

            while ((line = br.readLine()) != null) {
                //Store each valid line in the string buffer
                if (linenumber != taskNumber && linenumber != total) {
                    System.out.println("linenumber: " + linenumber + " line: " + line);
                    sb.append(line + "\n");
                } else if (linenumber != taskNumber) {
                    System.out.println("linenumber: " + linenumber + " line: " + line);
                    sb.append(line);
                } else {
                    String data;
                    if (taskType.equals("T")) {
                        data = taskType + " | " + done + " | " + description;
                    } else {
                        data = taskType + " | " + done + " | " + description + " | " + deadline;
                    }
                    System.out.println("linenumber: " + linenumber + " line: " + line);

                    if (linenumber == total) {
                        sb.append(data);
                    } else {
                        sb.append(data + "\n");
                    }
                }
                linenumber++;
            }
            br.close();

            FileWriter fw = new FileWriter(new File(filePath));
            //Write entire string buffer into the file
            fw.write(sb.toString());
            fw.close();
        }
        catch (IOException e) {
            System.out.println("Error editing file: " + e.getMessage());
        }
    }

    public static void deleteCurrentDataInFile(int taskNumber, int total) {
        try {
            String currentDir = System.getProperty("user.dir");
            String pathToFile = currentDir + File.separator + "data" + File.separator + "duke.txt";
            BufferedReader br = new BufferedReader(new FileReader(pathToFile));

            //String buffer to store contents of the file
            StringBuffer sb = new StringBuffer("");

            //Keep track of the line number
            int linenumber = 1;
            String line;

            while ((line = br.readLine()) != null) {
                //Store each valid line in the string buffer
                if (linenumber != taskNumber && linenumber == total + 1) {
                    sb.append(line);
                } else if (linenumber != taskNumber) {
                    sb.append(line + "\n");
                }
                linenumber++;
            }
            br.close();

            FileWriter fw = new FileWriter(new File(pathToFile));
            //Write entire string buffer into the file
            fw.write(sb.toString());
            fw.close();
        }
        catch (Exception ex) {
            System.out.println("Error deleting line: " + ex.getMessage());
        }
    }
}
