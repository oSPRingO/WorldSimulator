package ospringo.worldsimulator.loader.registration;

/**
 * 可注册对象
 */
public interface IRegistrable {

    RegistryClass register();

    RegistryInstance registerStack();
}
