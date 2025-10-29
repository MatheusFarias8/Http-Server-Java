package com.matheus.httpserver.config.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

public class Json {

    private static final ObjectMapper objectMapper = defaultObjectMapper();

    private static ObjectMapper defaultObjectMapper() {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return om;
    }

    public static JsonNode parse(String jsonSrc) throws JsonProcessingException {

        return objectMapper.readTree(jsonSrc);
    }

    public static <A> A fromJson(JsonNode node, Class<A> aClass) throws JsonProcessingException {

        return objectMapper.treeToValue(node, aClass);
    }

    public static JsonNode toJson(Object object) {

        return objectMapper.valueToTree(object);
    }

    public static String stringify(JsonNode jsonNode) throws JsonProcessingException {

        return generateJson(jsonNode, false);
    }

    public static String stringifyPretty(JsonNode jsonNode) throws JsonProcessingException {

        return generateJson(jsonNode, true);
    }

    private static String generateJson(Object object, boolean pretty) throws JsonProcessingException {
        ObjectWriter objectWriter = objectMapper.writer();
        if (pretty) {
            objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);
        }

        return objectWriter.writeValueAsString(object);
    }

}
