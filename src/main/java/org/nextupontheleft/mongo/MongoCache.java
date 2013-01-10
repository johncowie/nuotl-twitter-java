package org.nextupontheleft.mongo;

import com.google.code.morphia.Datastore;
import org.nextupontheleft.domain.*;

import java.util.List;

public class MongoCache implements NuotlCache {

    Datastore ds = MongoDB.getInstance().getDS();

    public void addEvent(Event event) {
        ds.save(event);
    }

    public void addTweeter(Tweeter tweeter) {
        ds.save(tweeter);
    }

    public Tweeter getTweeter(long id) {
        return ds.find(Tweeter.class, "_id", id).get();
    }

    public void addFeature(FeatureTweet feature) {
        ds.save(feature);
    }

    public List<Area> getAreas() {
        return ds.find(Area.class).asList();
    }
}
