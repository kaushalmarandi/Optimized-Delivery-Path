package m.kash.optimizeddeliverypath.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import m.kash.optimizeddeliverypath.enums.LocationType;
@Getter
@Setter
@AllArgsConstructor
public class Location {
    double latitude;
    double longitude;
    LocationType locationType;

}
