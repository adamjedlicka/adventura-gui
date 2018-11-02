package jeda00.adventura.client.gui.map;

import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Factory.to;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.RankDir;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Link;
import jeda00.adventura.support.Place;
import jeda00.adventura.support.PlaceManager;
import jeda00.container.Container;

public class MapGenerator {

    private final PlaceManager placeManager = Container.resolve(PlaceManager.class);

    private Graph graph;

    public MapGenerator generate() {
        graph = graph("map").graphAttr().with(RankDir.LEFT_TO_RIGHT);

        Place currentPlace = placeManager.getCurrentPlace();

        // Iterate over all places
        for (Place place : placeManager.getPlaces()) {
            Link[] links = new Link[place.getNeighbours().size()];

            // Create array of all links to all neighbours
            for (int i = 0; i < place.getNeighbours().size(); i++) {
                links[i] = to(node(place.getNeighbours().get(i).getName()));
            }

            // If current room set color to red
            Color color = place.equals(currentPlace) ? Color.RED : Color.BLACK;

            // Add current room to the graph with links
            graph = graph.with(node(place.getName()).with(color).link(links));
        }

        return this;
    }

    public InputStream toInputStream() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        try {
            Graphviz.fromGraph(graph).width(720).height(540).render(Format.PNG).toOutputStream(os);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        byte[] bytes = os.toByteArray();

        return new ByteArrayInputStream(bytes);
    }

}
