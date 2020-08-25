import java.io.*;

public class EditiFile {
    private String path;

    public EditiFile(String path) {
        this.path = path;
    }

    public void deleteLine(int lineNum) {
        try {
            File fileToBeModified = new File(this.path);
            String text = "";
            BufferedReader reader = new BufferedReader(new FileReader(fileToBeModified));
            int currentLineNum = 1;
            String currentLine = reader.readLine();

            while (currentLine != null) {
                if (currentLineNum != lineNum) {
                    text += currentLine + "\n";
                }
                currentLineNum++;
                currentLine = reader.readLine();
            }

            FileWriter fileWriter = new FileWriter(fileToBeModified);
            fileWriter.write(text);
            reader.close();
            fileWriter.close();
        } catch (IOException e) {
            DukeException.ReadLineException();
        }
    }

    public void setTaskDone(int lineNum) {
        try {
            File fileToBeModified = new File(this.path);
            String text = "";
            BufferedReader reader = new BufferedReader(new FileReader(fileToBeModified));
            int currentLineNum = 1;
            String currentLine = reader.readLine();

            while (currentLine != null) {
                if (currentLineNum != lineNum) {
                    text += currentLine + "\n";
                } else {
                    String editedLine =
                            currentLine.substring(0, 4)
                                    + Status.FINISHED.toString()
                                    + currentLine.substring(5);
                    text += editedLine + "\n";
                }
                currentLineNum++;
                currentLine = reader.readLine();
            }

            FileWriter fileWriter = new FileWriter(fileToBeModified);
            fileWriter.write(text);
            reader.close();
            fileWriter.close();
        } catch (IOException e) {
            DukeException.ReadLineException();
        }
    }
}
