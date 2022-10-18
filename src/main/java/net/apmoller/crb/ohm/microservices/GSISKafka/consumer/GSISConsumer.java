package net.apmoller.crb.ohm.microservices.GSISKafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import net.apmoller.crb.ohm.microservices.GSISKafka.models.gsis.ScheduleEntries;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@EnableKafka
@Slf4j
public class GSISConsumer {

    @KafkaListener(topics = {
            "${kafka.consumer.topics.gsis-topic}" }, groupId = "#{'${kafka.consumer.groupIds.gsis-group-id}'}", containerFactory = "gsisListenerContainerFactory")
    public void recievingKafkaMessage( @Payload ConsumerRecord<String, Object> request,
                                              Acknowledgment acknowledgment) {
        ScheduleEntries scheduleEntries=null;
        try
        {
            scheduleEntries=new ObjectMapper().readValue(String.valueOf(request),ScheduleEntries.class);
            log.info("scheduleEntries object: "+scheduleEntries.getScheduleEntry().get(0).getArrivalStatus());
//            if(checkScheduleEtaUpdatedEvent(request))
//            {
//                scheduleEntries=new ObjectMapper().readValue(String.valueOf(request),ScheduleEntries.class);
//                log.info("scheduleEntries object: "+scheduleEntries.getScheduleEntry().getArrivalStatus());
//            }

        }
        catch(Exception e)
        {
            log.error("issue while consuming gsis data");
        }
        acknowledgment.acknowledge();
        }


     private boolean checkScheduleEtaUpdatedEvent(ConsumerRecord<String, Object> request)
     {
         for(Header headers:request.headers())
         {
             if(headers.key().equalsIgnoreCase("eventName"))
             {
                String value=new String(headers.value(),StandardCharsets.UTF_8);
                if(value.equalsIgnoreCase("VesselDatedScheduleEtaUpdated"))
                {
                    log.info("recieved VesselDatedScheduleEtaUpdated event: "+value);
                    return true;
                }
                log.info("event name: "+value);
                return false;
             }
         }
         return false;
     }

    }
