package nl.gremmee.lumbricidae.rules;

import java.lang.reflect.*;
import java.util.*;

public class Rules {
	private static Rules instance;

	private final Set<IRule> registry;

	private Rules() {
		this.registry = new TreeSet<IRule>();
		fillRegistry();
	}

	public static Rules getInstance() {
		if (instance == null) {
			instance = new Rules();
		}
		return instance;
	}

	public Set<IRule> getRules() {
		return this.registry;
	}

	private void fillRegistry() {
		registerRule(new LumbricidaeRule());
	}

	private void registerRule(IRule aRule) {

		System.out.print("Registering Rule " + aRule.getClass().getSimpleName() + "...");
		if (!this.registry.add(aRule)) {
			assert false : "Classname: " + aRule.getClass().getName() + " already exist.";
		}
		System.out.println("[OK]");
	}
}
