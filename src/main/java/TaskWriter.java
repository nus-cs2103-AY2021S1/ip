import java.io.*;
import java.util.Scanner;


public class TaskWriter {
    String filepath;

    TaskWriter(String filepath) {
        this.filepath = filepath;
    }

    public void appendTask(Command command, String info) throws IOException {
        try {
            FileWriter fw = new FileWriter(this.filepath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            switch(command) {
                case TODO:
                    pw.println("T | 0 | " + info);
                    break;
                case DEADLINE:
                    String[] dInfo = info.split(" /by ");
                    Validator.info(command, dInfo.length, true);
                    String deadlineEvent = dInfo[0];
                    String deadlineTime = dInfo[1];
                    pw.println("D | 0 | " + deadlineEvent + " | " + deadlineTime);
                    break;
                case EVENT:
                    String[] eInfo = info.split(" /at ");
                    Validator.info(command, eInfo.length, true);
                    String eventEvent = eInfo[0];
                    String eventTime = eInfo[1];
                    pw.println("E | 0 | " + eventEvent + " | " + eventTime);
                    break;
                default:
                    break;
            }

            pw.flush();
            pw.close();
        } catch (IOException ex) {
            throw new IOException("Something wwnt wrong. Mug fail to add the Task :_:");
        }
    }

    public void deleteTask(int taskId) {
        String tempFile = "temp.txt";
        File oldFile = new File(this.filepath);
        File newFile = new File(tempFile);
        int taskTrack = 1;
        String newLine;

        try {
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            Scanner sc = new Scanner(new File(this.filepath));
            sc.useDelimiter("[\n]");

            while(sc.hasNext()) {
                newLine = sc.next();
                if(taskTrack != taskId) {
                    pw.println(newLine);
                }
                taskTrack++;
            }
            sc.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File renameFile = new File(this.filepath);
            newFile.renameTo(renameFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
