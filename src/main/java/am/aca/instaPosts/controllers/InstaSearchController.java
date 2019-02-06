package am.aca.instaPosts.controllers;

import am.aca.instaPosts.components.RequestSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;


@RestController
public class InstaSearchController {
    private static Map<String, String> urls;


    @GetMapping("/")
    public ModelAndView homePageGet() {
        return new ModelAndView("home.html");
    }

    @GetMapping("/instagram")
    public ModelAndView instaGet(HttpServletRequest req) throws IOException {

        String searchWord = req.getParameter("searchWord");


        urls = RequestSender.test(searchWord);

        return new ModelAndView("images.html");
    }

    @GetMapping("/instagram/urls")
    public Set<String> instaUrls() {

        return urls.keySet();
    }

    @GetMapping("/instagram/locations")
    public Collection<String> instaLocations() {

        return urls.values();
    }


}
