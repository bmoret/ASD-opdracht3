package java.com.asd.reservation.domain.model.reservation;

import java.time.LocalDateTime;
import java.util.Objects;

public class TimeSpan {
    private final LocalDateTime begin;
    private final LocalDateTime end;

    public TimeSpan(LocalDateTime begin, LocalDateTime end) {
        this.begin = begin;
        this.end = end;
    }

    public Boolean hasOverlap(TimeSpan otherTimeSpan) {
        if (!this.getBegin().isAfter(otherTimeSpan.getEnd()) && !this.getEnd().isBefore(otherTimeSpan.getBegin())) {
            return true;
        }
        return false;
    }

    public LocalDateTime getBegin() {
        return begin;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeSpan timeSpan = (TimeSpan) o;
        return begin.equals(timeSpan.begin) && end.equals(timeSpan.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(begin, end);
    }
}
