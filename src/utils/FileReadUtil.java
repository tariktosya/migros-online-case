package utils;

import model.Store;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileReadUtil {

    private static final String REGEX = "},\\s*\\{";
    private static final String FORMAT_STR = "^[{]|[}]$";
    private static final String NAME = "name";
    private static final String LAT = "lat";
    private static final String LNG = "lng";
    private FileReadUtil() {}

    public static List<Store> parseStores(File file) throws IOException {
        List<Store> stores = new ArrayList<>();
        String content = new String(Files.readAllBytes(file.toPath())).trim();

        if (content.startsWith("[")) content = content.substring(1);
        if (content.endsWith("]")) content = content.substring(0, content.length() - 1);

        String[] objects = content.split(REGEX);

        for (String object : objects) {

            String jsonStr = stringToJsonString(object);
            String name = JsonUtil.getJsonStringRequired(jsonStr, NAME);
            double lat = JsonUtil.getJsonDoubleRequired(jsonStr, LAT);
            double lng = JsonUtil.getJsonDoubleRequired(jsonStr, LNG);

            stores.add(new Store(name, lat, lng));
        }

        return stores;
    }

    private static String stringToJsonString(String str) {
        String cleanStr = str.replaceAll(FORMAT_STR, "");
        return "{" + cleanStr + "}";
    }
}
