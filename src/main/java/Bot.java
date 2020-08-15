import java.util.ArrayList;

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
}
