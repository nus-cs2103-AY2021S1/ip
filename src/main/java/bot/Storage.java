package bot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Processes the text file and returns it as an ArrayList of Task.
     * @return ArrayList of Tasks
     * @throws IOException
     */
    public ArrayList<Task> loadFileContents() throws IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        File f = new File(filePath);
        File dir = new File(f.toPath().getParent().toString());
        if(!dir.exists()) {
            dir.mkdir();
        }
        if (!f.exists()) {
            String response = "Hey, you new user eh? I've got your profile created. No worries.";
            System.out.println(responseWrapper(response));
            f.createNewFile();
        }
        String content = Files.readString(Paths.get(filePath), StandardCharsets.US_ASCII);
        String[] items = content.split("\n");
        for (String item : items) {
            if(item.length() != 0) {
                taskList.add(lineToObj(item));
            }
        }
        return taskList;
    }

    /**
     * Updates the user's test file with the taskList.
     * @param taskList
     * @throws IOException
     */

    public void saveUserData(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : taskList.getList()) {
            fw.write(task.toFileFormat());
        }
        fw.close();
    }

    private Task lineToObj(String line) {
        String[] words = line.split(" \\| ");
        char firstChar = line.charAt(0);
        switch (firstChar) {
            case 'D':
                int len = words[3].length();
                return new Deadline(words[2], words[3].toString().substring(0,len - 1), words[1].equals("1"));
            case 'E':
                int len2 = words[3].length();
                return new Event(words[2], words[3].toString().substring(0, len2 - 1), words[1].equals("1"));
            default:
                return new Todo(words[2], words[1].equals("1"));
        }
    }

    private String responseWrapper(String str) {
        final String TEXT_LINE = "________________________________________________________________";
        return TEXT_LINE + "\n    " + str + "\n" + TEXT_LINE;
    }
}
