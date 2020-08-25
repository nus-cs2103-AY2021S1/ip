import java.util.ArrayList;

public class TaskList {

    public ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> savedData) {
        this.list = savedData;
    }

    public Task addItem(String input) throws DukeException {

        String arr[] = input.split(" ", 2);
        Task curr = new Task("");
        if (arr.length == 1) {
            throw new DukeException("The description of a " + arr[0] + " cannot be empty!");
        } else if (arr[0].equals("todo")) {
            curr = new ToDo(arr[1]);
            list.add(curr);
        } else if (arr[0].equals("deadline")) {
            String info[] = arr[1].split("/by ", 2);
            if (info.length == 1) {
                throw new DukeException("Deadline not provided!");
            } else {
                curr = new Deadline(info[0], Duke.dateParser(info[1]));
                list.add(curr);
            }
        } else if (arr[0].equals("event")) {
            String info[] = arr[1].split("/at ", 2);
            if (info.length == 1) {
                throw new DukeException("Time not provided!");
            } else {
                String[] t = info[1].split(" ", 2);
                curr = new Event(info[0], Duke.dateParser(t[0]), Duke.timeParser(t[1]));
                list.add(curr);
            }
        }
        return curr;
    }

    public Task deleteItem(String input) throws DukeException{
        String info[] = input.split(" ", 2);
        Task toBeDeleted = new Task("");
        if (info.length == 1) {
            throw new DukeException("Which item???");
        } else {
            try {
                int index = Integer.parseInt(info[1]);
                if (index > list.size() || index <= 0) {
                    throw new DukeException("Excuse Moi???");
                } else {
                    toBeDeleted = list.get(index - 1);
                    list.remove(index - 1);
                    return toBeDeleted;
                }
            } catch (NumberFormatException ex1){
                throw new DukeException("Excuse Meee? number pls.");
            }
        }
    }

    public Task doneItem(String input) throws DukeException {
        String info[] = input.split(" ", 2);
        Task toBeRet = new Task("");
        if (info.length == 1) {
            throw new DukeException("Which item???");
        } else {
            int index = Integer.parseInt(info[1]);
            if (index > list.size() || index <= 0) {
                throw new DukeException("Excuse Moi???");
            } else {
                list.get(index-1).markAsDone();
                toBeRet = list.get(index-1);
            }
        }
        return toBeRet;
    }

    public int getListSize() {
        return this.list.size();
    }

    public ArrayList<Task> getList() {
        return list;
    }

}
