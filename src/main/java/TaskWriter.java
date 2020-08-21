import java.io.*;
import java.util.Scanner;


public class TaskWriter {
    String filepath;

    TaskWriter(String filepath) {
        this.filepath = filepath;
    }

    public String appendTask(Command command, String info) throws IOException {
        try {
            FileWriter fw = new FileWriter(this.filepath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            Task newTask = null;

            switch(command) {
                case TODO:
                    pw.println("TODO|0|" + info);
                    newTask = new Todo(info);
                    break;
                case DEADLINE:
                    String[] dInfo = info.split(" /by ");
                    Validator.info(command, dInfo.length, true);
                    String deadlineEvent = dInfo[0];
                    String deadlineTime = dInfo[1];
                    pw.println("DEADLINE|0|" + deadlineEvent + "|" + deadlineTime);
                    newTask = new Deadline(deadlineEvent, deadlineTime);
                    break;
                case EVENT:
                    String[] eInfo = info.split(" /at ");
                    Validator.info(command, eInfo.length, true);
                    String eventEvent = eInfo[0];
                    String eventTime = eInfo[1];
                    pw.println("EVENT|0|" + eventEvent + "|" + eventTime);
                    newTask = new Event(eventEvent, eventTime);
                    break;
                default:
                    break;
            }

            pw.flush();
            pw.close();

            return "Got it. MUG has added this task:\n"
                    + newTask;
        } catch (IOException ex) {
            throw new IOException("Something went wrong. Mug fail to add the Task :_:");
        }
    }

    public void deleteTask(int taskId) {
        String tempFile = "temp.txt";
        File oldFile = new File(this.filepath);
        File newFile = new File(tempFile);
        int taskTrack = 1;
        String line;

        try {
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            Scanner sc = new Scanner(new File(this.filepath));
            sc.useDelimiter("[\n]");

            while(sc.hasNext()) {
                line = sc.next();
                if(taskTrack != taskId) {
                    pw.println(line);
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

    public void doneTask(int taskId) {
        String tempFile = "temp.txt";
        File oldFile = new File(this.filepath);
        File newFile = new File(tempFile);
        int taskTrack = 1;
        String line;

        try {
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            Scanner sc = new Scanner(new File(this.filepath));
            sc.useDelimiter("[\n]");

            while(sc.hasNext()) {
                line = sc.next();
                if(taskTrack != taskId) {
                    pw.println(line);
                } else {
                    String[] newLine = line.split("[|]" , 3);
                    pw.println(newLine[0] + "|" + 1 + "|" + newLine[2] );
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

    public String readTask() throws FileNotFoundException {
        Scanner sc = new Scanner(new File(this.filepath));
        sc.useDelimiter("[\n]");
        String line;
        StringBuilder results = new StringBuilder();
        int taskId = 0;

        while (sc.hasNext()) {
            line = sc.next();
            String[] newLine = line.split("[|]");
            Command command = Validator.command(newLine[0]);
            boolean taskStatus = Integer.parseInt(newLine[1]) == 1;
            taskId++;

            if(taskId != 1) {
                results.append("\n");
            }
            results.append(taskId);
            results.append(". ");

            if (command == Command.TODO) {
                results.append(new Todo(newLine[2], taskStatus));
            } else if (command == Command.DEADLINE) {
                results.append(new Deadline(newLine[2], newLine[3], taskStatus));
            } else if (command == Command.EVENT) {
                results.append(new Event(newLine[2], newLine[3], taskStatus));
            }
        }

        if (taskId == 0) {
            return "MUG don't have any of your task \"_\"";
        } else {
            return results.toString();
        }
    }
}
