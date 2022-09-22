package mk.ukim.finki.emt.ticketcatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
public class TicketCatalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketCatalogApplication.class, args);
    }

}
