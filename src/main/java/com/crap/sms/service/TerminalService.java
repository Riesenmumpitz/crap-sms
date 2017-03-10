package com.crap.sms.service;

import com.crap.sms.domain.model.Terminal;
import com.crap.sms.domain.repository.TerminalRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martin Geßenich on 10.03.2017.
 */
public class TerminalService {

    private static TerminalRepository terminalRepository = TerminalRepository.getInstance();

    public static ArrayList<String> getTerminalTypes() {
        ArrayList<String> ret = new ArrayList<String>();
        List<Terminal> all = terminalRepository.getAll();
        for (Terminal t : all) {
            ret.add(t.getUniqueName());
        }
        return ret;
    }

    public static String[] getTerminalTypesArray() {
        ArrayList<String> subscriptionTypes = getTerminalTypes();
        return subscriptionTypes.toArray(new String[0]);
    }

    public static boolean addTerminal(Terminal terminal) {
        return terminalRepository.save(terminal);
    }
}
