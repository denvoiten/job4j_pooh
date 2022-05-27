package ru.job4j.pooh;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class QueueServiceTest {
    private final QueueService queueService = new QueueService();

    @Test
    public void whenPostThenGetQueue() {
        String paramForPostMethod = "temperature=18";
        Resp process = queueService.process(
                new Req("POST", "queue", "weather", paramForPostMethod)
        );
        Resp result = queueService.process(
                new Req("GET", "queue", "weather", null)
        );
        assertThat(process.status(), is("201 Created"));
        assertThat(result.text(), is("temperature=18"));
    }

    @Test
    public void whenUnknownReq() {
        Resp process = queueService.process(
                new Req("POSST", "queue", "weather", "temperature=18")
        );
        Resp result = queueService.process(
                new Req("GET", "queue", "weather", null)
        );
        assertThat(process.text(), is("POSST"));
        assertThat(process.status(), is("501 Not Implemented"));
        assertThat(result.status(), is("204 No Content"));
        assertThat(result.text(), is(""));
    }
}