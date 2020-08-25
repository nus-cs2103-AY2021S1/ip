import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    TaskList myTaskList = new TaskList();
    Task task1 = new Task("[T]","Task1");
    Task task2 = new Task("[T]","Task2");
    Task task3 = new Task("[T]","Task3");



    @Test
    public void addTest(){
        myTaskList.addTask(task1);
        myTaskList.addTask(task2);
        myTaskList.addTask(task3);

        assertEquals(myTaskList.getTasks().size(),3);
    }

    @Test
    public void deleteTest(){
        myTaskList.addTask(task1);
        myTaskList.addTask(task2);
        myTaskList.addTask(task3);

        myTaskList.deleteTask(1);
        assertEquals(myTaskList.getTasks().size(),2);
    }


}
