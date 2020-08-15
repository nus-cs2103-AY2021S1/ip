import java.util.ArrayList;

public class Processor {

    public Processor() {}

    public void processorList(ArrayList<Task> arraylst) {
        System.out.println("_________________________________________");
        for (int i = 0; i < arraylst.size(); i++) {
            int index = i+1;
            String checked = arraylst.get(i).getDone() ? "O" : "X";
            System.out.println(index + ".[" + checked + "] " + arraylst.get(i).getTask());
        }
        System.out.println("_________________________________________");
    }

    public void processorDone(ArrayList<Task> arraylst, int index) {
        int i = index - 1;
        arraylst.get(i).setDone();
        System.out.println("_________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(index + ".[O] " + arraylst.get(i).getTask());
        System.out.println("_________________________________________");
    }
}
