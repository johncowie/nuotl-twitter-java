package org.nextupontheleft.domain;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Property;
import twitter4j.Status;

import java.util.Date;

@Entity(value="feature", noClassnameStored=true)
public class FeatureTweet {
    @Id
    private long id;
    private String username;
    @Property("screen-name")
    private String screenName;
    private String text;
    @Property("created-at")
    private Date createdAt;

    public FeatureTweet(Status tweet) {
        id = tweet.getId();
        username = tweet.getUser().getScreenName();
        screenName = tweet.getUser().getName();
        text = tweet.getText();
        createdAt = tweet.getCreatedAt();
    }
}
