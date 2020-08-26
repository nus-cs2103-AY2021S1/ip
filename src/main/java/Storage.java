import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Storage {

    private String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    public void writeToFile(TaskList tasks) throws IOException {
        String output = "";
        if (tasks.getNumOfTasks() == 0) { // user has not added any task
            output = "Nothing has been added to the list yet!";
        } else {
            output = tasks.displayTasks();
        }
        File file = new File(this.filePath);
        File directory = new File(file.getParent());
        if (!directory.exists()) { // directory does not exist
            directory.mkdir();
        }
        if (!file.exists()) { // file does not exist
            file.createNewFile();
        }
        FileWriter fw = new FileWriter("./data/duke.txt");
        fw.write(output);
        fw.close();
    }

    public LocalDateTime getDate(String taskDate) throws ParseException {
        String[] splitDate = taskDate.split(" ");
        int date = Integer.parseInt(splitDate[0]);

        // convert month in words to month in int
        String monthInWords = splitDate[1];
        Date checkMonth = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(monthInWords);
        Calendar cal = Calendar.getInstance();
        cal.setTime(checkMonth);
        int month = cal.get(Calendar.MONTH) + 1;

        int year = Integer.parseInt(splitDate[2]);

        // get hour and minute from time
        String time = splitDate[3];
        int hour = Integer.parseInt(time.split(":")[0]);
        if (splitDate[4].equals("PM")) {
            hour += 12;
        }
        int min = Integer.parseInt(time.split(":")[1].substring(0, 2));

        return LocalDateTime.of(year, month, date, hour, min);
    }

    public ArrayList<Task> load() throws DukeException, IOException, ParseException {
        File file = new File(this.filePath);
        if (!file.exists()) {
            throw new DukeException("There are no saved tasks.");
        }
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        return getPastTasks(br);
    }

    public ArrayList<Task> getPastTasks(BufferedReader br) throws IOException, ParseException, DukeException {
        ArrayList<Task> pastTasks = new ArrayList<>();
        String line = br.readLine();
        while (line != null) {
            Pattern p = Pattern.compile("\\[(.*?)\\]");
            Matcher m = p.matcher(line);
            m.find();
            String taskType = m.group(1);
            m.find();
            String completionState = m.group(1);
            int pos = m.end();

            // check for completion
            boolean isCompleted;
            if (completionState.equals("done")) {
                isCompleted = true;
            } else {
                isCompleted = false;
            }

            Task currTask;
            if (taskType.equals("T")) { // Todo
                String taskName = line.substring(pos + 1);
                currTask = new Todo(taskName, isCompleted);
            } else {
                String taskNameAndDate = line.substring(pos + 1);
                String taskName1 = taskNameAndDate.split("\\(")[0];
                String taskName = taskName1.substring(0, taskName1.length() - 1);

                String taskDate = taskNameAndDate.split("\\)")[0].split("\\(")[1].substring(4);
                LocalDateTime taskTime = getDate(taskDate);

                if (taskType.equals("D")) { // Deadline
                    currTask = new Deadline(taskName, isCompleted, taskTime);
                } else if (taskType.equals("E")) { // Event
                    currTask = new Event(taskName, isCompleted, taskTime);
                } else {
                    throw new DukeException("Task type is not recognised.");
                }
            }
            pastTasks.add(currTask);
            line = br.readLine();
        }
        return pastTasks;
    }
}
