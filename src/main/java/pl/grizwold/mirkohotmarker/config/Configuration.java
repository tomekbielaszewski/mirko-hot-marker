package pl.grizwold.mirkohotmarker.config;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.validators.PositiveInteger;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Configuration {
    @Parameter(names = {"--hot-hours", "-hot"}, description = "Fetch entries from Mirko Hot of given hours", validateWith = PositiveInteger.class, required = true, order = 0)
    private int hour;
}
