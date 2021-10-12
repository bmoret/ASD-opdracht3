package main.java.com.asd.session.domain.model.session;

import java.time.LocalDateTime;

public class TimeSpan {
    private final LocalDateTime begin;
    private final LocalDateTime end;

    public TimeSpan(LocalDateTime begin, LocalDateTime end) {
        this.begin = begin;
        this.end = end;
    }

    //checkt of de start tijd na de huidige tijd ligt
    public boolean timeSpanBeginAfterNow() {
        return this.begin.isAfter(LocalDateTime.now());
    }

    public LocalDateTime getBegin() {
        return begin;
    }

    public LocalDateTime getEnd() {
        return end;
    }
}
