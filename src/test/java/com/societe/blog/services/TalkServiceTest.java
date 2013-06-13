package com.societe.blog.services;

import com.societe.blog.domain.Talk;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;

import static org.fest.assertions.Assertions.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext.xml"})
public class TalkServiceTest {


    public static final int EXPECTED_TALKS_COUNT = 67;

    public static final int EXPECTED_TOOLS_IN_ACTION_COUNT = 18;

    private static final int EXPECTED_OTHER_THAN_TOOLS_IN_ACTION_COUNT = EXPECTED_TALKS_COUNT - EXPECTED_TOOLS_IN_ACTION_COUNT;

    private static final int EXPECTED_TALKS_LAB_COUNT = 5;

    @Autowired
    @Qualifier("functionalProgrammeService")
    private TalkService talkService;

    @Test
    public void should_find_all_talks() throws Exception {
        Collection<Talk> allTalks = talkService.findTalks();
        assertThat(allTalks).hasSize(EXPECTED_TALKS_COUNT);
    }

    @Test
    public void should_find_all_talk_tools_in_action() throws IOException, ParseException {
        Collection<Talk> allTalks = talkService.findTalksToolsInAction();
        assertThat(allTalks).hasSize(EXPECTED_TOOLS_IN_ACTION_COUNT);
    }

    @Test
    public void should_find_all_talk_not_tools_in_action() throws IOException, ParseException {
        Collection<Talk> allTalks = talkService.findTalksNotToolsInAction();
        assertThat(allTalks).hasSize(EXPECTED_OTHER_THAN_TOOLS_IN_ACTION_COUNT);
    }

    @Test
    public void should_find_all_talk_about_lab() throws IOException, ParseException {
        Collection<Talk> allTalks = talkService.findTalksAboutLabs();
        assertThat(allTalks).hasSize(EXPECTED_TALKS_LAB_COUNT);
    }


}
