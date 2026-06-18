// src/main/java/com/samsungfire/qa/VulnController.java
// critical-java-rce 전용 — 트림된 pom(spring/log4j/struts)에서 컴파일되는 sink만 사용.
package com.samsungfire.qa;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@RestController
public class VulnController {
    private static final Logger log = LogManager.getLogger(VulnController.class);

    // CWE-78: OS command injection
    @GetMapping("/ping")
    public String ping(@RequestParam String host) throws IOException {
        Runtime.getRuntime().exec(new String[]{"sh", "-c", "ping -c1 " + host});
        return "ok";
    }

    // CWE-117 / Log4Shell 표면: 신뢰 불가 입력 로깅
    @GetMapping("/login")
    public String login(@RequestParam String user) {
        log.info("login attempt: " + user);
        return "ok";
    }
}
