import java.util.LinkedList;

public class Server {
    LinkedList<String> taskList = new LinkedList<>();

    public void add(String task) {
        this.taskList.add(task);
        System.out.print(formatOut("Added: " + task + "\n"));
    }

    public void list() {
        StringBuilder builder = new StringBuilder();
        int index = 1;

        for(String s:taskList) {
            builder.append(index).append(". ").append(s).append("\n").append("\t");
            index++;
        }

        System.out.print(formatOut(builder.toString()));
    }

    public String formatOut(String s) {
        return String.format("  %s\n    %s\n  %s\n",hor_line(),s,hor_line());
    }

    private String hor_line() {
        return "-------------------------------------";
    }
}
