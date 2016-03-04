package wsc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import ec.Individual;
import ec.simple.SimpleFitness;
import ec.util.Parameter;

public class WSCIndividual extends Individual {

	private static final long serialVersionUID = 1L;
	private Map<String, Set<String>> predecessorMap;

	public WSCIndividual(){
		super();
		super.fitness = new SimpleFitness();
		super.species = new WSCSpecies();
	}

	public WSCIndividual(Map<String, Set<String>> map) {
		super();
		super.fitness = new SimpleFitness();
		super.species = new WSCSpecies();
		predecessorMap = map;
	}

	@Override
	public Parameter defaultBase() {
		return new Parameter("wscindividual");
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof WSCIndividual) {
			return toString().equals(other.toString());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	public Map<String, Set<String>> getPredecessorMap() {
		return predecessorMap;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Entry<String, Set<String>> e : predecessorMap.entrySet()) {
			builder.append("(");
			builder.append(e.getKey());
			builder.append(",");
			builder.append(e.getValue());
			builder.append("), ");
		}
		return builder.toString();
	}

	@Override
	public WSCIndividual clone() {
		Map<String, Set<String>> newMap = new HashMap<String, Set<String>>();
		for (Entry<String, Set<String>> e : predecessorMap.entrySet()) {
			String key = e.getKey();
			Set<String> value = new HashSet<String>();
			value.addAll(e.getValue());
			newMap.put(key, value);
		}
		WSCIndividual wsci = new WSCIndividual(newMap);
		wsci.fitness = (SimpleFitness) fitness.clone();
		wsci.species = species;
		return wsci;
	}
}
