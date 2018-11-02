package jeda00.container;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;

public class ContainerTest {

    @Before
    public void setUp() {
        Container.clear();
    }

    @Test
    public void interfaceCanBeResolvedFromTheContainer() {
        Container.bindFactory(Adder.class, SimpleAdder::new);

        Adder adder = Container.resolve(Adder.class);

        assertNotNull(adder);

        assertEquals(2, adder.add(1, 1));
    }

    @Test
    public void singletonCanBeResolvedFromTheContainer() {
        Container.bindSingleton(Adder.class, SimpleAdder::new);

        Adder adderResolved1 = Container.resolve(Adder.class);
        Adder adderResolved2 = Container.resolve(Adder.class);

        assertEquals(adderResolved1, adderResolved2);
    }

    @Test
    public void multipleInterfaceCanBeBoundToTheContainer() {
        Container.bindFactory(Adder.class, SimpleAdder::new);
        Container.bindFactory(Multiplier.class, SimpleMultiplier::new);

        Adder adder = Container.resolve(Adder.class);
        Multiplier multiplier = Container.resolve(Multiplier.class);

        assertNotEquals(adder, multiplier);

        assertEquals(6, adder.add(3, 3));
        assertEquals(9, multiplier.multiply(3, 3));
    }

    @Test
    public void testClearMethod() {
        Container.bindSingleton(Adder.class, SimpleAdder::new);

        Adder a1 = Container.resolve(Adder.class);
        Adder a2 = Container.resolve(Adder.class);

        assertEquals(a1, a2);

        Container.clear();
        Container.bindSingleton(Adder.class, SimpleAdder::new);

        Adder a3 = Container.resolve(Adder.class);

        assertNotEquals(a1, a3);
    }

}
