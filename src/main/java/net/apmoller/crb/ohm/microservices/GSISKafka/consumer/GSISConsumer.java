package net.apmoller.crb.ohm.microservices.GSISKafka.consumer;

import emp.maersk.com.datedSchedule;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
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
    public void recievingKafkaMessage(  ConsumerRecord<String, datedSchedule> request,
                                              Acknowledgment acknowledgment) {
        try
        {

            if(checkScheduleEtaUpdatedEvent(request))
            {
                log.info("datedSchedule object: "+ request.value());;
            }

        }
        catch(Exception e)
        {
            log.error("issue while consuming gsis data");
        }
        acknowledgment.acknowledge();
        }

    @KafkaListener(topics = {
            "${kafka.consumer.topics.gsis-topic}" }, groupId = "#{'${kafka.consumer.groupIds.gsis-group-id}'}", containerFactory = "gsisListenerContainerFactory")
    public void recievingKafkaMessage2(  ConsumerRecord<String, datedSchedule> request,
                                        Acknowledgment acknowledgment) {
        try
        {

            if(checkScheduleEtaUpdatedEvent(request))
            {
                log.info("datedSchedule object: "+ request.value());;
            }

        }
        catch(Exception e)
        {
            log.error("issue while consuming gsis data");
        }
        acknowledgment.acknowledge();
    }


    private boolean checkScheduleEtaUpdatedEvent(ConsumerRecord<String, datedSchedule> request)
     {
         for(Header headers:request.headers())
         {
             if(headers.key().equalsIgnoreCase("eventName"))
             {
                String value=new String(headers.value(),StandardCharsets.UTF_8);
                if(value.equalsIgnoreCase("VesselDatedScheduleEtaUpdated"))
                {
                    log.info("recieved VesselDatedScheduleEtaUpdated event: "+value+": "+request.partition()+" , "+request.offset());
                    return true;
                }
                //log.info("event name: "+value+": "+request.partition()+" , "+request.offset());
                return false;
             }
         }
         return false;
     }

    }
