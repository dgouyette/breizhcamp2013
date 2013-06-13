package com.societe.blog.utils;

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

    public static Talk extractTalkFromJsonObject(String date, JsonElement jsonElement) throws ParseException {
        JsonObject jsonTalkObject = jsonElement.getAsJsonObject();
        String time = jsonTalkObject.get("time").getAsString();
        String title = jsonTalkObject.get("title").getAsString();
        String room = jsonTalkObject.get("room").getAsString();
        String format = jsonTalkObject.get("format").getAsString();
        return new Talk(ROOM.fromString(room), FORMAT.fromString(format), title, String.format("%s %s", date, time));
    }

    public static List<Talk> extractTalksFromJson(Resource programmeResource) throws IOException, ParseException {
        JsonObject jsonObject = loadProgramme(programmeResource);
        List<Talk> talks = Lists.newArrayList();
        JsonObject programme = jsonObject.getAsJsonObject("programme");
        JsonArray jours = programme.getAsJsonArray("jours");


        for (JsonElement jour : jours) {
            JsonObject jourAsJsonObject = jour.getAsJsonObject();
            String date = jourAsJsonObject.get("date").getAsString();
            JsonArray tracks = jourAsJsonObject.getAsJsonArray("tracks");
            for (JsonElement track : tracks) {
                JsonArray jsonTalks = track.getAsJsonObject().getAsJsonArray("talks");

                for (JsonElement jsonTalkElement : jsonTalks) {
                    talks.add(extractTalkFromJsonObject(date, jsonTalkElement));
                }
            }
        }
        return talks;
    }
}
