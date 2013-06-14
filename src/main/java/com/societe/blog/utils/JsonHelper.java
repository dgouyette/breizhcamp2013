package com.societe.blog.utils;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.societe.blog.domain.FORMAT;
import com.societe.blog.domain.ROOM;
import com.societe.blog.domain.Talk;
import org.h2.util.IOUtils;
import org.springframework.core.io.Resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public final class JsonHelper {

    public static Function<JsonArray, List<JsonObject>> jsonArrayToListJsonObject = new Function<JsonArray, List<JsonObject>>() {
        @Override
        public List<JsonObject> apply(JsonArray jsonElements) {
            List<JsonObject> jsonObjectTalks = Lists.newArrayList();
            for (JsonElement jsonElement : jsonElements) {
                jsonObjectTalks.add(jsonElement.getAsJsonObject());
            }
            return jsonObjectTalks;
        }
    };


    public static Function<JsonObject, Talk> extractTalkFromJsonObject = new Function<JsonObject, Talk>() {
        @Override
        public Talk apply(JsonObject jsonTalkObject) {
            String title = jsonTalkObject.get("title").getAsString();
            String room = jsonTalkObject.get("room").getAsString();
            String format = jsonTalkObject.get("format").getAsString();
            String url = jsonTalkObject.get("url").getAsString();
            return new Talk(ROOM.fromString(room), FORMAT.fromString(format), title, url);
        }
    };




    private JsonHelper() {
    }

    public static String stringOfUrl(Resource resource) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        IOUtils.copy(resource.getURL().openStream(), output);
        return output.toString();
    }

    public static JsonObject loadProgramme(Resource programmeResource) throws IOException {
        String json = stringOfUrl(programmeResource);
        JsonObject jobj = new Gson().fromJson(json, JsonObject.class);
        return jobj;

    }

    public static List<Talk> extractTalksFromJson(Resource programmeResource) throws IOException, ParseException {
        JsonObject jsonObject = loadProgramme(programmeResource);
        List<Talk> talks = Lists.newArrayList();
        JsonObject programme = jsonObject.getAsJsonObject("programme");
        JsonArray jours = programme.getAsJsonArray("jours");


        for (JsonElement jour : jours) {
            JsonObject jourAsJsonObject = jour.getAsJsonObject();
            JsonArray tracks = jourAsJsonObject.getAsJsonArray("tracks");
            for (JsonElement track : tracks) {
                JsonArray jsonTalks = track.getAsJsonObject().getAsJsonArray("talks");


                List<JsonObject> talksAsJsonObjects = jsonArrayToListJsonObject.apply(jsonTalks);
                List<Talk> list = Lists.transform(talksAsJsonObjects, extractTalkFromJsonObject);


                talks.addAll(list);
                //talks.addAll(transform(jsonArrayToListJsonObject.apply(jsonTalks), extractTalkFromJsonObject));



            }
        }
        return talks;
    }
}
