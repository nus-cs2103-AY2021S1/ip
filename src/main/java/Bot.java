import java.util.ArrayList;

public class Bot {
    String delim;
    ArrayList<Task> activityList;

    String welcomeMessage = "Hello! I'm Duke\n What can I do for you? ";
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
    public void welcomeMessage() {
        reply(welcomeMessage);
    }
    public void goodByeMessage() {
        reply(goodbyeMessage);
    }
    public void addActivity(String activity) {
        activityList.add(new Task(activity));
        reply("added: " + activity);
    }

    public void displayActivities() {
        System.out.println(this.delim);
        System.out.println("Here are the tasks in your list:");
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
}
