package ospringo.worldsimulator.loader.registration;

/**
 * 注册对象
 */
public class BaseRegistrableObject implements IRegistrable {

    @Override
    public RegistryClass register() {
        return new RegistryClass(this);
    }

    @Override
    public RegistryInstance registerStack() {
        return new RegistryInstance(this);
    }
}
