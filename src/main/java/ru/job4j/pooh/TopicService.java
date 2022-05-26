package ru.job4j.pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TopicService implements Service {
    private final ConcurrentHashMap<String, ConcurrentHashMap<String, ConcurrentLinkedQueue<String>>> topics =
            new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        Resp resp;
        var param = req.getParam();
        var sourceName = req.getSourceName();
        if ("POST".equals(req.httpRequestType())) {
            var topic = topics.get(sourceName);
            if (topic != null) {
                for (var subscriber : topic.values()) {
                    subscriber.offer(param);
                }
            }
            resp = new Resp("", "200 Ok");
        } else if ("GET".equals(req.httpRequestType())) {
            topics.putIfAbsent(sourceName, new ConcurrentHashMap<>());
            topics.get(sourceName).putIfAbsent(param, new ConcurrentLinkedQueue<>());
            String text = topics.get(sourceName).get(param).poll();
            resp = text == null ? new Resp("", "204 No Content") : new Resp(text, "200 Ok");
        } else {
            resp = new Resp(req.httpRequestType(), "501 Not Implemented");
        }
        return resp;
    }
}