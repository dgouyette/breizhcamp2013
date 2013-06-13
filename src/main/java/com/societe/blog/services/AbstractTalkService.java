package com.societe.blog.services;

import com.google.common.base.Optional;
import com.societe.blog.dao.TalkDao;
import com.societe.blog.domain.Talk;
import fj.data.Either;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;

public abstract class AbstractTalkService {


    @Autowired
    protected TalkDao talkDao;

    public Collection<Talk> findTalks() {
        Collection<Talk> all = talkDao.findAll();
        return all;
    }

    public Either<String, Void> removeById(long id) {
        Optional<Talk> talkOptional = talkDao.findById(id);
        if (talkOptional.isPresent()) {
            talkDao.remove(talkOptional.get());
            return Either.right(null);
        } else {
            return Either.left("");
        }
    }

}
