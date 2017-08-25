package pl.grizwold.mirkohotmarker.service;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotPageParser {
    public List<Long> getEntryIds(Document mirkoPage) {
        return mirkoPage.body()
                .select("#itemsStream > li > div")
                .stream()
                .map(entry -> entry.attr("data-id"))
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }
}
