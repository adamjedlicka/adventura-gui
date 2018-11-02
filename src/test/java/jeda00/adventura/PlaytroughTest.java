package jeda00.adventura;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import jeda00.adventura.core.events.GameOverEvent;
import jeda00.adventura.core.events.GameWonEvent;
import jeda00.adventura.support.Game;
import jeda00.container.Container;
import jeda00.eventbus.EventBus;

public class PlaytroughTest extends DefaultTestCase {

    public Game game;

    public EventBus eventBus;

    @Before
    public void setUp() {
        super.setUp();

        game = Container.resolve(Game.class);
        eventBus = Container.resolve(EventBus.class);
    }

    @Test
    public void gameCanBeWon() {
        game.execute("pick_up rock");
        game.execute("go scissors_room");
        game.execute("put_down rock");
        game.execute("go room");

        game.execute("pick_up paper");
        game.execute("go rock_room");
        game.execute("put_down paper");
        game.execute("go room");

        game.execute("pick_up scissors");
        game.execute("go paper_room");
        game.execute("put_down scissors");
        game.execute("go room");

        assertEquals(1, eventBus.getSentEvents(GameWonEvent.class).size());
        assertFalse(game.isRunning());
    }

    @Test
    public void puttingScissorsToRockRoomFailsTheGame() {
        game.execute("pick_up scissors");
        game.execute("go rock_room");
        game.execute("put_down scissors");

        assertEquals(1, eventBus.getSentEvents(GameOverEvent.class).size());
        assertFalse(game.isRunning());
    }

}
