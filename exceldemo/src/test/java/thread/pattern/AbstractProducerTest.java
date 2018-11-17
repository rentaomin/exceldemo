package thread.pattern;

import org.junit.Before;
import org.junit.Test;

public abstract class AbstractProducerTest {

    private AbstractProducer abstractProducer;

    public abstract AbstractProducer getAbstractProducer();

    public abstract  Producer getInstance();

    public abstract boolean startRun();

    @Before
    public void setUp() {
        abstractProducer = getAbstractProducer();
    }

    @Test
    public void testGetInstance() {
        AbstractProducer abstractProducer = getAbstractProducer();

    }
}