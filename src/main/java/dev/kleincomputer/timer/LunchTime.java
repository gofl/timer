package dev.kleincomputer.timer;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LunchTime {

    private LocalDateTime start;
    private LocalDateTime end;

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    @JsonIgnore
    public String getBreak() {
        Duration between = Duration.between(start, end);
        return String.format("%02d:%02d:%02d", between.toHours(), between.toMinutesPart(), between.toSecondsPart());
    }

    @Override
    public String toString() {
        return "LunchTime{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
