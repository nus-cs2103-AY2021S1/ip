import java.util.ArrayList;

public class Server {
    ArrayList<Task> taskList = new ArrayList<>();

    public void add(Task newTask) {
        StringBuilder out = new StringBuilder();
        this.taskList.add(newTask);
        out.append("Got it. I've added this task: ").append("\n\t\t")
                .append(newTask.toString()).append("\n\t")
                .append(String.format("Now you have %d tasks in the list.\n",taskList.size()));
        System.out.print(Duke.formatOut(out.toString()));
    }

    public void list() {
        StringBuilder builder = new StringBuilder();
        builder.append("Here are the tasks in your list: \n\t");

        for(Task task:taskList) {
            builder.append(taskList.indexOf(task) + 1).append(". ").append(task.toString()).append("\n").append("\t");
        }

        System.out.print(Duke.formatOut(builder.toString()));
    }

    public void perform(String input) {
        String[] s = input.split(" ",2);
        if (s[0].equals("list")) {
            this.list();
        } else if (s[0].equals("done")) {
            int index = Integer.parseInt(s[1]) - 1;
            this.taskList.get(index).markAsDone();
        } else {
            switch (s[0]) {
                case "todo":
                    this.add(new Todo(s[1]));
                    break;
                case "deadline": {
                    String[] set = s[1].split(" /by ");
                    this.add(new Deadline(set[0], set[1]));
                    break;
                }
                case "event": {
                    String[] set = s[1].split(" /at ");
                    this.add(new Event(set[0], set[1]));
                    break;
                }
                default:
                    this.add(new Task(input));
                    break;
            }
        }
    }

    public String formatOut(String s) {
        return String.format("  %s\n    %s\n  %s\n",hor_line(),s,hor_line());
    }

    private String hor_line() {
        return "-------------------------------------";
    }
}
