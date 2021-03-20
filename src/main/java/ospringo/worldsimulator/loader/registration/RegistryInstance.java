package ospringo.worldsimulator.loader.registration;

import java.util.*;

/**
 * 注册实例
 */
public class RegistryInstance {

    private IRegistrable registry;
    private Map<String, Object> options;

    public RegistryInstance(IRegistrable registry) {
        this.registry = registry;
        this.options = new HashMap<>();
    }

    public Object getOption(String name) {
        return options.get(name);
    }

    public void setOption(String name, Object option) {
        options.put(name, option);
    }

    void setOptions(Map<String, Object> options) {
        this.options = options;
    }

    Map<String, Object> getOptions() {
        return options;
    }
}
