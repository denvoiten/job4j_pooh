package ru.job4j.pooh;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TopicServiceTest {
    private final TopicService topicService = new TopicService();

    @Test
    public void whenTopic() {
        String paramForPublisher = "temperature=18";
        String paramForSubscriber1 = "client407";
        String paramForSubscriber2 = "client6565";
        topicService.process(
                new Req("GET", "topic", "weather", paramForSubscriber1)
        );
        topicService.process(
                new Req("POST", "topic", "weather", paramForPublisher)
        );
        Resp result1 = topicService.process(
                new Req("GET", "topic", "weather", paramForSubscriber1)
        );
        Resp result2 = topicService.process(
                new Req("GET", "topic", "weather", paramForSubscriber2)
        );
        assertThat(result1.text(), is("temperature=18"));
        assertThat(result2.text(), is(""));
    }

    @Test
    public void whenNewSubAndPostThenGet() {
        String paramForSubscriber1 = "client407";
        String paramForSubscriber2 = "client6565";
        topicService.process(
                new Req("GET", "topic", "weather", paramForSubscriber1)
        );
        topicService.process(
                new Req("POST", "topic", "weather", "temperature=18")
        );
        Resp result = topicService.process(
                new Req("GET", "topic", "weather", paramForSubscriber2)
        );
        assertThat(result.text(), is(""));
        topicService.process(
                new Req("POST", "topic", "weather", "temperature=25")
        );
        Resp result2 = topicService.process(
                new Req("GET", "topic", "weather", paramForSubscriber2)
        );
        assertThat(result2.text(), is("temperature=25"));
        assertThat(result2.status(), is("200 Ok"));
        Resp result3 = topicService.process(
                new Req("GET", "topic", "weather", paramForSubscriber2)
        );
        assertThat(result3.text(), is(""));
        assertThat(result3.status(), is("204 No Content"));
    }

    @Test
    public void henUnknownReq() {
        String paramForSubscriber1 = "client407";
        topicService.process(
                new Req("GET", "topic", "weather", paramForSubscriber1)
        );
        Resp result = topicService.process(
                new Req("POOST", "topic", "weather", "temperature=18")
        );
        Resp result1 = topicService.process(
                new Req("GET", "topic", "weather", paramForSubscriber1)
        );
        assertThat(result.status(), is("501 Not Implemented"));
        assertThat(result1.text(), is(""));
    }
}