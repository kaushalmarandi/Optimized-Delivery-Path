package m.kash.optimizeddeliverypath.services;

import m.kash.optimizeddeliverypath.enums.LocationType;
import m.kash.optimizeddeliverypath.models.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;

@Service
public class PathGenerator {
    public static List<Path> generatePaths(List<Location> locations, DeliveryPerson deliveryPerson, Map<Location, Customer> customerMap,
                                           Map<Location, Restaurant> restaurantMap){
        List<Path> allPaths = new ArrayList<>();
        permute(new Path(), new ArrayList<>(locations), allPaths, deliveryPerson);
        return filterPaths(allPaths, customerMap, restaurantMap);
    }

    private static void permute(Path path, List<Location> remainingLocations,
                                List<Path> allPaths, DeliveryPerson deliveryPerson){

        //Base case: no more remaining locations.
        if (remainingLocations.isEmpty()){
            if(path.getLocations().contains(deliveryPerson.getDeliveryPersonLocation())){
                allPaths.add(path);
            }
            return;
        }
        for (int i=0; i<remainingLocations.size(); i++){
            Location currentlocation = remainingLocations.get(i);
             //Ensure that path either starts empty or with delivery person's location.

            if (ObjectUtils.isEmpty(path.getLocations()) || path.getLocations().get(0).equals(deliveryPerson.getDeliveryPersonLocation())){
                //Create new path, based on current path, and add the current location
                Path newPath = new Path();
                List<Location> newLocations = new ArrayList<>(path.getLocations());
                newLocations.add(currentlocation); //adding the current location
                newPath.setLocations(newLocations);

                //create a new list of remaining locations excluding the current location.
                List<Location> newRemainingLocation = new ArrayList<>(remainingLocations);
                newRemainingLocation.remove(i);

                //Recursively generate permutations for the new path and remaining locations
                permute(newPath, newRemainingLocation, allPaths, deliveryPerson);
            }
        }
    }

    public static List<Path> filterPaths(List<Path> allPaths, Map<Location,Customer> customerMap,
                                         Map<Location, Restaurant> restaurantMap){
        List<Path> filteredPaths = new ArrayList<>();

        for (Path path : allPaths){
            if(isValidPath(path, customerMap, restaurantMap)){
                filteredPaths.add(path);
            }
        }
        return filteredPaths;
    }

    private static boolean isValidPath(Path path, Map<Location,Customer> customerMap,
                                       Map<Location,Restaurant> restaurantMap){
        // Check if the delivery person visits the correct restaurant before delivering to the corresponding customer
        Set<String> visitedRestaurantIds = new HashSet<>();
        for (int i=0; i<path.getLocations().size(); i++){
            Location current = path.getLocations().get(i);
            Customer customer = customerMap.get(current);
            Restaurant restaurant = restaurantMap.get(current);
            if (current.getLocationType().equals(LocationType.RESTAURANT_LOCATION)){
                boolean isIdMatch = customerMap.values().stream().anyMatch(c -> restaurant.getId().equals(c.getRestaurant().getId()));
                if (isIdMatch){
                    visitedRestaurantIds.add(restaurant.getId());
                }
            } else if (current.getLocationType().equals(LocationType.CUSTOMER_LOCATION)) {
                if (!visitedRestaurantIds.contains(customer.getRestaurant().getId())){
                    return false;
                }
            }
        }
        return true;
    }
}
