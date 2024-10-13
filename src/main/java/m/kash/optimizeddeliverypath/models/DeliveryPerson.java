package m.kash.optimizeddeliverypath.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DeliveryPerson {
   private String id;
   private String name;
   private String email;
   private String phoneNumber;
    Location deliveryPersonLocation;

}
