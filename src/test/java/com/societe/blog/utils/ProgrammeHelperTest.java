package com.societe.blog.utils;

import com.societe.blog.domain.FORMAT;
import com.societe.blog.domain.Talk;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class ProgrammeHelperTest {


    @Test
    public void should_return_true_when_talk_about_tools_in_action() {
        Talk t = new Talk();
        t.setFormat(FORMAT.TOOLS_IN_ACTION);
        assertThat(ProgrammeHelper.TALK_ABOUT_TOOLS_IN_ACTION.apply(t)).isTrue();
    }

    @Test
    public void should_return_false_when_talk_about_tools_in_action() {
        Talk t = new Talk();
        t.setFormat(FORMAT.LAB);
        assertThat(ProgrammeHelper.TALK_ABOUT_TOOLS_IN_ACTION.apply(t)).isFalse();
    }

}
