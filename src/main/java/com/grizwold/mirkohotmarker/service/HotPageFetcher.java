package com.grizwold.mirkohotmarker.service;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class HotPageFetcher {
    private final String baseUrl = "https://www.wykop.pl/mikroblog/hot/ostatnie/%s/strona/%s/";

    @SneakyThrows
    public Document getHotPage(int page, int hours) {
        return Jsoup.connect(getBaseUrl(page, hours)).get();
    }

    private String getBaseUrl(int page, int hours) {
        return String.format(baseUrl, hours, page);
    }
}
