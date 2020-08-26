import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

	@Test
	public void runTest() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		Duke duke = new Duke("data/duke.txt");
		String welcomeMsg = "";
		assertEquals(outContent.toString(), welcomeMsg);

		InputStream sysInBackup = System.in;
		ByteArrayInputStream in = new ByteArrayInputStream("bye".getBytes());
		System.setIn(in);

		String byeMsg = "";
		assertEquals(outContent.toString(), byeMsg);
	}
}
