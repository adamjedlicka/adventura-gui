package jeda00.adventura;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import jeda00.adventura.support.Game;
import jeda00.container.Container;

public class GameTest extends DefaultTestCase {

    @Test
    public void testGameIsRunningAfterCreation() {
        assertTrue(Container.resolve(Game.class).isRunning());
    }

    @Test
    public void testIfContainerResolvesSingletonsProperly() {
        Game g1 = Container.resolve(Game.class);
        Game g2 = Container.resolve(Game.class);

        assertEquals(g1, g2);
    }

}
