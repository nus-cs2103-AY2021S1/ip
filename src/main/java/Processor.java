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

    public void processorAdd(String cmd, ArrayList<Task> arraylst) {
        String[] stringarr = cmd.split(" ", 2);
        if (stringarr[0].equals("todo")) {
            Todo todo = new Todo(stringarr[1]);
            arraylst.add(todo);
            System.out.println("_________________________________________");
            System.out.println("Got it. I've added this task:");
            System.out.println("[T][X] " + stringarr[1]);
            System.out.println("New you have " + arraylst.size() + " tasks in the list.");
            System.out.println("_________________________________________");
        } else if (stringarr[0].equals("deadline")) {
            String[] secondarr = stringarr[1].split("/by", 2);
            Deadline deadline = new Deadline(secondarr[0], secondarr[1]);
            System.out.println("_________________________________________");
            System.out.println("Got it. I've added this task:");
            System.out.println("[D][X] " + secondarr[0] + "(by:" + secondarr[1] + ")");
            System.out.println("New you have " + arraylst.size() + " tasks in the list.");
            System.out.println("_________________________________________");
        } else if (stringarr[0].equals("event")) {
            String[] secondarr = stringarr[1].split("/at", 2);
            Event event = new Event(secondarr[0], secondarr[1]);
            System.out.println("_________________________________________");
            System.out.println("Got it. I've added this task:");
            System.out.println("[E][X] " + secondarr[0] + "(at:" + secondarr[1] + ")");
            System.out.println("New you have " + arraylst.size() + " tasks in the list.");
            System.out.println("_________________________________________");
        } else {
            System.out.println("Place holder for exceptions");
            return;
        }
    }
}
