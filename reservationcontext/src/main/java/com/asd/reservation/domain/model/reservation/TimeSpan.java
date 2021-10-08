package java.com.asd.reservation.domain.model.reservation;

import java.time.LocalDateTime;

public class TimeSpan {
    private final LocalDateTime begin;
    private final LocalDateTime end;

    public TimeSpan(LocalDateTime begin, LocalDateTime end) {
        this.begin = begin;
        this.end = end;
    }

    public LocalDateTime getBegin() {
        return begin;
    }

    public LocalDateTime getEnd() {
        return end;
    }
}
