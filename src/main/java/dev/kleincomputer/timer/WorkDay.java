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

   public WorkDay( WorkDayState state ) {
      this.state = state;
   }

   public LocalDateTime getStart() {
      return start;
   }

   public void setStart( LocalDateTime start ) {
      this.start = start;
   }

   public LocalDateTime getEnd() {
      return end;
   }

   public void setEnd( LocalDateTime end ) {
      this.end = end;
   }

   public LunchTime getLunchTime() {
      return lunchTime;
   }

   public void setLunchTime( LunchTime lunchTime ) {
      this.lunchTime = lunchTime;
   }

   public WorkDayState getState() {
      return state;
   }

   public void setState( WorkDayState state ) {
      this.state = state;
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
