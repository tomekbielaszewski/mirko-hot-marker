package pl.grizwold.mirkohotmarker.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HotPageFetcher {
    private final String baseUrl = "https://www.wykop.pl/mikroblog/hot/ostatnie/%s/strona/%s/";

    @SneakyThrows
    public Document getHotPage(int page, int hours) {
        log.info("Getting #{} {}h hot page", page, hours);
        return Jsoup.connect(getBaseUrl(page, hours)).get();
    }

    private String getBaseUrl(int page, int hours) {
        return String.format(baseUrl, hours, page);
    }
}
