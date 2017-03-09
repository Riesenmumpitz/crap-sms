package com.crap.sms.domain.repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Martin Geßenich on 09.03.2017.
 */
public abstract class AbstractRepository {
    private String storage;

    protected List<Object> getAllIntern() {
        ArrayList<Object> objects = new ArrayList<Object>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(storage));
            for (;;)
            {
                objects.add(ois.readObject());
            }
        } catch (EOFException e) {

        } catch (ClassNotFoundException e) {

        } catch (IOException e) {

        }
        return objects;
    }

    protected boolean save(Object o) {
        List<Object> objects = getAllIntern();
        if (objects.contains(o)) {
            objects.remove(o);
        }
        objects.add(o);
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(storage));
            for (Object obj : objects) {
                out.writeObject(obj);
            }
            out.close();
        } catch(FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    protected boolean delete(Object o) {
        boolean ret = false;
        List<Object> objects = getAllIntern();
        if (objects.contains(o)) {
            objects.remove(o);
            ret = true;
        }
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(storage));
            for (Object obj : objects) {
                out.writeObject(obj);
            }
            out.close();
        } catch(FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
        return ret;
    }
}
