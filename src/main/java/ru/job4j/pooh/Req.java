package ru.job4j.pooh;

public class Req {

    private final String httpRequestType;
    private final String poohMode;
    private final String sourceName;
    private final String param;

    public Req(String httpRequestType, String poohMode, String sourceName, String param) {
        this.httpRequestType = httpRequestType;
        this.poohMode = poohMode;
        this.sourceName = sourceName;
        this.param = param;
    }

    public static Req of(String content) {
        String[] lines = content.split(System.lineSeparator());
        String[] firstLine = lines[0].split(" /|/| ");
        String httpRequestType = firstLine[0];
        String poohMode = firstLine[1];
        String sourceName = firstLine[2];
        String param = "";
        if ("POST".equals(httpRequestType)) {
            param = lines[7];
        } else if ("GET".equals(httpRequestType) && "topic".equals(poohMode)) {
            param = firstLine[3];
        }
        return new Req(httpRequestType, poohMode, sourceName, param);
    }

    public String httpRequestType() {
        return httpRequestType;
    }

    public String getPoohMode() {
        return poohMode;
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getParam() {
        return param;
    }
}
