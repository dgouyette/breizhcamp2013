package com.societe.blog.services;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.societe.blog.domain.FORMAT;
import com.societe.blog.domain.Talk;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;
import java.util.List;

@Component
public class HomeMadeTalkService extends AbstractTalkService implements TalkService {

    @Override
    public Collection<Talk> findTalksToolsInAction() {
        Collection<Talk> allTalks = findTalks();
        List<Talk> toolsInAction = Lists.newArrayList();
        int i = 0;
        for (Talk talk : allTalks) {
            if (talk.getFormat() == FORMAT.TOOLS_IN_ACTION) {
                toolsInAction.add(talk);
            }
        }
        return toolsInAction;
    }

    @Override
    public Collection<Talk> findTalksNotToolsInAction() {
        Collection<Talk> allTalks = findTalks();
        List<Talk> notToolsInAction = Lists.newArrayList();
        for (Talk talk : allTalks) {
            if (talk.getFormat() != FORMAT.TOOLS_IN_ACTION) {
                notToolsInAction.add(talk);
            }
        }
        return notToolsInAction;

    }

    @Override
    public Collection<Talk> findTalksAboutLabs() {
        Collection<Talk> allTalks = findTalks();
        List<Talk> notToolsInAction = Lists.newArrayList();
        for (Talk talk : allTalks) {
            if (talk.getFormat() == FORMAT.LAB || talk.getFormat() == FORMAT.BIGLAB) {
                notToolsInAction.add(talk);
            }
        }
        return notToolsInAction;

    }

    @Override
    public Optional<Talk> findById(long talkId) throws IOException, ParseException {
        Collection<Talk> talks = findTalks();
        for (Talk talk : talks) {
            if (talk.getId() == talkId) {
                return Optional.of(talk);
            } else {
                return Optional.absent();
            }
        }
        return Optional.absent();
    }


}
