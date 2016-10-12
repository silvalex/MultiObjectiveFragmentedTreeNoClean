package wsc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import ec.Fitness;
import ec.Individual;
import ec.simple.SimpleFitness;
import ec.util.Parameter;

public class WSCIndividual extends Individual {

	private double availability;
	private double reliability;
	private double time;
	private double cost;
	private static final long serialVersionUID = 1L;
	private Map<String, Set<String>> predecessorMap;

//	public WSCIndividual(){
//		super();
//		super.fitness = new SimpleFitness();
//		super.species = new WSCSpecies();
//	}
//
//	public WSCIndividual(Map<String, Set<String>> map) {
//		super();
//		super.fitness = new SimpleFitness();
//		super.species = new WSCSpecies();
//		predecessorMap = map;
//	}

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
		WSCIndividual wsci = new WSCIndividual();
		wsci.species = this.species;

		if (this.fitness == null)
    		wsci.fitness = (Fitness) wsci.species.f_prototype.clone();
    	else
    		wsci.fitness = (Fitness) this.fitness.clone();

		wsci.setAvailability(availability);
		wsci.setReliability(reliability);
		wsci.setTime(time);
		wsci.setCost(cost);

		if (predecessorMap != null) {
			Map<String, Set<String>> newMap = new HashMap<String, Set<String>>();
			for (Entry<String, Set<String>> e : predecessorMap.entrySet()) {
				String key = e.getKey();
				Set<String> value = new HashSet<String>();
				value.addAll(e.getValue());
				newMap.put(key, value);
			}
			wsci.setMap(newMap);
		}
		return wsci;
	}

	public void setAvailability(double availability) {
		this.availability = availability;
	}

	public void setReliability(double reliability) {
		this.reliability = reliability;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public void setMap(Map<String, Set<String>> predecessorMap) {
		this.predecessorMap = predecessorMap;
	}

	public double getAvailability() {
		return availability;
	}

	public double getReliability() {
		return reliability;
	}

	public double getTime() {
		return time;
	}

	public double getCost() {
		return cost;
	}
}
