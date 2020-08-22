import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bot {
    String delim;
    ArrayList<Task> activityList;

    String welcomeMessage = "Hello! I'm Duke\n What can I do for you?";
    String goodbyeMessage = "Bye! Message me anytime!";

    public Bot(String delim) {
        this.delim = delim;
        this.activityList = new ArrayList<>();
    }
    public void reply(String content) {
        System.out.println(this.delim);
        System.out.println(content);
        System.out.println(this.delim);
        System.out.println();
    }
    public void initialise() {

    }
    public void welcomeMessage() {
        reply(welcomeMessage);
    }
    public void goodByeMessage() {
        reply(goodbyeMessage);
    }

    public void addTodo(String activity) {
        Task todoTask = new TodoTask(activity);
        activityList.add(todoTask);
        activityReply(todoTask);
    }

    public void addDeadline(String activity, String deadline) {
        Task deadlineTask = new DeadlineTask(activity, deadline);
        activityList.add(deadlineTask);
        activityReply(deadlineTask);
    }

    public void addEvent(String activity, String time) {
        Task event = new EventTask(activity,time);
        activityList.add(event);
        activityReply(event);
    }

    public void parseFile(File file){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            //T | 1 | read book

            while ((line = reader.readLine()) != null) {
                Task task = readLine(line);
                if (task != null) {
                    this.activityList.add(task);
                }

            }
        } catch(FileNotFoundException e) {
            System.out.println("File not found??");
        } catch (IOException e2) {
            System.out.println("IO exception");
        }
    }

    public Task readLine(String line) {
        Pattern todoPattern = Pattern.compile("T \\| ([01]) \\| (.+)");
        Pattern deadlinePattern = Pattern.compile("D \\| ([01]) \\| (.+?) \\| (.+)");
        Pattern eventPattern = Pattern.compile("E \\| ([01]) \\| (.+?) \\| (.+)");
        Matcher todoMatcher = todoPattern.matcher(line);
        Matcher deadlineMatcher = deadlinePattern.matcher(line);
        Matcher eventMatcher = eventPattern.matcher(line);

        if (todoMatcher.find()) {
            return new TodoTask(todoMatcher.group(2), Integer.parseInt(todoMatcher.group(1)));
        } else if (deadlineMatcher.find()){
            return new DeadlineTask(deadlineMatcher.group(2), Integer.parseInt(deadlineMatcher.group(1)), deadlineMatcher.group(3));
        } else if (eventMatcher.find()) {
            return new EventTask(eventMatcher.group(2), Integer.parseInt(eventMatcher.group(1)), eventMatcher.group(3));
        } else {
            // invalid input
            return null;
        }
    }

    public void activityReply(Task task) {
        System.out.println(this.delim);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println(replyTaskNum());
        System.out.println(this.delim);

    }

    public String replyTaskNum() {
       return String.format("You have %s tasks currently, work on them soon!", activityList.size());
    }
    public void displayActivities() {
        System.out.println(this.delim);
        System.out.println("Here are your current tasks:");
        for (int i = 0; i < activityList.size(); i++) {
            System.out.println(i + 1 + "." + activityList.get(i));
        }
        System.out.println(this.delim);
    }

    public void completeTask(int taskNum) {
        Task task = activityList.get(taskNum - 1);
        task.MarkAsDone();
        reply("Nice! I've marked this task as done:\n " + task);
    }

    public void deleteTask(int taskNum) {
        Task task = activityList.get(taskNum - 1);
        this.activityList.remove(taskNum - 1);
        reply(String.format("Noted. I've removed this task:\n%s\n%s", task, replyTaskNum()));
    }

    public void saveTasks(File file) {
         try {
             BufferedWriter writer = new BufferedWriter(new FileWriter(file));
             for (Task task: this.activityList) {
                 if (task instanceof TodoTask) {
                     writer.write(String.format("T | %s | %s\n", task.getHasCompleted(), task.getName()));
                 } else if (task instanceof DeadlineTask) {
                     DeadlineTask deadlineTask = (DeadlineTask) task;
                     writer.write(String.format("D | %s | %s | %s\n", deadlineTask.getHasCompleted(), deadlineTask.getName(), deadlineTask.getDeadline()));
                 } else if (task instanceof EventTask) {
                     EventTask eventTask = (EventTask) task;
                     writer.write(String.format("E | %s | %s | %s\n", eventTask.getHasCompleted(), eventTask.getName(), eventTask.getTime()));
                 }
             }
             writer.close();
         } catch(IOException e) {
             System.out.println(e.getMessage());
         }
    }
}
