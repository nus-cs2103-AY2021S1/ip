package duke.Command;

import duke.Command.Command;
<<<<<<< HEAD
import duke.Duke;
=======
>>>>>>> 7cf06867bc1780a8ba55bc5d6537869fa9988b2d

public class ListCommand extends Command {

    public String execute() {

        StringBuilder list = new StringBuilder();

<<<<<<< HEAD
        if (Duke.listArray.isEmpty()) {
            return "list is empty";
        } else {
            for (int i = 1; i <= Duke.listArray.size(); i++) {
                if (i == Duke.listArray.size()) {
                    list.append(i).append(".").append(Duke.listArray.get(i - 1).toString());
                } else {
                    list.append(i).append(".").append(Duke.listArray.get(i - 1).toString())
=======
        if (listArray.isEmpty()) {
            return "list is empty";
        } else {
            for (int i = 1; i <= listArray.size(); i++) {
                if (i == listArray.size()) {
                    list.append(i).append(".").append(listArray.get(i - 1).toString());
                } else {
                    list.append(i).append(".").append(listArray.get(i - 1).toString())
>>>>>>> 7cf06867bc1780a8ba55bc5d6537869fa9988b2d
                            .append("\n");
                }
            }
            return "Here are the tasks in your list:\n" + list.toString();
        }
    }
}
