package jeda00.adventura;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import jeda00.adventura.core.Init;

public class DefaultTestCase {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        Init.init();
    }

    @Test
    public void testDefault() {
        assertTrue(true);
    }

}
