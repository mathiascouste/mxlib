package eu.couste.common.network;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;


public class ImageDownloader {

    private static Map<String, String> map = new HashMap<String, String>();
    private static String target;

    public static String getGeneratedPath(String url) throws MalformedURLException, IOException {
        String path = map.get(url);
        if (path == null) {
            downloadImage(url);
            return map.get(url);
        }
        return path;

    }

    private static void downloadImage(String url) throws MalformedURLException, IOException {
        BufferedImage image = null;
        image = ImageIO.read(new URL(url));
        String path = target + "img" + map.size() + ".png";
        File outputfile = new File(path);
        ImageIO.write(image, "png", outputfile);

        map.put(url, path);
    }

    public static String getTarget() {
        return target;
    }

    public static void setTarget(String target) {
        ImageDownloader.target = target;
    }

    public static void createDirectory() {
        File f = new File(target);
        if (!f.exists()) {
            f.mkdirs();
        }
    }

}
