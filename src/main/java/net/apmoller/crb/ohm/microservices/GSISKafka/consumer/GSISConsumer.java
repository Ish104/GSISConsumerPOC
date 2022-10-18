package net.apmoller.crb.ohm.microservices.GSISKafka.consumer;

import lombok.RequiredArgsConstructor;

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

        try
        {
            if(checkScheduleEtaUpdatedEvent(request))
                log.info("request: "+request.value());
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
