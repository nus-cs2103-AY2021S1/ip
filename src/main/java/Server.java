import java.util.ArrayList;

public class Server {
    ArrayList<Task> taskList = new ArrayList<>();

    public void add(String description) {
        Task newTask = new Task(description);
        this.taskList.add(newTask);
        System.out.print(Duke.formatOut("Added: " + description + "\n"));
    }

    public void list() {
        StringBuilder builder = new StringBuilder();

        for(Task task:taskList) {
            builder.append(taskList.indexOf(task) + 1).append(". ").append(task.toString()).append("\n").append("\t");
        }

        System.out.print(Duke.formatOut(builder.toString()));
    }

    public void perform(String input) {
        String[] s = input.split(" ");
        if (s[0].equals("list")) {
            this.list();
        } else if (s[0].equals("done")) {
            int index = Integer.parseInt(s[1]) - 1;
            this.taskList.get(index).markAsDone();
        } else {
            this.add(input);
        }
    }

    public String formatOut(String s) {
        return String.format("  %s\n    %s\n  %s\n",hor_line(),s,hor_line());
    }

    private String hor_line() {
        return "-------------------------------------";
    }
}
