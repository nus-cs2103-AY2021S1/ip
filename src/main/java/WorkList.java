import java.util.ArrayList;

public class WorkList {
    private final ArrayList<Task> workList;

    WorkList() {
        this.workList = new ArrayList<>();
    }

    public String addWork(Task work) {
        this.workList.add(work);
        return "Got it. MUG has added this task:\n"
                + work
                + "\nNow you have "
                + this.workList.size()
                + " tasks in the list.";
    }

    public String readWork() {
        StringBuilder results = new StringBuilder();
        if (this.workListLen() <= 0) {
            return "MUG don't have any of your task \"_\"";
        }
        for(int i = 0; i < this.workListLen(); i ++) {
            results.append(i + 1);
            results.append(". ");
            results.append(this.workList.get(i));
            if(i + 1 != this.workList.size()){
                results.append("\n");
            }
        }
        return results.toString();
    }

    public String updateTaskStatus(int taskId) {
        Task tsk =  this.workList.get(taskId - 1);
        if(tsk.getIsDone()) {
            return "Mug had marked this task as done:\n"
                    + this.workList.get(taskId - 1);
        } else {
            this.workList.set(taskId - 1, tsk.markAsDone());
            return "Congratz! MUG has marked this task as done:\n"
                    + this.workList.get(taskId - 1);
        }
    }

    public int workListLen() {
        return this.workList.size();
    }

    public String deleteWork(int taskId) {
        Task tsk = this.workList.get(taskId - 1);
        this.workList.remove(taskId - 1);
        return "Noted. MUG has removed this task: \n"
                + tsk
                + "\nNow you have "
                + this.workList.size()
                + " tasks left in the list.";
    }
}
