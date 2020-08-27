import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> list;

    TaskList(List<Task> list){
        this.list = list;
    }

    public Task add(String input) throws DukeException{

        if (input.split(" ")[0].equals("todo")) {
            String[] temp = input.split(" ",2);
            if (temp.length == 1) {
                throw new DukeException("Description of todo cannot be empty!");
            }
            Task newTask = new ToDo(temp[1]);
            list.add(newTask);
            return newTask;

        } else if (input.split(" ")[0].equals("deadline")) {
            String[] temp = input.split(" ",2);
            if (temp.length <= 1) {
                throw new DukeException("Description of deadline cannot be empty!");
            }
            String[] temp2 = temp[1].split("/by",2);
            if (temp2.length <= 1){
                throw new DukeException("You need to specify a time!");
            }
            Task newTask = new Deadlines(temp2[0], temp2[1]);
            list.add(newTask);
            return newTask;

        } else if (input.split(" ")[0].equals("event")) {
            String[] temp = input.split(" ",2);
            if (temp.length == 1) {
                throw new DukeException("Description of event cannot be empty!");
            }
            String[] temp2 = temp[1].split("/at",2);
            if (temp2.length <= 1){
                throw new DukeException("You need to specify a time!");
            }
            Task newTask = new Events(temp2[0], temp2[1]);
            list.add(newTask);
            return newTask;

        } else {
            throw new DukeException("Sorry I don't know what you mean by that");
        }

    }

    public Task delete(String input){
        String num = input.split(" ")[1];
        if (num.equals("all")) {
            deleteAll();
            return null;
        } else {
            int intNum = Integer.parseInt(num);
            Task temp = list.get(intNum-1);
            list.remove(intNum-1);
            return temp;
        }
    }

    public Task done(int num){
        return list.set(num-1, list.get(num-1).completedTask());
    }

    public List<Task> getList(){
        return list;
    }

    public int countList(){
        return list.size();
    }

    public void deleteAll(){
        list = new ArrayList<>();
    }
}
