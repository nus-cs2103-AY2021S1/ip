import org.junit.jupiter.api.Test;

public class DukeTest {
    //tests if duke can run
    @Test
    public void dukeTest(){
        new Duke("../../data/duke.txt").run();
    }
}
