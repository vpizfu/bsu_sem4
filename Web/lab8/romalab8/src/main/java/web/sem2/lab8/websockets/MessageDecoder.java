package web.sem2.lab8.websockets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessageDecoder implements Decoder.Text<Message> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

    private static final Gson GSON = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
            (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) ->
                    FORMATTER.parse(json.getAsString(), LocalDateTime::from)).create();

    @Override
    public Message decode(String s) throws DecodeException {
        return GSON.fromJson(s, Message.class);
    }

    @Override
    public boolean willDecode(String s) {
        return s != null;
    }

    @Override
    public void init(EndpointConfig config) { }

    @Override
    public void destroy() { }
}
