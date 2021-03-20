package ospringo.worldsimulator.basic;

import ospringo.worldsimulator.basic.classes.*;
import ospringo.worldsimulator.loader.registration.*;

public class Registration {
    
    static {
        new RegistryTable(new Data());
        new RegistryTable(new Event());
        new RegistryTable(new Landform());
        new RegistryTable(new Mobile());
    }
}
