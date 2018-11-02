package jeda00.container;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class Container {

    private static final Map<Class<?>, Resolver<?>> resolvers = new HashMap<>();

    private Container() {
    }

    /**
     * Binds factory method that returns implementation instance of the interface.
     *
     * @param iface   Interface for future resolution
     * @param factory Factory function returning concrete instance
     */
    public static <T> void bindFactory(Class<T> iface, Supplier<T> factory) {
        resolvers.put(iface, new FactoryResolver<T>(factory));
    }

    /**
     * Binds factory method which is used exactly once for creation of singleton
     * instance.
     *
     * @param iface     Interface for future resolution
     * @param singleton Factory function returning the instance of the singleton
     */
    public static <T> void bindSingleton(Class<T> iface, Supplier<T> singleton) {
        resolvers.put(iface, new SingletonResolver<T>(singleton));
    }

    /**
     * Returns implementation instance of the requested interface. Creates new
     * instance or uses singleton based on type of binding.
     *
     * @param iface Interface to be resolved
     * @return Instance of the interface
     */
    @SuppressWarnings("unchecked")
    public static <T> T resolve(Class<T> iface) {
        return (T) resolvers.get(iface).resolve();
    }

    /**
     * Clears the container and removes all binded interfaces.
     */
    public static void clear() {
        resolvers.clear();
    }

}
