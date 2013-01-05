package org.nextupontheleft.twitter;

public enum TweetParsingErrorCode {

    SUCCESS("Thanks!, Your event tweet was successful!"),
    DATE_ERROR("Sorry, I had trouble understanding the date."),
    TIME_ERROR("Sorry, I couldn't understand the time."),
    POSTAL_AREA_ERROR("Sorry, I couldn't understand the area code."),
    OUT_OF_DATE_ERROR("Sorry, it seems like your event is in the past. Double-check the date/time. "),
    UNAUTHORISED_USER("Sorry, you aren't authorised to post events yet. I'll let you know when you're authorised."),
    NOT_ENOUGH_PARTS("Sorry, you don't seem to have enough info in your tweet."),
    NO_URL("Please include a URL in your tweet that links to more info about the event."),
    DURATION_ERROR("There's a problem with the formatting of the duration");

    private String message;

    private TweetParsingErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}