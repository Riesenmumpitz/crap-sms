package com.crap.sms.domain.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Martin Geßenich on 09.03.2017.
 */
public class Terminal implements Serializable {

	private String uniqueName;
	private boolean active;
	private Set<RAN> connections = new HashSet<RAN>();

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Terminal(String uniqueName, Set<RAN> connections) {
		this.uniqueName = uniqueName;
		this.connections = connections;
		this.active = true;
	}

	public Terminal(String uniqueName, Set<RAN> connections, boolean active) {
		this.uniqueName = uniqueName;
		this.connections = connections;
		this.active = active;
	}

	public String getUniqueName() {
		return uniqueName;
	}

	public Set<RAN> getConnections() {
		return connections;
	}

	public RAN getMaxConnection() {
		if (connections.contains(RAN.G4)) {
			return RAN.G4;
		} else if (connections.contains(RAN.G3)) {
			return RAN.G3;
		} else {
			return RAN.G2;
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Terminal terminal = (Terminal) o;

		return connections != null ? connections.equals(terminal.connections) : terminal.connections == null;
	}

	@Override
	public String toString() {
		String result = String.format("%s: RAN types: %s", uniqueName, RAN.toString(connections));
		if (active) {
			return result + "; active";
		} else {
			return result + "; not active";
		}
	}
}
