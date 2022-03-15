package dev.kleincomputer.timer;

import static dev.kleincomputer.timer.WorkDayTransition.create;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public enum WorkDayTransitionTable {

   DEFAULT(
      create( WorkDayState.WORK_DAY_START, workDay -> workDay.setStart( LocalDateTime.now() ) ,  WorkDayState.LUNCH_TIME_START ),
      create( WorkDayState.LUNCH_TIME_START, workDay -> workDay.getLunchTime().setStart( LocalDateTime.now() ) , WorkDayState.LUNCH_TIME_END ),
      create( WorkDayState.LUNCH_TIME_END, workDay -> workDay.getLunchTime().setEnd( LocalDateTime.now() ) , WorkDayState.WORK_DAY_END ),
      create( WorkDayState.WORK_DAY_END,  workDay ->
      {
         workDay.setEnd( LocalDateTime.now() );

         Path timetable = Paths.get( ".", "timetable.csv" );

         if ( !Files.exists( timetable ) ) {
            try {
               Files.writeString( timetable, "date,from,to,break"+System.lineSeparator(), StandardOpenOption.CREATE_NEW );
            } catch ( IOException e ) {
               e.printStackTrace();
            }
         }

         try {
            Files.writeString( timetable, tableLine( workDay ), StandardOpenOption.APPEND );
         } catch ( IOException e ) {
            e.printStackTrace();
         }

      }, WorkDayState.SLEEP )
   );

   private final List<WorkDayTransition> transitions;

   WorkDayTransitionTable(
         WorkDayTransition... transitions ) {
      this.transitions = Arrays.asList( transitions );
   }

   public List<WorkDayTransition> getTransitions() {
      return transitions;
   }

   private static String tableLine( WorkDay workDay ) {
      return String.join( ",",
            workDay.getStart().withNano(0).format(DateTimeFormatter.ISO_LOCAL_DATE),
            workDay.getStart().withNano(0).format( DateTimeFormatter.ISO_LOCAL_TIME ),
            workDay.getEnd().withNano(0).format( DateTimeFormatter.ISO_LOCAL_TIME ),
            workDay.getLunchTime().getBreak())
            + System.lineSeparator();
   }
}
