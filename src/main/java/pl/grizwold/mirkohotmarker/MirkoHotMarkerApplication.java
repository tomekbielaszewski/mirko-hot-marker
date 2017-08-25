package pl.grizwold.mirkohotmarker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.grizwold.mirkohotmarker.config.Configuration;
import pl.grizwold.mirkohotmarker.config.ConfigurationParser;
import pl.grizwold.mirkohotmarker.repo.EntryRepository;
import pl.grizwold.mirkohotmarker.service.HotPageFetcher;
import pl.grizwold.mirkohotmarker.service.HotPageParser;

import java.util.stream.IntStream;

@SpringBootApplication
public class MirkoHotMarkerApplication {
    @Autowired
    private HotPageFetcher hotPageFetcher;

    @Autowired
    private HotPageParser hotPageParser;

    @Autowired
    private EntryRepository entryRepository;

    public static void main(String[] args) {
        Configuration configuration = new ConfigurationParser().parse(args);
        ConfigurableApplicationContext context = SpringApplication.run(MirkoHotMarkerApplication.class, args);

        context.getBean(MirkoHotMarkerApplication.class).run(configuration);
    }

    private void run(Configuration configuration) {
        IntStream.range(1, 11)
                .boxed()
                .parallel()
                .map(page -> hotPageFetcher.getHotPage(page, configuration.getHour()))
                .flatMap(hotPage -> hotPageParser.getEntryIds(hotPage).stream())
                .forEach(id -> entryRepository.markAsHot(id, configuration.getHour()));
    }
}
