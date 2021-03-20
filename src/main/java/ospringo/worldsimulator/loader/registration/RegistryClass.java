package ospringo.worldsimulator.loader.registration;

/**
 * 类型注册
 */
public class RegistryClass {

    private final Class<? extends IRegistrable> clazz;

    public RegistryClass(IRegistrable registry) {
        clazz = registry.getClass();
    }

    public Class<? extends IRegistrable> getClazz() {
        return clazz;
    }

    public IRegistrable newInstance() {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            return null;
        }
    }

    public RegistryInstance newStack() {
        return new RegistryInstance(newInstance());
    }
}
