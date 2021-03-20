package ospringo.worldsimulator.loader.registration;

import java.util.*;

/**
 * 注册表管理器
 */
public class RegistryTable {

    private static final List<RegistryTable> tables;

    private final List<RegistryClass> classes;
    private final List<RegistryInstance> instances;

    static {
        tables = new ArrayList<>();

        RegistryTable tableMap = new RegistryTable();
        tableMap.register(new BaseRegistrableObject());
        tableMap.register(new BaseRegistrableObject(), new HashMap<>());
        tableMap.set(0, "location", 1);
    }

    public static RegistryTable getTable(Class<? extends IRegistrable> clazz) {
        RegistryTable meta = tables.get(0);
        for (int i=1; i < meta.classes.size(); i++) {
            if (Objects.equals(meta.classes.get(i).getClazz(), clazz)) {
                return tables.get((int) meta.instances.get(i).getOption("location"));
            }
        }
        return null;
    }

    private RegistryTable() {
        classes = new ArrayList<>();
        instances = new ArrayList<>();

        tables.add(this);
    }

    public RegistryTable(IRegistrable registry) {
        this();
        tables.get(0).register(registry);
        tables.get(0).register(registry, new HashMap<>());
        tables.get(0).set(tables.size()-1, "location", tables.size()-1);
    }

    public void register(IRegistrable registry) {
        if (!classes.contains(registry.register())) {
            classes.add(registry.register());
        }
    }

    public void register(IRegistrable registry, Map<String, Object> options) {
        if (classes.contains(registry.register())) {
            RegistryInstance instance = new RegistryInstance(registry);
            instance.setOptions(options);
            instances.add(instance);
        }
    }

    public void set(int index, Map<String, Object> options) {
        if (instances.size() > index) {
            RegistryInstance instance = instances.get(index);
            instance.setOptions(options);
            instances.set(index, instance);
        }
    }

    public void set(int index, String name, Object option) {
        if (instances.size() > index) {
            RegistryInstance instance = instances.get(index);
            Map<String, Object> options = instance.getOptions();
            options.put(name, option);
            instance.setOptions(options);
            instances.set(index, instance);
        }
    }

    public RegistryInstance[] getInstances() {
        return (RegistryInstance[]) instances.toArray();
    }
}
