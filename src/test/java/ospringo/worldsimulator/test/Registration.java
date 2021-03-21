package ospringo.worldsimulator.test;

import ospringo.worldsimulator.basic.classes.*;
import ospringo.worldsimulator.loader.registration.*;
import org.junit.jupiter.api.Test;
import java.util.*;

public class Registration {

    @Test
    public void register() {
        new RegistryTable(new Data());
        RegistryTable table = RegistryTable.getTable(Data.class);
        table.register(new Data());
        table.register(new Data(), new HashMap<>());
        table.set(0, "Hello", "world");
        System.out.println((String) table.get(0, "Hello"));
    }
}
