package com.github.bruce_mig.webflux_video_streaming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class StreamingController {

    Logger log = LoggerFactory.getLogger(StreamingController.class);

    private final StreamingService service;

    public StreamingController(StreamingService service) {
        this.service = service;
    }

    @GetMapping(value = "video/{title}", produces = "video/mp4")
    public Mono<Resource> getVideosStream(
            @PathVariable String title,
            @RequestHeader("Range") String range
    ) {
        log.info("range in bytes : {}", range);
        return service.getVideo(title);
    }
}
