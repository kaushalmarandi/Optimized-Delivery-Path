package m.kash.optimizeddeliverypath.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import m.kash.optimizeddeliverypath.models.Customer;
import m.kash.optimizeddeliverypath.models.DeliveryPerson;
import m.kash.optimizeddeliverypath.models.Restaurant;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@Builder
public class RequestDto {
    private List<Customer> customers;
    private List<Restaurant> restaurants;
    private List<DeliveryPerson> deliveryPerson;
}
