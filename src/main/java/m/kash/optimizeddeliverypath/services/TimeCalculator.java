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
            totalTime += calculateTime(current, next);
        }
        return  totalTime + prepTime1 + prepTime2;
    }

    /*
    * returns time taken between two locations
    * */
    private static double calculateTime(Location location1, Location location2){
        return calculateDistance(location1.getLatitude(), location1.getLongitude(), location2.getLatitude(), location2.getLongitude())/20.0;
    }


    /*
    * returns distance between location1 & location2
    * */
    private static double calculateDistance(double lat1, double lon1, double lat2, double lon2){
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat1);
        double lon2Rad = Math.toRadians(lon2);

        double deltaLat = lat2Rad - lat1Rad;
        double deltaLon = lon2Rad - lon1Rad;

        double a = Math.pow(Math.sin(deltaLat/2), 2) +
                        Math.cos(lat1Rad) *
                        Math.cos(lat2Rad) *
                        Math.pow(Math.sin(deltaLon)/2, 2);

        double c = 2* Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return EARTH_RADIUS_KM * c;
    }

    public static double shortestDeliveryTime(List<Path> allPaths, double prepTime1, double prepTime2){
        double shortestTime = Double.MAX_VALUE;
        for (Path path : allPaths){
            double totalTime = calculateTotalTime(path, prepTime1, prepTime2);
            shortestTime = Math.min(shortestTime, totalTime);
        }
        return shortestTime;
    }
}
