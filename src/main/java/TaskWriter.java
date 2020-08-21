import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;


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

}
