package it.uniud.poo.compiti.compito_luglio_2023;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

private static List<Object> createTuple(String raw_category, int numberOfTickets) {
        List<Object> tuple = new ArrayList<>();
        tuple.add(raw_category);
        tuple.add(raw_category);
        return tuple;
    }

    public static void example1_sale() throws Exception {
        EventiLive api = new EventiLive();
        Factory f = api.factory();
        Customer customer= f.createCustomer("giorgio brajnik", "34812345678", "giorgio.brajnik@uniud.it");
        String event_name = "patty smith a udine 2023";
        PreSale thePresale = api.findEventPresale(event_name);

        EventEdition theEventEdition = api.findEventEdition(event_name,
                LocalDate.of(2023, 9, 30));
        if (theEventEdition == null){
            throw new Exception("Unable to find the requested edition for the event");
        }
        List<DesiredTicket> desiredTickets = new ArrayList<DesiredTicket>();
        String cardData = "number-of-credit-card, card-holder, expiry-date, secure-code"
        desiredTickets.add(f.createDesiredTicket("righe_1_20", 5,
                theEventEdition,PaymentKind.CREDIT_CARD,cardData));
        desiredTickets.add(f.createDesiredTicket("righe_50_120", 2,
                theEventEdition, PaymentKind.CREDIT_CARD, cardData));

        List<Ticket> boughtTickets = api.buyTickets(customer, desiredTickets, thePresale);
    }

    public static void example2_reporting(){
        EventiLive api = new EventiLive();
        LocalDate startDate = LocalDate.of(2023, 1, 15);
        LocalDate endDate = LocalDate.of(2023, 7, 26);

        ReportData report = api.generateReportByEvent();
        // consume report
        ReportData report2 = api.generateReportByEventEdition();
        // consume report2
        ReportData report3 = api.generateReportByEventEditionAndSeatType();
        // consume report3

    }
}
