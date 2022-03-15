package dev.kleincomputer.timer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class TimerApplication {
   public static void main( String... args ) throws IOException, InterruptedException {

      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.registerModule( new JavaTimeModule() );

      Path workDayFile = Paths.get( ".", "state.json" );

      if ( !Files.exists( workDayFile ) ) {
         WorkDay workDay = new WorkDay();
         workDay.setState( WorkDayState.WORK_DAY_START );
         Files.writeString( workDayFile, objectMapper.writeValueAsString( workDay ),
               StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE_NEW );
      }
      WorkDay currentWorkDay = objectMapper.readValue(
            Files.readString( workDayFile ), WorkDay.class );

//      if ( currentWorkDay.getStart() != null
//            && currentWorkDay.getEnd() != null
//            && currentWorkDay.getState() == WorkDayState.WORK_DAY_END ) {
//
//
//
//         currentWorkDay = new WorkDay();
//         currentWorkDay.setState( WorkDayState.WORK_DAY_START );
//         Files.writeString( workDayFile, objectMapper.writeValueAsString( currentWorkDay ),
//               StandardOpenOption.TRUNCATE_EXISTING );
//      }

      List<WorkDayTransition> transitions = WorkDayTransitionTable.DEFAULT.getTransitions();

      for ( WorkDayTransition transition : transitions ) {

         if ( transition.getFrom() == currentWorkDay.getState() ) {
            transition.action().accept( currentWorkDay );
            currentWorkDay.setState( transition.getTo() );

            Files.writeString( workDayFile, objectMapper.writeValueAsString( currentWorkDay ),
                  StandardOpenOption.TRUNCATE_EXISTING );

            break;
         }
      }

   }


}
