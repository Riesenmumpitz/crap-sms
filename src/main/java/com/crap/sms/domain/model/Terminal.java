package com.crap.sms.domain.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Martin Geßenich on 09.03.2017.
 */
public class Terminal {
    private Set<RAN> connections = new HashSet<RAN>();

    public Terminal(Set<RAN> connections) {
        this.connections = connections;
    }

    public Set<RAN> getConnections() {
        return connections;
    }
}
