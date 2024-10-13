package m.kash.optimizeddeliverypath.services;

import m.kash.optimizeddeliverypath.dtos.RequestDto;
import m.kash.optimizeddeliverypath.models.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OptimizedPathFinder {
    public double minimumTimeCalculator(RequestDto requestDto){
        Map<Location,Customer> customerMap = getCustomerLocationMap(requestDto.getCustomers());
        Map<Location,Restaurant> restaurantMap = getRestaurantLocationMap(requestDto.getRestaurants());

        List<Location> locations = new ArrayList<>();

        List<DeliveryPerson> deliveryPersonList = requestDto.getDeliveryPerson();
        List<Customer> customers = requestDto.getCustomers();
        List<Restaurant> restaurants = requestDto.getRestaurants();

        deliveryPersonList.forEach(deliveryPerson -> locations.add(deliveryPerson.getDeliveryPersonLocation()));
        restaurants.forEach(restaurant -> locations.add(restaurant.getRestaurantLocation()));
        customers.forEach(customer -> locations.add(customer.getCustomerLocation()));


        //Generate all possible paths
        List<Path> allPaths = PathGenerator.generatePaths(locations, deliveryPersonList.get(0), customerMap, restaurantMap);
        double prepTime1 = 0.8;
        double prepTime2 = 0.9;

        double shortestTime =

    }

    public Map<Location, Customer> getCustomerLocationMap(List<Customer> customers){
      Map<Location, Customer> custopmerMap = new HashMap<>();

      customers.forEach(customer -> {
          custopmerMap.put(customer.getCustomerLocation(), customer);
      });
      return custopmerMap;
    }

    public Map<Location, Restaurant> getRestaurantLocationMap(List<Restaurant> restaurants){
        Map<Location, Restaurant> restaurantMap = new HashMap<>();

        restaurants.forEach(restaurant -> {
            restaurantMap.put(restaurant.getRestaurantLocation(), restaurant);
        });
        return restaurantMap;
    }
}
