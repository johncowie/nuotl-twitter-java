package org.nextupontheleft.mongo;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;

public class MongoDB {

    Datastore ds;

    private static MongoDB instance;

    private MongoDB() throws Exception {
        Morphia morphia = new Morphia();
        ds = morphia.createDatastore(new Mongo("localhost", 27017), "nuotl");

    }

    public static MongoDB getInstance() {
        if(instance == null) {
            try {
                instance = new MongoDB();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public Datastore getDS() {
        return ds;
    }

    public void save(Object o) {
        ds.save(o);
    }

}
