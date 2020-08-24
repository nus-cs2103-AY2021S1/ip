package test.java;

import luoyi.duke.common.TimeWrapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TimeWrapperTest {

    @Test
    public void equals_sameDateTime_true() {
        TimeWrapper t1 = TimeWrapper.getTimeWrapper("2020 11 11 12:12");
        TimeWrapper t2 = TimeWrapper.getTimeWrapper("2020 11 11 12:12");
        assertEquals(t2, t1);
    }

    @Test
    public void equals_sameDateDiffTime_false() {
        TimeWrapper t1 = TimeWrapper.getTimeWrapper("2020 11 11 12:11");
        TimeWrapper t2 = TimeWrapper.getTimeWrapper("2020 11 11 22:12");
        assertNotEquals(t2, t1);
    }

    @Test
    public void equals_sameDate_true() {
        TimeWrapper t1 = TimeWrapper.getTimeWrapper("2020 11 11");
        TimeWrapper t2 = TimeWrapper.getTimeWrapper("2020 11 11");
        assertEquals(t2, t1);
    }

    @Test
    public void equals_diffDate_false() {
        TimeWrapper t1 = TimeWrapper.getTimeWrapper("2020 11 11");
        TimeWrapper t2 = TimeWrapper.getTimeWrapper("2020 8 11");
        assertNotEquals(t2, t1);
    }

    @Test
    public void equals_sameStringDescription_true() {
        TimeWrapper t1 = TimeWrapper.getTimeWrapper("Tomorrow");
        TimeWrapper t2 = TimeWrapper.getTimeWrapper("Tomorrow");
        assertEquals(t2, t1);
    }

    @Test
    public void equals_diffStringDescription_false() {
        TimeWrapper t1 = TimeWrapper.getTimeWrapper("Tomorrow");
        TimeWrapper t2 = TimeWrapper.getTimeWrapper("Next year");
        assertNotEquals(t2, t1);
    }
}
