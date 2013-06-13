package com.societe.blog.resources;


import com.google.common.base.Optional;
import com.societe.blog.domain.Talk;
import com.societe.blog.services.TalkService;
import fj.data.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;

@Component
@Path("/talk")
public class TalkResource {


    @Autowired
    @Qualifier("functionalTalkService")
    private TalkService talkService;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findAll() throws IOException, ParseException {
        Collection<Talk> talks = talkService.findTalks();
        return Response.ok(talks).build();
    }

    @Path("{talkId}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findTalkById(@PathParam("talkId") long talkId) throws IOException, ParseException {
        Optional<Talk> optionalTalk = talkService.findById(talkId);
        if (optionalTalk.isPresent()) {
            return Response.ok(optionalTalk.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{talkId}")
    public Response deleteById(@PathParam("talkId") long talkId) {
        Either<String, Void> result = talkService.removeById(talkId);
        if (result.isRight()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


}
