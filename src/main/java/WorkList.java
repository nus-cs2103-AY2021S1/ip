import java.util.ArrayList;

public class WorkList {
    private final ArrayList<Task> workList;

    WorkList() {
        this.workList = new ArrayList<>();
    }

    public void addWork(Task work) {
        this.workList.add(work);
    }

    public String readWork() {
        StringBuilder results = new StringBuilder("1. "
                +"["
                + this.workList.get(0).getStatusIcon()
                +"]"
                + this.workList.get(0).getDescription());
        for(int i = 1; i < this.workList.size(); i ++) {
            results.append("\n");
            results.append(i + 1);
            results.append(". ");
            results.append("[");
            results.append(this.workList.get(i).getStatusIcon());
            results.append("]");
            results.append(this.workList.get(i).getDescription());
        }
        return results.toString();

    }

    public String updateTaskStatus(int taskId) {
        Task tsk =  this.workList.get(taskId - 1);
        this.workList.set(taskId - 1, tsk.markAsDone());

        return  "Congratz! I've marked this task as done:\n"
                +"["
                + this.workList.get(taskId - 1).getStatusIcon()
                +"]"
                + " "
                + this.workList.get(taskId - 1).getDescription();
    }
}
