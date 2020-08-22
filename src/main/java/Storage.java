import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    public File f;
    private Scanner lineReader;
    private FileWriter fw;

    public Storage(File f){
        this.f = f;
        try {
            f.createNewFile();
            this.lineReader = new Scanner(f);

        } catch (FileNotFoundException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> loadFile(){
        ArrayList<Task> shelf = new ArrayList<>();
        while(lineReader.hasNextLine()) {
            String data = lineReader.nextLine();
            shelf.add(taskCreator(data));
        }
        return shelf;
    }

    public void updateFile(ArrayList<Task> shelf) throws IOException {
        this.fw = new FileWriter(f.getAbsolutePath());
        for(int i = 0; i < shelf.size(); i++) {
            fw.write(shelf.get(i).toString());
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    private Task taskCreator(String task){
        switch (task.charAt(1)) {
            case 'T':
                return new ToDo(task.toString(), true);
            case 'D':
                return new Deadline(task.toString(), true);
            default:
                return new EventTask(task.toString(), true);
        }
    }




}
