package com.crap.sms.domain.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Martin Geßenich on 09.03.2017.
 */
public class Terminal implements Serializable {

    private String uniqueName;
    private Set<RAN> connections = new HashSet<RAN>();

    public Terminal(String uniqueName, Set<RAN> connections) {
        this.uniqueName = uniqueName;
        this.connections = connections;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public Set<RAN> getConnections() {
        return connections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Terminal terminal = (Terminal) o;

        return connections != null ? connections.equals(terminal.connections) : terminal.connections == null;
    }
}
