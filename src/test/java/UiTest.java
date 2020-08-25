import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    Ui ui = new Ui(new Scanner(System.in));

    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }
}
