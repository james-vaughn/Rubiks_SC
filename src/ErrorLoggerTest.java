import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by james on 12/1/16.
 */
public class ErrorLoggerTest {

    ByteArrayOutputStream errText = new ByteArrayOutputStream();


    @Before
    public void SetUp() {
        System.setErr(new PrintStream(errText));
    }


    //die

    //structured basis
    //full coverage
    @Test(expected = RuntimeException.class)
    public void Should_print_message_and_die() {
        ErrorLogger.getInstance().die("test");
        assertEquals("test", errText.toString());
    }

    //bad data
    @Test(expected = RuntimeException.class)
    public void Should_not_print_anything() {
        ErrorLogger.getInstance().die(null);
        assertEquals("", errText.toString());
    }
}
