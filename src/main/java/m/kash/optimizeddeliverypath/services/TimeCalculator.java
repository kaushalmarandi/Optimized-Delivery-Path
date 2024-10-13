package m.kash.optimizeddeliverypath.services;

import m.kash.optimizeddeliverypath.models.Location;
import m.kash.optimizeddeliverypath.models.Path;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeCalculator {
    private static final double EARTH_RADIUS_KM = 6371.0; // Earth's radius

    /*
    * returns total time of particular path
    * */
    private static double calculateTotalTime(Path path, double prepTime1, double prepTime2){
        //check if the route has at least two locations
        if (path == null || path.getLocations()==null || path.getLocations().size()<2){
            throw new IllegalArgumentException("Path must have atleat two locations to calculate total time.");
        }

        double totalTime = 0;
        List<Location> locations = path.getLocations();

        for(int i=0; i < locations.size()-1; i++){
            Location current = locations.get(i);
            Location next = locations.get(i+1);
            totalTime +=
        }
    }

    /*
    * returns time taken between two locations
    * */
    private static double calculateTime(Location location1, Location location2){
        return
    }


    /*
    * returns distance between location1 & location2
    * */
    private static double calculateDistance(double lat1, double lon1, double lat2, double lon2){
        double lat1Rad
    }
}
