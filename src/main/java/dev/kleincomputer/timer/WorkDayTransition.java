package dev.kleincomputer.timer;

public record WorkDayTransition(WorkDayState from,
                                WorkDayAction action,
                                WorkDayState to) {

   public WorkDayState getFrom() {
      return from;
   }

   public WorkDayState getTo() {
      return to;
   }

   public static WorkDayTransition create( WorkDayState from, WorkDayAction action, WorkDayState to ) {
      return new WorkDayTransition( from, action, to );
   }
}
