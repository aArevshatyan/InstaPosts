package am.aca.instaPosts.components;


import me.postaddict.instagram.scraper.Instagram;
import me.postaddict.instagram.scraper.cookie.CookieHashSet;
import me.postaddict.instagram.scraper.cookie.DefaultCookieJar;
import me.postaddict.instagram.scraper.interceptor.ErrorInterceptor;
import me.postaddict.instagram.scraper.model.Location;
import me.postaddict.instagram.scraper.model.Media;
import me.postaddict.instagram.scraper.model.Tag;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class RequestSender {

    public static Map<String, String> test(String searchWord) throws IOException {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(loggingInterceptor)
                .addInterceptor(new ErrorInterceptor())
                .cookieJar(new DefaultCookieJar(new CookieHashSet()))
                .build();
        Instagram instagram = new Instagram(httpClient);

        Tag searchTag = instagram.getMediasByTag(searchWord, 1);
        Map<String, String> urls = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            Media media = searchTag.getMediaRating().getMedia().getNodes().get(i);
            Location location = media.getLocation();
            urls.put(media.getDisplayUrl(), (location == null)? "" : "[" + location.getLat().toString() + "," + location.getLng().toString() + "]");
        }
        return urls;
    }

}




