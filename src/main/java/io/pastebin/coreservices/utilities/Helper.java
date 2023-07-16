package io.pastebin.coreservices.utilities;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class Helper {
    public static Map<TimeUnit, Integer> timeUnitEnumValues = new HashMap<>();

    @PostConstruct
    public static void populateTimeUnitValues() {
        timeUnitEnumValues.put(TimeUnit.MINUTES, 1);
        timeUnitEnumValues.put(TimeUnit.HOURS, 60);
        timeUnitEnumValues.put(TimeUnit.DAYS, 1440);
        timeUnitEnumValues.put(TimeUnit.WEEKS, 10080);
        timeUnitEnumValues.put(TimeUnit.MONTHS, 43840);
        timeUnitEnumValues.put(TimeUnit.YEARS, 527040);

    }
}
