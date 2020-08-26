import java.util.Arrays;
import java.util.List;

public class TaskSearcher {
    TaskList taskList;
    
    TaskSearcher(TaskList taskList) {
        this.taskList = taskList;
    }
    
    void searchAndDisplay(String searchString) {
        List<Task> results = taskList.searchByKeyword(searchString);
        Ui.showSearchResults(results);
    }
}
