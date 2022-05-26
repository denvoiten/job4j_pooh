package ru.job4j.pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueService implements Service {
    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queues =
            new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        Resp resp;
        String param = req.getParam();
        if ("POST".equals(req.httpRequestType())) {
            queues.putIfAbsent(req.getSourceName(), new ConcurrentLinkedQueue<>());
            queues.get(req.getSourceName()).offer(param);
            resp = param.isEmpty() ? new Resp(param, "200 Ok") : new Resp(param, "201 Created");
        } else if ("GET".equals(req.httpRequestType())) {
            String text = queues.getOrDefault(req.getSourceName(), new ConcurrentLinkedQueue<>()).poll();
            resp = text == null || text.isEmpty() ? new Resp("", "204 No Content") : new Resp(text, "200 Ok");
        } else {
            resp = new Resp(req.httpRequestType(), "501 Not Implemented");
        }
        return resp;
    }
}

