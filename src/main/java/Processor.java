import java.util.ArrayList;

public class Processor {

    public Processor() {}

    public void processorList(ArrayList<Task> arraylst) {
        System.out.println("_________________________________________\n" + "Here are the tasks in your list:");
        for (int i = 0; i < arraylst.size(); i++) {
            int index = i+1;
            String checked = arraylst.get(i).getDone() ? "O" : "X";
            System.out.println(index + "." + arraylst.get(i).toString());
        }
        System.out.println("_________________________________________");
    }

    public void processorDone(ArrayList<Task> arraylst, int index) {
        int i = index - 1;
        arraylst.get(i).setDone();
        System.out.println("_________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(index + "." + arraylst.get(i).toString());
        System.out.println("_________________________________________");
    }

    public ArrayList<Task> processorAdd(String cmd, ArrayList<Task> arraylst) {
        String[] stringarr = cmd.split(" ", 2);
        if (stringarr[0].equals("todo")) {
            Todo todo = new Todo(stringarr[1]);
            arraylst.add(todo);
        } else if (stringarr[0].equals("deadline")) {
            String[] secondarr = stringarr[1].split("/by", 2);
            Deadline deadline = new Deadline(secondarr[0], secondarr[1]);
            arraylst.add(deadline);
        } else if (stringarr[0].equals("event")) {
            String[] secondarr = stringarr[1].split("/at", 2);
            Event event = new Event(secondarr[0], secondarr[1]);
            arraylst.add(event);
        } else {
            System.out.println("Place holder for exceptions");
            return arraylst;
        }
        int lastelem = arraylst.size() - 1;
        Task latesttask = arraylst.get(lastelem);
        System.out.println("_________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(latesttask.toString());
        System.out.println("Now you have " + arraylst.size() + " tasks in the list.");
        System.out.println("_________________________________________");
        return arraylst;
    }
}
