// javaapp/src/main/java/com/samsungfire/qa/App.java
// critical-java-rce 브랜치 전용 — Log4Shell 도달 경로 + command injection.
package com.samsungfire.qa;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;

public class App {
    private static final Logger log = LogManager.getLogger(App.class);

    // CVE-2021-44228 Log4Shell — User-Agent 등 외부 입력을 그대로 로깅
    public void handle(String userAgent) {
        log.info("UA=" + userAgent);
    }

    // CWE-78: OS command injection
    public Process run(String cmd) throws IOException {
        return Runtime.getRuntime().exec(new String[]{"sh", "-c", cmd});
    }
}
