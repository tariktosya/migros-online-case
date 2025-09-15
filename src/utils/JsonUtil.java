package utils;

import java.util.Optional;

public class JsonUtil {

    private static final String PATTERN_STR = "\"\\s*:\\s*\"?([^\"]+?)\"?([,}])";

    private JsonUtil() {}

    public static String getJsonStringRequired(String json, String key) {
        return Optional.ofNullable(getJsonValue(json, key))
                .orElseThrow(() -> new IllegalArgumentException("required field : " + key));
    }

    public static Double getJsonDoubleRequired(String json, String key) {
        return Optional.ofNullable(getJsonValue(json, key))
                .map(Double::parseDouble)
                .orElseThrow(() -> new IllegalArgumentException("required field : " + key));
    }

    public static Long getJsonLongRequired(String json, String key) {
        return Optional.ofNullable(getJsonValue(json, key))
                .map(Long::parseLong)
                .orElseThrow(() -> new IllegalArgumentException("required field : " + key));
    }

    private static String getJsonValue(String json, String key) {
        String pattern = "\"" + key + PATTERN_STR;
        var matcher = java.util.regex.Pattern.compile(pattern).matcher(json);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return null;
    }
}
