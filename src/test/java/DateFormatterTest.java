import org.junit.jupiter.api.Test;

import rock.utility.DateFormatter;

public class DateFormatterTest {
    @Test
    public void formatDateTest() {
        String noFormat = DateFormatter.formatDate("next Monday");
        assert(noFormat.equals("next Monday"));
        String needFormat = DateFormatter.formatDate("2000-03-27");
        assert(needFormat.equals("Mar 27 2000"));
    }
}
