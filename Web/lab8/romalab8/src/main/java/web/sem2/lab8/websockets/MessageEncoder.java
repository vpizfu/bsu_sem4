package web.sem2.lab8.websockets;

import com.google.gson.*;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessageEncoder implements Encoder.Text<Message> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

    private static final Gson GSON = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
            (JsonSerializer<LocalDateTime>) (time, type, jsonSerializationContext) ->
                    new JsonPrimitive(FORMATTER.format(time))).create();

    @Override
    public String encode(Message object) throws EncodeException {
        return GSON.toJson(object);
    }

    @Override
    public void init(EndpointConfig config) { }

    @Override
    public void destroy() { }
}
