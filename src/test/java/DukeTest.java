import org.junit.jupiter.api.Test;

public class DukeTest {
    
    @Test
    public void testOverall() {
        new Duke("taskList").run();
    }
    
    @Test
    public void testDelete(TaskList taskList, Ui ui) {
        taskList.deleteTask(2);
        ui.showList(taskList);
    }
    
    @Test
    public void testEvent() {
        
    }
}
