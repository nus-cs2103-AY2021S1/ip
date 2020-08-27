import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    protected String filepath;
    
    Storage(String filepath) {
        this.filepath = filepath;
    }
    
    public void updateList(ArrayList<Task> list) throws DukeException {
        try {
            FileWriter fw1 = new FileWriter(filepath);
            FileWriter fw2 = new FileWriter(filepath, true);
            for (int i = 1; i <= list.size(); i++) {
                Task thisTask = list.get(i - 1);
                if (i == 1) {
                    fw1.write("     " + i + "." + thisTask.toString() + System.lineSeparator());
                } else {
                    fw2.write("     " + i + "." + thisTask.toString() + System.lineSeparator());
                }
            }
            fw1.close();
            fw2.close();
        } catch (IOException ex) {
            throw new DukeException(ex.getMessage());
        }
    }
    
    public ArrayList<Task> load() {
        ArrayList<Task> list = new ArrayList<>();
        String thisLine;
        char identifier;
        boolean status;
        
        try {
            if (!(new File(filepath).exists())) {
                throw new DukeException("There is not such file.");
            } else {
                File f = new File(filepath);
                try {
                    Scanner s = new Scanner(f);
                    while (s.hasNext()) {
                        thisLine = s.nextLine();
                        identifier = thisLine.charAt(8);
                        status = thisLine.charAt(11) == '\u2713';
                        if (identifier == 'D') {
                            Deadline thisDeadline = new Deadline(thisLine.substring(14, thisLine.indexOf('(') - 2),
                                    status, thisLine.substring((thisLine.indexOf(':') + 2), thisLine.indexOf(')')));
                            thisDeadline.updateDateTime();
                            list.add(thisDeadline);
                        } else if (identifier == 'E') {
                            Event thisEvent = new Event(thisLine.substring(14, thisLine.indexOf('(') - 2),
                                    status, thisLine.substring((thisLine.indexOf(':') + 2), thisLine.indexOf(')')));
                            list.add(thisEvent);
                        } else {
                            Todo thisTodo = new Todo(thisLine.substring(14), status);
                            list.add(thisTodo);
                        }
                    }
                } catch (FileNotFoundException ex) {
                    throw new DukeException(ex.getMessage());
                }
            }
        } catch (DukeException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
}
