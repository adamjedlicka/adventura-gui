package jeda00.adventura.commands.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Represents one argument of the command
 */
public class Argument {

    /**
     * Name of the argument
     */
    private String name;

    /**
     * Description of the argument
     */
    private String description;

    /**
     * List of possible values
     */
    private Optional<List<String>> possibleValuesList;

    /**
     * Supplier function which on execute returns list of the possible values
     */
    private Optional<Supplier<List<String>>> possibleValuesSupplier;

    public Argument(String name) {
        this.name = name;
        this.description = "";
        this.possibleValuesList = Optional.empty();
        this.possibleValuesSupplier = Optional.empty();
    }

    /**
     * Fluent description setter
     */
    public Argument description(String description) {
        this.description = description;

        return this;
    }

    /**
     * Fluent possibleValues setter
     */
    public Argument possibleValues(String... values) {
        possibleValuesList = Optional.of(Arrays.asList(values));

        return this;
    }

    /**
     * Fluent possibleValues setter
     */
    public Argument possibleValues(Supplier<List<String>> supplier) {
        possibleValuesSupplier = Optional.of(supplier);

        return this;
    }

    /**
     * Returns argument name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns argument description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns list of possible values. It gets it either from concrete list of
     * possible values or executes supplier method and return its result
     */
    public List<String> getPossibleValues() {
        if (possibleValuesList.isPresent()) {
            return possibleValuesList.get();
        }

        if (possibleValuesSupplier.isPresent()) {
            return possibleValuesSupplier.get().get();
        }

        return new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(name);
        sb.append(" - ");
        sb.append(description);

        return sb.toString();
    }

}
