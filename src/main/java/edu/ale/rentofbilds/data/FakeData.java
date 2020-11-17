package edu.ale.rentofbilds.data;

import edu.ale.rentofbilds.model.Client;
import edu.ale.rentofbilds.model.Item;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class FakeData {
    private List<Item> items = Stream.of(
            new Item(null, "Coca-Cola", "Drink",
                    LocalDateTime.now(), LocalDateTime.now()),
            new Item(null, "Pepsi", "Drink",
                    LocalDateTime.now(), LocalDateTime.now()),
            new Item(null, "Sprite", "Drink",
                    LocalDateTime.now(), LocalDateTime.now())
    ).collect(Collectors.toList());

    private List<Client> clients = Stream.of(


            new Client("1","Bomj Viktoriya","", "Chernivtsi pod mostom", "nema",
                    LocalDate.of(2012, Month.APRIL, 2), "EWE TOT BOMJ"
            ),
            new Client("2", "Bomj ILUSha","", "Chernivtsi ", "nema",
                    LocalDate.of(2000, Month.SEPTEMBER, 5), "EWE TOT BOMJ"
            ),
            new Client("3", "Bomj EdaaaaGAR","", "Chernivtsi pod mostom", "2034982039",
                    LocalDate.of(2002, Month.APRIL, 5), "EWE TOT BOMJ"
            ),
            new Client("4", "Bomj VGOSDS","", "Chernivtsi pod mostom", "00012312",
                    LocalDate.of(1987, Month.MARCH, 2), "EWE TOT BOMJ"
            )
    ).collect(Collectors.toList());

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
