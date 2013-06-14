package com.societe.blog.services;

import com.google.common.base.Optional;
import com.societe.blog.domain.Talk;
import com.societe.blog.utils.TalkHelper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;

import static com.google.common.base.Predicates.not;
import static com.google.common.base.Predicates.or;
import static com.google.common.collect.Collections2.filter;

@Component
public class FunctionalTalkService extends AbstractTalkService implements TalkService {


    //passe plat
    @Override
    public Optional<Talk> findById(final long talkId) throws IOException, ParseException {
        return talkDao.findById(talkId);
    }

    @Override
    public Collection<Talk> findTalksToolsInAction() throws IOException, ParseException {
        return filter(findTalks(), TalkHelper.TALK_ABOUT_TOOLS_IN_ACTION);

    }

    @Override
    public Collection<Talk> findTalksNotToolsInAction() throws IOException, ParseException {
        return filter(findTalks(), not(TalkHelper.TALK_ABOUT_TOOLS_IN_ACTION));
    }

    @Override
    public Collection<Talk> findTalksAboutLabs() throws IOException, ParseException {
        return filter(findTalks(), or(TalkHelper.BIGLAB, TalkHelper.LAB));
    }


}
