package com.societe.blog.services;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.societe.blog.domain.Talk;
import com.societe.blog.utils.ProgrammeHelper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;

import static com.google.common.base.Predicates.not;
import static com.google.common.base.Predicates.or;
import static com.google.common.collect.Collections2.filter;

@Component
public class FunctionalTalkService extends AbstractTalkService implements TalkService {


    @Override
    public Collection<Talk> findTalksToolsInAction() throws IOException, ParseException {
        return filter(findTalks(), ProgrammeHelper.TALK_ABOUT_TOOLS_IN_ACTION);

    }

    @Override
    public Collection<Talk> findTalksNotToolsInAction() throws IOException, ParseException {
        return filter(findTalks(), not(ProgrammeHelper.TALK_ABOUT_TOOLS_IN_ACTION));
    }

    @Override
    public Collection<Talk> findTalksAboutLabs() throws IOException, ParseException {
        return filter(findTalks(), or(ProgrammeHelper.BIGLAB, ProgrammeHelper.LAB));
    }

    @Override
    public Optional<Talk> findById(final long programmeId) throws IOException, ParseException {

        Predicate<Talk> predicate_by_id = new Predicate<Talk>() {
            @Override
            public boolean apply(Talk talk) {
                return (talk.getId() == programmeId);
            }
        };


        Collection<Talk> filter = filter(findTalks(), predicate_by_id);

        if (filter.size() == 1) {
            return Optional.of(Lists.newArrayList(filter).get(0));
        } else {
            return Optional.absent();
        }

    }




}
