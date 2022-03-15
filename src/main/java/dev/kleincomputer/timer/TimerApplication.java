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
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class TimerApplication {
   public static void main( String... args ) throws IOException, InterruptedException {

      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
      objectMapper.registerModule( new JavaTimeModule() );

      Path workDayFile = Paths.get( ".", "state.json" );

      if( !Files.exists( workDayFile )){
         Files.createFile( workDayFile );

         resetState(workDayFile, objectMapper, new WorkDay().setState(WorkDayState.WORK_DAY_START));
      }

      WorkDay currentWorkDay = objectMapper.readValue(
            Files.readString( workDayFile ), WorkDay.class );

      if(currentWorkDay.getState() == WorkDayState.SLEEP){
         resetState(workDayFile, objectMapper, new WorkDay().setState(WorkDayState.WORK_DAY_START));
      }

      List<WorkDayTransition> transitions = WorkDayTransitionTable.DEFAULT.getTransitions();

      for ( WorkDayTransition transition : transitions ) {

         if ( transition.getFrom() == currentWorkDay.getState() ) {
            transition.action().accept( currentWorkDay );
            currentWorkDay.setState( transition.getTo() );

            resetState(workDayFile, objectMapper, currentWorkDay);

            break;
         }
      }

   }

   private static void resetState(Path workDayFile, ObjectMapper objectMapper, WorkDay WORK_DAY_START) throws IOException {
      Files.writeString(workDayFile, objectMapper.writeValueAsString(WORK_DAY_START),
              StandardOpenOption.TRUNCATE_EXISTING );
   }


}
