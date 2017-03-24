package nl.gremmee.lumbricidae.ai;

import java.util.Set;
import java.util.TreeSet;

public class AIs {
    private static AIs instance;

    private final Set<IArtificialIntelligence> registry;

    private AIs() {
        this.registry = new TreeSet<IArtificialIntelligence>();
        fillRegistry();
    }

    public static AIs getInstance() {
        if (instance == null) {
            instance = new AIs();
        }
        return instance;
    }

    public Set<IArtificialIntelligence> getAIs() {
        return this.registry;
    }

    private void fillRegistry() {
        registerAI(new FirstAvailableAI());
        registerAI(new MostLumbricidaePointsFirstAI());
        registerAI(new SequentialAI());
    }

    private void registerAI(IArtificialIntelligence aArtificialIntelligence) {

        System.out.print("Registering AI " + aArtificialIntelligence.getClass().getSimpleName() + "...");
        if (!this.registry.add(aArtificialIntelligence)) {
            assert false : "Classname: " + aArtificialIntelligence.getClass().getName() + " already exist.";
        }
        System.out.println("[OK]");
    }
}
