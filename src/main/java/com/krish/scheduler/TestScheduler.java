package com.krish.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@EnableScheduling
public class TestScheduler {
    Logger log = LoggerFactory.getLogger(TestScheduler.class);

    //@Scheduled(cron="0 0/01 18-19 * * *") // runs between 18 and 19 for every one min
    @Scheduled(cron = "0 0/30 15 * * *")// runs at 3:30 PM
    public void scheduleCronTask() {
        log.info("CRON TASK 1", System.currentTimeMillis() / 1000);
    }

    @Scheduled(cron = "0 0 3 * * *") // runs every day at 3AM once daily
    public void scheduleCronTask2() {
        log.info("CRON TASK 2", System.currentTimeMillis() / 1000);
    }

    //@Scheduled(fixedRate = 1000)
    public void fixedRateSch() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        Date now = new Date();
        String strDate = sdf.format(now);
        log.info("Fixed Rate scheduler:: ", strDate);
    }

//	    These are valid formats for cron expressions:
// second, minute, hour, day, month, weekday
// https://www.freeformatter.com/cron-expression-generator-quartz.html
// 0 0 0 ? * * * >>> At 00:00:00am every day
// 0 0/01 13-16 * * * >> this runs between 1 pm to 4 pm ..every one min...

// 0 0 * * * * = the top of every hour of every day.
// */10 * * * * * = every ten seconds.
// 0 0 8-10 * * * = 8, 9 and 10 o'clock of every day.
// 0 0 6,19 * * * = 6:00 AM and 7:00 PM every day.
// 0 0/30 8-10 * * * = 8:00, 8:30, 9:00, 9:30, 10:00 and 10:30 every day.
// 0 0 9-17 * * MON-FRI = on the hour nine-to-five weekdays
// 0 0 0 25 12 ? = every Christmas Day at midnight
// The pattern is:

//second, minute, hour, day, month, weekday

}
