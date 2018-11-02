package jeda00.adventura.client.gui;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TextArea;
import jeda00.adventura.core.events.CommandExecutedEvent;
import jeda00.adventura.core.events.FlavourTextEvent;
import jeda00.adventura.support.Game;
import jeda00.container.Container;
import jeda00.eventbus.EventBus;

/**
 * Output window for rendering the responses from the game
 */
public class FXOutput extends TextArea {

    private final EventBus eventBus;

    private final Game game;

    private final List<String> flavourTexts;

    public FXOutput() {
        eventBus = Container.resolve(EventBus.class);
        game = Container.resolve(Game.class);
        flavourTexts = new ArrayList<>();

        setEditable(false);

        setText(game.getWelcomeMessage() + "\n");

        eventBus.subscribe(CommandExecutedEvent.class, this::onCommandExecuted);
        eventBus.subscribe(FlavourTextEvent.class, this::onFlavourText);
    }

    /**
     * After command was executed add its reponse and any aditional flavour text to
     * the output window
     */
    private void onCommandExecuted(CommandExecutedEvent event) {
        appendText("> " + event.getCommand() + "\n");
        appendText(event.getResponse().getMessage().trim() + "\n");

        for (String flavourText : flavourTexts) {
            appendText(" * ");
            appendText(flavourText);
            appendText("\n");
        }

        flavourTexts.clear();
    }

    /**
     * When new flavour text was generated add it to the buffer for rendering after
     * the next command
     */
    private void onFlavourText(FlavourTextEvent event) {
        flavourTexts.add(event.getMessage());
    }

}
