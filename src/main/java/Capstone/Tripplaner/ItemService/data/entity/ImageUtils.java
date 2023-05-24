package Capstone.Tripplaner.ItemService.data.entity;

import java.util.Base64;

public class ImageUtils {
    public static byte[] decodeBase64(String base64String) {
        return Base64.getDecoder().decode(base64String);
    }
}
