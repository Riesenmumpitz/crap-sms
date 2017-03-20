package com.crap.sms.domain.repository;

import com.crap.sms.domain.model.Terminal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by batkefe on 09.03.2017.
 */
public class TerminalRepository extends AbstractRepository{

    private static TerminalRepository terminalRepository = new TerminalRepository();

    private TerminalRepository() {
        super("Terminal.txt");
        try {
            File file = new File(super.storage);
            file.createNewFile();
        }
        catch(IOException io) {
            io.printStackTrace();
        }
    }

    public static TerminalRepository getInstance(){
        return terminalRepository;
    }

    public boolean save(Terminal terminal) {
        List<Object> objects = super.getAllIntern();
        for(Object o : objects) {
            Terminal terminalAlt = (Terminal)o;
            if(terminal.getUniqueName().equals(terminalAlt.getUniqueName())) {
                if(!super.delete(terminalAlt)) {
                    return false;
                }
            }
        }
        return super.save(terminal);
    }

    public List<Terminal> getAll () {
        List<Terminal> terminals = new ArrayList<Terminal>();
        List<Object> objects = super.getAllIntern();
        for(Object o : objects) {
            terminals.add((Terminal)o);
        }
        return terminals;
    }

    public Terminal getByUniqueName(String uniqueName) {
        List<Object> objects = super.getAllIntern();
        for(Object o : objects) {
            Terminal terminal = (Terminal)o;
            if(terminal.getUniqueName().equals(uniqueName)) {
                return terminal;
            }
        }
        return null;
    }

    public boolean delete(Terminal terminal) {
        return super.delete(terminal);
    }
}
