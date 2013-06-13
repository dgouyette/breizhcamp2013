package com.societe.blog.services;

import com.google.common.base.Optional;
import com.societe.blog.domain.Talk;
import fj.data.Either;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;

public interface TalkService {

    Collection<Talk> findTalks() throws IOException, ParseException;

    Collection<Talk> findTalksToolsInAction() throws IOException, ParseException;

    Collection<Talk> findTalksNotToolsInAction() throws IOException, ParseException;

    Collection<Talk> findTalksAboutLabs() throws IOException, ParseException;

    Optional<Talk> findById(long programmeId) throws IOException, ParseException;

    Either<String, Void> removeById(long id);

}
