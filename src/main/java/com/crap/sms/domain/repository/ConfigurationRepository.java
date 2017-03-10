package com.crap.sms.domain.repository;

import com.crap.sms.domain.model.Configuration;
import com.crap.sms.domain.model.User;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by batkefe on 09.03.2017.
 */
public class ConfigurationRepository extends AbstractRepository{

    private static ConfigurationRepository configurationRepository = new ConfigurationRepository();

    private ConfigurationRepository() {
        super("Config.txt");
        try {
            File file = new File(super.storage);
            file.createNewFile();
        }
        catch(IOException io) {
            io.printStackTrace();
        }
    }

    public static ConfigurationRepository getInstance(){
        return configurationRepository;
    }

    public String getConfig(Configuration config) {
        List<Object> objects = super.getAllIntern();
        for(Object o : objects) {
            String[] conf = (String[])o;
            if(conf[0].equals(config.name())) {
                return conf[1];
            }
        }
        return null;
    }

    public boolean setConfig(Configuration config, String value) {
        List<Object> objects = super.getAllIntern();
        for(Object o : objects) {
            String[] conf = (String[])o;
            if(conf[0].equals(config.name())) {
                if(!super.delete(conf)) {
                    return false;
                }
            }
        }
        String[] a = new String[2];
        a[0] = config.name();
        a[1] = value;
        return super.save(a);
    }

    public boolean deleteConfig(Configuration config) {
        List<Object> objects = super.getAllIntern();
        for(Object o : objects) {
            String[] conf = (String[])o;
            if(conf[0].equals(config.name())) {
                return super.delete(conf);
            }
        }
        return false;
    }
}
