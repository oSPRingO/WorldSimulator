package ospringo.worldsimulator.loader.registration;

/**
 * 注册对象
 */
public class BaseRegistrableObject implements IRegistrable {

    RegistryClass rc = new RegistryClass(this);
    RegistryInstance instance = new RegistryInstance(this);

    @Override
    public RegistryClass register() {
        return rc;
    }

    @Override
    public RegistryInstance registerInstance() {
        return instance;
    }
}
