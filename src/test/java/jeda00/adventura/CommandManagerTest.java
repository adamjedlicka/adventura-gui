package jeda00.adventura;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import jeda00.adventura.commands.LookAroundCommand;
import jeda00.adventura.core.DefaultCommandManager;
import jeda00.adventura.support.Command;
import jeda00.adventura.support.CommandManager;

public class CommandManagerTest extends DefaultTestCase {

    @Test
    public void testItReturnsProperCommand() {
        CommandManager cm = new DefaultCommandManager();
        cm.registerCommand(new LookAroundCommand());
        Command c = cm.getCommand("look_around").get();

        assertTrue(c instanceof LookAroundCommand);
    }

}
