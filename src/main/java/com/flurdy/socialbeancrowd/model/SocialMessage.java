package com.flurdy.socialbeancrowd.model;


import org.joda.time.DateTime;

public interface SocialMessage extends Comparable {

    String getPostMessage();

    String getWallMessage();

    DateTime getTimestamp();

}
