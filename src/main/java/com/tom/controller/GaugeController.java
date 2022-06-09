package com.tom.controller;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Gauge;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GaugeController {
    private final Gauge demoMetric;

    public GaugeController(CollectorRegistry collectorRegistry) {
        demoMetric = Gauge.build()
                .name("demo_metric")
                .help("demo metric")
                .register(collectorRegistry);
    }

    @GetMapping(value = "/push")
    public String push() {
        demoMetric.inc();

        return "You pushed an item to the queue!";
    }

    @GetMapping(value = "/start")
    public void loopMethod() throws InterruptedException {
        System.out.println("Started loopMethod");
        int i = 1000;
        while(i >= 0) {
            Thread.sleep(1000);
            double random = Math.random() * 10;
            demoMetric.inc(random); // 0 -> 12.0 -> 82.0 -> 0 -> 2.0
            i--;
        }
    }
}
