import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {

    TaskList taskList;
    Parser parser;
    Path storageFilePath;

    public Storage(TaskList taskList, Parser parser) throws IOException, DukeException {
        this.taskList = taskList;
        this.parser = parser;
        storageFilePath = Paths.get(".", "data", "test.txt");
        try {
            // Create directory if needed
            Path parentPath = storageFilePath.getParent();
            Files.createDirectories(parentPath);

            if (!Files.exists(storageFilePath)) {
                Files.createFile(storageFilePath);
            }

        } catch (IOException e) {
            System.out.println("unable to read file " + e.getMessage());
        }
        LoadFile();
    }

    public void LoadFile() throws IOException, DukeException {


        BufferedReader bf = new BufferedReader(new FileReader(storageFilePath.toString()));
        String task = bf.readLine();
        String[] inputs;
        while(task != null){
            inputs = task.split(" \\| ",4);
            String taskType = inputs[0];
            Task newTask;
//            for(String s : inputs){
//                System.out.print(s);
//            }
//            System.out.println(inputs.length);
            switch( taskType ){
                case "T" : {
                    newTask = new Todo(inputs[2]);
                    break;
                }

                case "D" : {
                    newTask = Deadline.create(inputs[2],inputs[3]);
                    break;
                }

                case "E" : {
                    newTask = Event.create(inputs[2],inputs[3]);
                    break;
                }

                default : {
                    throw new DukeException("smlj??????");
                }
            }
            if(inputs[1].equals("1")){
                newTask.complete();
            }
            taskList.AddTask(newTask,false);
            task = bf.readLine();
        }

    }

    public void saveFile() throws IOException {
        FileWriter fw = new FileWriter(storageFilePath.toString());
            for (int i = 0; i < taskList.taskList.size(); i++) {
                fw.write(taskList.taskList.get(i).safeFileFormat());
            }
        fw.close();
    }

}
