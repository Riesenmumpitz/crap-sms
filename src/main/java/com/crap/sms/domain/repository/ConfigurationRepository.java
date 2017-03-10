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
        return "";
    }

    public boolean setConfig(Configuration config, String value) { return true; }
}
