package jeda00.adventura.client.txt;

import java.util.Scanner;

import jeda00.adventura.core.events.FlavourTextEvent;
import jeda00.adventura.support.Game;
import jeda00.adventura.support.Response;
import jeda00.container.Container;
import jeda00.eventbus.EventBus;

/**
 * Text client for the game
 */
public class TextClient {

    private final EventBus eventBus = Container.resolve(EventBus.class);

    private final Game game = Container.resolve(Game.class);

    private final Scanner scanner;

    private final StringBuilder flavourText;

    public TextClient() {
        scanner = new Scanner(System.in);
        flavourText = new StringBuilder();

        eventBus.subscribe(FlavourTextEvent.class, e -> flavourText.append(e.getMessage()));
    }

    /**
     * Starts the game
     */
    public void run() {
        System.out.println(game.getWelcomeMessage());

        while (game.isRunning()) {
            String line = scanner.nextLine();

            Response response = game.execute(line);

            System.out.println(response.getMessage());
            System.out.println(flavourText.toString());
            flavourText.setLength(0);
        }

        System.out.println(game.getWelcomeMessage());
    }

}
