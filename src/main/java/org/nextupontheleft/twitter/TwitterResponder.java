/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nextupontheleft.twitter;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;

public class TwitterResponder {

	private Twitter twitter;
	private Long twitterId;

	public TwitterResponder(Configuration configuration) throws Exception {
		twitter = new TwitterFactory(configuration).getInstance();
		twitterId = twitter.getId();
	}
	
	public long getTwitterAccountId() {
		return twitterId;
	}
	
	public void replyToTweet(Status tweet, TweetParsingErrorCode errorCode) {

//		String message = "@" + tweet.getUser().getScreenName() + " " + errorCode.getMessage() + " " + constructTweetUrl(tweet);
//		System.out.println("Attempting to send: " + message);
//		StatusUpdate statusUpdate = new StatusUpdate(message);
//		statusUpdate.inReplyToStatusId(tweet.getId());
//		try {
//            if(this.configFlagProvider.isFlagEnabled(ConfigFlagId.RESPOND_TO_TWEETS)) {
//                twitter.updateStatus(statusUpdate);
//            }
//		} catch(TwitterException e) {
//			e.printStackTrace();
//		}
	}
        
        private String constructTweetUrl(Status tweet) {
            return "http://twitter.com/" + tweet.getUser().getId() + "/status/" + tweet.getId();
        }
	
}
