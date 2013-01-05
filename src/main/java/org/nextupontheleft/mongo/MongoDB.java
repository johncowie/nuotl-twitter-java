package org.nextupontheleft.mongo;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;
import org.joda.time.LocalDateTime;
import org.nextupontheleft.domain.Approved;
import org.nextupontheleft.domain.Area;
import org.nextupontheleft.domain.Event;
import org.nextupontheleft.domain.Tweeter;

import java.util.Date;

public class MongoDB {

    Datastore ds;

    private static MongoDB instance;

    private MongoDB() throws Exception {
        ds = new Morphia().createDatastore(new Mongo("localhost", 27017), "nuotl");
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

    public static void main(String[] args) throws Exception {
        MongoCache cache = new MongoCache();
        cache.addEvent(createEvent(3, 9, 2012, 0, "event 1", cache.getArea("N")));
        cache.addEvent(createEvent(4, 9, 2012, 3, "event 2", cache.getArea("G")));
        cache.addEvent(createEvent(23, 10, 2012, 1, "event 3", cache.getArea("CF")));
        cache.addEvent(createEvent(29, 10, 2012, 0, "event 5", cache.getArea("S")));
    }

    public static Event createEvent(int day, int month, int year, int days, String text, Area a) {
        Tweeter t = new Tweeter(1, "johnacowie", "John Cowie", Approved.Y);
        int id = (int)(Math.random()*1000);
        Date startDate = new LocalDateTime(year, month, day, 19, 0, 0).toDate();
        Date endDate = new LocalDateTime(year, month, day, 19, 0, 0).plusDays(days).toDate();
        return new Event(id, startDate, endDate, a, text, "tags", t, Approved.Y);
    }

    public Datastore getDS() {
        return ds;
    }

    public void save(Object o) {
        ds.save(o);
    }

}
