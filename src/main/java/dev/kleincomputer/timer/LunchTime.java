package dev.kleincomputer.timer;

import java.time.Duration;
import java.time.LocalDateTime;

public class LunchTime {

   private LocalDateTime start;
   private LocalDateTime end;

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


   @Override public String toString() {
      return "LunchTime{" +
            "start=" + start +
            ", end=" + end +
            '}';
   }
}
