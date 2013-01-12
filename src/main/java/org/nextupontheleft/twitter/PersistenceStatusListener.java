/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nextupontheleft.twitter;

import org.apache.log4j.Logger;
import twitter4j.*;

/**
 *
 * @author john
 */
public class PersistenceStatusListener implements UserStreamListener {

    private static final Logger logger = Logger.getLogger(PersistenceStatusListener.class);

	private TweetProcessor processor;

    public PersistenceStatusListener(TweetProcessor tweetProcessor) {
        this.processor = tweetProcessor;
    }

	public void onStatus(Status status) {
		logger.debug("RECEIVE: " + status);
		this.processor.processTweet(status);
	}

	public void onDeletionNotice(StatusDeletionNotice sdn) {
		logger.debug("DELETE: " + sdn.getStatusId());
	}

	public void onTrackLimitationNotice(int i) {
		// do nothing
	}

	public void onScrubGeo(long l, long l1) {
		// do nothing
	}

	public void onException(Exception excptn) {
		logger.warn("Twitter Error: ", excptn);
	}

    @Override
    public void onDeletionNotice(long directMessageId, long userId) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onFriendList(long[] friendIds) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onFavorite(User source, User target, Status favoritedStatus) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onUnfavorite(User source, User target, Status unfavoritedStatus) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onFollow(User source, User followedUser) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onRetweet(User source, User target, Status retweetedStatus) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onDirectMessage(DirectMessage directMessage) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onUserListMemberAddition(User addedMember, User listOwner, UserList list) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onUserListMemberDeletion(User deletedMember, User listOwner, UserList list) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onUserListSubscription(User subscriber, User listOwner, UserList list) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onUserListUnsubscription(User subscriber, User listOwner, UserList list) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onUserListCreation(User listOwner, UserList list) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onUserListUpdate(User listOwner, UserList list) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onUserListDeletion(User listOwner, UserList list) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onUserProfileUpdate(User updatedUser) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onBlock(User source, User blockedUser) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onUnblock(User source, User unblockedUser) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
