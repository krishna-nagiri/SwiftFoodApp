package main.com.infy.data;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeAdapter implements JsonSerializer<LocalTime>, JsonDeserializer<LocalTime> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    @Override
    public JsonElement serialize(LocalTime src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(formatter.format(src));
    }

    @Override
    public LocalTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            // Handle "HH:mm" string format (expected)
            if (json.isJsonPrimitive()) {
                return LocalTime.parse(json.getAsString(), formatter);
            }

            // Handle {"hour": 10, "minute": 30} legacy format
            if (json.isJsonObject()) {
                JsonObject obj = json.getAsJsonObject();
                int hour = obj.get("hour").getAsInt();
                int minute = obj.get("minute").getAsInt();
                return LocalTime.of(hour, minute);
            }

            throw new JsonParseException("Invalid format for LocalTime: " + json.toString());
        } catch (Exception e) {
            throw new JsonParseException("Failed to deserialize LocalTime: " + json.toString(), e);
        }
    }
}
