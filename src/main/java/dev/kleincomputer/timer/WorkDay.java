package dev.kleincomputer.timer;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class WorkDay {

   private LocalDateTime start;
   private LocalDateTime end;
   private LunchTime lunchTime = new LunchTime();
   private WorkDayState state;

   public WorkDay() {
   }

   public LocalDateTime getStart() {
      return start;
   }

   public WorkDay setStart(LocalDateTime start) {
      this.start = start;
      return this;
   }

   public LocalDateTime getEnd() {
      return end;
   }

   public WorkDay setEnd(LocalDateTime end) {
      this.end = end;
      return this;
   }

   public LunchTime getLunchTime() {
      return lunchTime;
   }

   public WorkDay setLunchTime(LunchTime lunchTime) {
      this.lunchTime = lunchTime;
      return this;
   }

   public WorkDayState getState() {
      return state;
   }

   public WorkDay setState(WorkDayState state) {
      this.state = state;
      return this;
   }

   @Override
   public String toString() {
      return "WorkDay{" +
            "start=" + start +
            ", end=" + end +
            ", lunchTime=" + lunchTime +
            '}';
   }
}
