package jeda00.adventura.core;

import jeda00.adventura.commands.ExitCommand;
import jeda00.adventura.commands.GoCommand;
import jeda00.adventura.commands.LookAroundCommand;
import jeda00.adventura.commands.PickUpCommand;
import jeda00.adventura.commands.PutDownCommand;
import jeda00.adventura.core.events.NewGameEvent;
import jeda00.adventura.items.GenericItem;
import jeda00.adventura.places.GenericPlace;
import jeda00.adventura.places.PaperRoom;
import jeda00.adventura.places.RockRoom;
import jeda00.adventura.places.ScissorsRoom;
import jeda00.adventura.support.CommandManager;
import jeda00.adventura.support.Game;
import jeda00.adventura.support.Item;
import jeda00.adventura.support.ItemManager;
import jeda00.adventura.support.Place;
import jeda00.adventura.support.PlaceManager;
import jeda00.container.Container;
import jeda00.eventbus.DefaultEventBus;
import jeda00.eventbus.EventBus;

/**
 * Static class responsible for initializing the whole game
 */
public class Init {

    public static void init() {
        Container.clear();

        Container.bindSingleton(EventBus.class, DefaultEventBus::new);

        Container.bindSingleton(Game.class, DefaultGame::new);
        Container.bindSingleton(CommandManager.class, DefaultCommandManager::new);
        Container.bindSingleton(PlaceManager.class, DefaultPlaceManager::new);

        Container.bindFactory(ItemManager.class, DefaultItemManager::new);

        registerCommands();
        createWorld();

        Container.resolve(EventBus.class).subscribe(NewGameEvent.class, e -> init());
    }

    public static void registerCommands() {
        CommandManager cm = Container.resolve(CommandManager.class);

        cm.registerCommand(new ExitCommand());
        cm.registerCommand(new LookAroundCommand());
        cm.registerCommand(new GoCommand());
        cm.registerCommand(new PickUpCommand());
        cm.registerCommand(new PutDownCommand());
    }

    public static void createWorld() {
        PlaceManager pm = Container.resolve(PlaceManager.class);

        GenericPlace entry = new GenericPlace("room");
        RockRoom rockRoom = new RockRoom();
        PaperRoom paperRoom = new PaperRoom();
        ScissorsRoom scissorsRoom = new ScissorsRoom();

        entry.getNeighbours().add(rockRoom);
        entry.getNeighbours().add(paperRoom);
        entry.getNeighbours().add(scissorsRoom);
        rockRoom.getNeighbours().add(entry);
        paperRoom.getNeighbours().add(entry);
        scissorsRoom.getNeighbours().add(entry);

        Place r1 = new GenericPlace("room1");
        r1.getNeighbours().add(entry);
        entry.getNeighbours().add(r1);
        pm.addPlace(r1);

        Place r2 = new GenericPlace("room2");
        r2.getNeighbours().add(rockRoom);
        rockRoom.getNeighbours().add(r2);
        pm.addPlace(r2);

        pm.addPlace(entry);
        pm.addPlace(rockRoom);
        pm.addPlace(paperRoom);
        pm.addPlace(scissorsRoom);
        pm.setCurrentPlace(entry);

        Item rock = new GenericItem("rock", true);
        entry.getItemManager().addItem(rock);

        Item paper = new GenericItem("paper", true);
        entry.getItemManager().addItem(paper);

        Item scissors = new GenericItem("scissors", true);
        entry.getItemManager().addItem(scissors);
    }

}
