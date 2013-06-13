package com.societe.blog.utils;

import com.google.common.base.Predicate;
import com.societe.blog.domain.FORMAT;
import com.societe.blog.domain.Talk;

public final class TalkHelper {

    public static Predicate<Talk> TALK_ABOUT_TOOLS_IN_ACTION = new Predicate<Talk>() {
        @Override
        public boolean apply(com.societe.blog.domain.Talk talk) {
            return talk.getFormat().equals(FORMAT.TOOLS_IN_ACTION);
        }
    };

    public static Predicate<Talk> BIGLAB = new Predicate<Talk>() {
        @Override
        public boolean apply(com.societe.blog.domain.Talk talk) {
            return talk.getFormat().equals(FORMAT.BIGLAB);
        }
    };

    public static Predicate<Talk> LAB = new Predicate<Talk>() {
        @Override
        public boolean apply(com.societe.blog.domain.Talk talk) {
            return talk.getFormat().equals(FORMAT.LAB);
        }
    };


    private TalkHelper() {
    }

}
