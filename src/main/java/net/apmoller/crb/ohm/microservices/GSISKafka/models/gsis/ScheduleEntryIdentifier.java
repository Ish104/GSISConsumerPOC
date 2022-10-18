package net.apmoller.crb.ohm.microservices.GSISKafka.models.gsis;

public class ScheduleEntryIdentifier {
    private Vessel vessel;
    private Voyage arrivalVoyage;
    private Voyage departureVoyage;
    private ArrivalDepartureService arrivalService;
    private ArrivalDepartureService departureService;
    private PreviousPortCall previousPortCall;
    private CurrentPortCall currentPortCall;
    private NextPortCall nextPortCall;
}
