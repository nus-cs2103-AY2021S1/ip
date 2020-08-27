package Duke;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
private Path path;

    public Storage() throws IOException {
        String home = System.getProperty("user.home");
        String fp = "/save.txt";
        path = Paths.get(home, fp);
        if (Files.notExists(path)) {
            File newDir = new File(path.toString());
            newDir.createNewFile();
        }
    }

    public void saveFile(List<Task> list) throws IOException {
        FileWriter fw = new FileWriter(String.valueOf(path));
        String contents = "";
        for (Task x : list) {

            if (x.getClass().getSimpleName() == "Duke.ToDo") {
                String temp = "Duke.ToDo\n" + x.completed + "\n" + x.name + "\n\n";
                contents += temp;
            } else if (x.getClass().getSimpleName() == "Duke.Deadlines") {
                String temp = "Duke.Deadlines\n" + x.completed + "\n" + x.name + "\n" + x.time + "\n\n";
                contents += temp;
            } else {
                String temp = "Duke.Events\n" + x.completed + "\n" +x.name + "\n" + x.time + "\n\n";
                contents += temp;
            }
        }
        fw.write(contents);
        fw.close();
    }

    public List<Task> loadFile() throws IOException {
        Scanner sc = new Scanner(path);
        List<Task> list = new ArrayList<>();
        while(sc.hasNextLine()) {
            String type = sc.nextLine();
            String done = sc.nextLine();
            String name = sc.nextLine();

            if (type.equals("Duke.ToDo")){
                sc.nextLine();
                Task temp = new ToDo(name);
                if (done == "true"){
                    temp.completedTask();
                }
                list.add(temp);
            } else if (type.equals("Duke.Deadlines")){
                String time = sc.nextLine();
                sc.nextLine();
                Task temp = new Deadlines(name,time);
                if (done == "true"){
                    temp.completedTask();
                }
                list.add(temp);
            } else {
                String time = sc.nextLine();
                sc.nextLine();
                Task temp = new Events(name,time);
                if (done == "true"){
                    temp.completedTask();
                }
                list.add(temp);
            }
        }
        return list;
    }


    }



