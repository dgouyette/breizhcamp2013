package com.societe.blog.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.societe.blog.domain.FORMAT;
import com.societe.blog.domain.ROOM;
import com.societe.blog.domain.Talk;
import org.junit.Test;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class JsonHelperTest {


    private static final String EXPECTED_TALK_TITLE = "Programmation fonctionnelle en java ? Si, si";

    private static final String EXPECTED_TALK_URL = "http://cfp.breizhcamp.org/#/talks/see/4";

    @Test
    public void should_map_jsonObject_to_talk() {

        /**
         {
         "id": 62,
         "time": "17:30",
         "format": "tools in action",
         "url": "http://cfp.breizhcamp.org/#/talks/see/62",
         "title": "Programmation fonctionnelle en java ? Si, si",
         "room": "Ouessant"
         }
         */


        Talk talk = JsonHelper.extractTalkFromJsonObject.apply(makeJsonObjectTalk());

        assertThat(talk.getFormat()).isEqualTo(FORMAT.TOOLS_IN_ACTION);
        assertThat(talk.getRoom()).isEqualTo(ROOM.OUESSANT);
        assertThat(talk.getUrl()).isEqualTo(EXPECTED_TALK_URL);
        assertThat(talk.getTitle()).isEqualTo(EXPECTED_TALK_TITLE);

    }

    @Test
    public void should_transform_jsonArray_to_list() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(makeJsonObjectTalk());

        List<JsonObject> jsonObjects = JsonHelper.jsonArrayToListJsonObject.apply(jsonArray);

        assertThat(jsonObjects.get(0)).isEqualTo(makeJsonObjectTalk());
    }

    private JsonObject makeJsonObjectTalk() {
        JsonObject jsonObject = new JsonObject();

        jsonObject.add("id", new JsonPrimitive(62));
        jsonObject.add("format", new JsonPrimitive("tools in action"));
        jsonObject.add("room", new JsonPrimitive("Ouessant"));
        jsonObject.add("url", new JsonPrimitive(EXPECTED_TALK_URL));
        jsonObject.add("title", new JsonPrimitive(EXPECTED_TALK_TITLE));
        return jsonObject;
    }

}
