import java.util.ArrayList;
import java.nio.file.Path;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Storage {
    private static int globalIndex = 1;
    
    public Storage(Path filePath) {
        try {
            if (!java.nio.file.Files.exists(filePath)) { 
                File newDir = new File("storage" + File.separator + "data.txt");
                newDir.getParentFile().mkdirs();
                newDir.createNewFile();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public ArrayList<Task> load() {
        ArrayList<Task> storedTasks = new ArrayList<>();
        try {
            String filePath = "storage" + File.separator + "data.txt";
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                String task[] = currentLine.split("\\|");
                if (task.length != 0 && !task[0].equals("")) {
                    switch(task[1]) {
                        case "T":
                            ToDo todo = new ToDo(task[3]);
                            if (Integer.parseInt(task[2]) == 1) todo.markAsDone();
                            storedTasks.add(todo);
                            break;
                        case "D":
                            Deadline deadline = new Deadline(task[3]);
                            if (Integer.parseInt(task[2]) == 1) deadline.markAsDone();
                            storedTasks.add(deadline);
                            break;
                        case "E":
                            Event event = new Event(task[3]);
                            if (Integer.parseInt(task[2]) == 1) event.markAsDone();
                            storedTasks.add(event);
                            break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return storedTasks;
    }
    
    public void writeToFile(Task t, String input) {
        String splitTask[] = input.split(" ", 2);
        if (t != null) {
            String type = "";
            int done = 0;
            try {
                if (t instanceof ToDo) {
                    type = "T";
                } else if (t instanceof Deadline) {
                    type = "D";
                } else if (t instanceof Event) {
                    type = "E";
                }
                if (t.isDone()) done = 1;

                FileWriter fw = new FileWriter("storage" + File.separator + "data.txt", true);
                fw.write(globalIndex + "|" + type + "|" + done + "|" + splitTask[1] + "\n");
                globalIndex++;
                fw.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        
    }

    public void removeFromFile(int taskIndex) {
        try {
            if (taskIndex != -1) {
                int index = 1;
                String tempFilePath = "storage" + File.separator + "temp.txt";
                String oldFilePath = "storage" + File.separator + "data.txt";
                File tempFile = new File(tempFilePath);
                FileReader fr = new FileReader(oldFilePath);
                BufferedReader br = new BufferedReader(fr);
                String currentLine;
                String[] task;
                FileWriter fw = new FileWriter(tempFile, true);

                while ((currentLine = br.readLine()) != null) {
                    task = currentLine.split("\\|");
                    if (Integer.parseInt(task[0]) != taskIndex) {
                        fw.write(index + "|" + task[1] + "|" + task[2] + "|" + task[3] + "\n");
                        index++;
                    }
                }
                globalIndex = index;
                fw.close();
                fr.close();
                br.close();
                Files.copy(Paths.get(tempFilePath), Paths.get(oldFilePath), StandardCopyOption.REPLACE_EXISTING);
                Files.delete(Paths.get(tempFilePath));
            }
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    public void overwriteInFile(int taskIndex) {
        try {
            if (taskIndex != -1) {
                String tempFilePath = "storage" + File.separator + "temp.txt";
                String oldFilePath = "storage" + File.separator + "data.txt";
                File tempFile = new File(tempFilePath);
                FileReader fr = new FileReader(oldFilePath);
                BufferedReader br = new BufferedReader(fr);
                String currentLine;
                String[] task;
                FileWriter fw = new FileWriter(tempFile, true);

                while ((currentLine = br.readLine()) != null) {
                    task = currentLine.split("\\|");
                    if (Integer.parseInt(task[0]) != taskIndex) {
                        fw.write(currentLine + "\n");
                    } else {
                        fw.write(taskIndex + "|" + task[1] + "|" + 1 + "|" + task[3] + "\n");
                    }
                }
                fw.close();
                fr.close();
                br.close();
                Files.copy(Paths.get(tempFilePath), Paths.get(oldFilePath), StandardCopyOption.REPLACE_EXISTING);
                Files.delete(Paths.get(tempFilePath));
            }
        } catch (Exception err) {
            System.out.println(err);
        }
    }
    
}
