package m.kash.optimizeddeliverypath.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Restaurant {
    private String id;
    private String name;
    Location restaurantLocation;
}
