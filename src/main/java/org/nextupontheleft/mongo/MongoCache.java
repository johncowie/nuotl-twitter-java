package org.nextupontheleft.mongo;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.query.Query;
import org.joda.time.LocalDate;
import org.nextupontheleft.domain.*;

import java.util.List;

public class MongoCache implements NuotlCache {

    Datastore ds = MongoDB.getInstance().getDS();

    public List<Event> getEvents(int month, int year) {
        Query<Event> q = ds.createQuery(Event.class);
        q.field("start_time").greaterThan(new LocalDate(year, month, 1).toDate());
        q.field("end_time").lessThan(new LocalDate(year, month+1, 1).toDate());
        return q.asList();
    }

    public List<Tweeter> getAuthorisedTweeters() {
        return ds.find(Tweeter.class).field("authorised").equal("Y").asList();
    }

    public void addEvent(Event event) {
        ds.save(event);
    }

    public void addFailedEvent(FailedEvent event) {
        throw new UnsupportedOperationException();    }

    public void addEventResponse(EventResponse response) {
        throw new UnsupportedOperationException();    }

    public Tweeter getTweeter(long id) {
        return ds.find(Tweeter.class, "_id", id).get();
    }

    public long getMaxTweetId() {
        throw new UnsupportedOperationException();
    }
}
