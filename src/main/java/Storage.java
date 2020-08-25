import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    File file;
    boolean added = false;
    Storage() throws IOException {
        String dir = System.getProperty("user.dir") + "/data";
        File path = new File(dir);
        if(!path.exists()) {
            path.mkdir();
        }
        file = new File(path + "/duke.txt");
        boolean result = file.createNewFile();
        if(result) {
            System.out.println("file created " + file.getCanonicalPath());
        } else {
            System.out.println("file exists at: " + file.getCanonicalPath());
        }
    }

    void reset() {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file.getAbsolutePath()))) {
            bufferedWriter.write("");
            added = false;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    ArrayList<Task> load() {
        System.out.println("reading... ");
        ArrayList<Task> list = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file.getAbsolutePath()))) {
            String line = bufferedReader.readLine();
            while(line != null) {
                String[] parts = line.split("</>");
                Task task;
                if (parts[0].equals("TODO") && parts.length == 3) {
                    task = new Todo(parts[2]);
                    if(parts[1].equals("true")) {
                        task.setCompleted();
                    }
                    list.add(task);
                } else if (parts[0].equals("DEADLINE") && parts.length == 4){
                    task = new Deadline(parts[2], parts[3]);
                    if(parts[1].equals("true")) {
                        task.setCompleted();
                    }
                    list.add(task);
                } else if (parts[0].equals("EVENT") && parts.length == 4) {
                    task = new Event(parts[2], parts[3]);
                    if(parts[1].equals("true")) {
                        task.setCompleted();
                    }
                    list.add(task);
                }
                line = bufferedReader.readLine();
            }
//            System.out.println(list);
            added = true;
        } catch (FileNotFoundException e) {
            // Exception handling
            System.out.println(e.getMessage());
        } catch (IOException e) {
            // Exception handling
            System.out.println(e.getMessage());
        }
        return list;
    }

    void readAll(List<Task> list) {
        if (added) {
            return;
        }
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file.getAbsolutePath()))) {
            String line = bufferedReader.readLine();
            while(line != null) {
                String[] parts = line.split("</>");
                Task task;
                if (parts[0].equals("TODO") && parts.length == 3) {
                    task = new Todo(parts[2]);
                    if(parts[1].equals("true")) {
                        task.setCompleted();
                    }
                    list.add(task);
                } else if (parts[0].equals("DEADLINE") && parts.length == 4){
                    task = new Deadline(parts[2], parts[3]);
                    if(parts[1].equals("true")) {
                        task.setCompleted();
                    }
                    list.add(task);
                } else if (parts[0].equals("EVENT") && parts.length == 4) {
                    task = new Event(parts[2], parts[3]);
                    if(parts[1].equals("true")) {
                        task.setCompleted();
                    }
                    list.add(task);
                }
                line = bufferedReader.readLine();
            }
//            System.out.println(list);
            added = true;
        } catch (FileNotFoundException e) {
            // Exception handling
            System.out.println(e.getMessage());
        } catch (IOException e) {
            // Exception handling
            System.out.println(e.getMessage());
        }
    }

    void addTask(Task task) {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file.getAbsolutePath(), true))) {
            String separator = "</>";
            Task.Type type = task.getType();
            String fileContent = type.toString() + separator + task.getCompleted() + separator + task.getName();
            if (type == Task.Type.DEADLINE) {
                fileContent += separator + ((Deadline) task).getDeadline();
            } else if (type == Task.Type.EVENT) {
                fileContent += separator + ((Event) task).getTime();
            }
//            System.out.println(type);
            bufferedWriter.write(fileContent);
            bufferedWriter.newLine();
        } catch (IOException e) {
            // Exception handling
            System.out.println(e.getMessage());
        }
    }

    void addAll(List<Task> list) {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file.getAbsolutePath(), true))) {
            for(Task t : list) {
                addTask(t);
            }
//            System.out.println(list);
        } catch (FileNotFoundException e) {
            // Exception handling
            System.out.println(e.getMessage());
        } catch (IOException e) {
            // Exception handling
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {

    }
}
