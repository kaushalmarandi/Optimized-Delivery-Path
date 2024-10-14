package m.kash.optimizeddeliverypath;

import m.kash.optimizeddeliverypath.enums.LocationType;
import m.kash.optimizeddeliverypath.models.*;
import m.kash.optimizeddeliverypath.services.OptimizedPathFinder;
import m.kash.optimizeddeliverypath.services.PathGenerator;
import m.kash.optimizeddeliverypath.services.TimeCalculator;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class OptimizedDeliveryPathApplicationTests {
    @Mock
    TimeCalculator timeCalculator;
    @Mock
    OptimizedPathFinder optimizedPathFinder;
    @Mock
    PathGenerator pathGenerator;

    @Test
    void contextLoads() {
    }
    @Test
    public void testShortestDeliveryTimeCalculation(){
        List<Path> allPaths = new ArrayList<>();

        double prepTime1 = 0.5;
        double prepTime2 = 0.7;

        //When
        double shortestTime = timeCalculator.shortestDeliveryTime(allPaths, prepTime1, prepTime2);

        //Then
        assertTrue(shortestTime >=0);  // Ensure the shortest time is non-negative.

    }
    @Test
    public void testPathGeneration(){
        // Given
        List<Location> locationList = new ArrayList<>();

        List<DeliveryPerson> deliveryPersonList = new ArrayList<>();
        List<Restaurant> restaurants = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();

        DeliveryPerson deliveryPerson = new DeliveryPerson("D1", "John", "john@email.com", "121212128990",
                new Location(12.9304, 77.6209, LocationType.DELIVERY_PERSON));
        deliveryPersonList.add(deliveryPerson);

        Restaurant restaurant1 = new Restaurant("R1", "abc1",
                new Location(12.9345, 77.62543, LocationType.RESTAURANT));

        Restaurant restaurant2 = new Restaurant("R2", "abc2",
                new Location(12.9345, 77.6254, LocationType.RESTAURANT));

        restaurants.add(restaurant1);
        restaurants.add(restaurant2);

        Customer customer1 = new Customer("C1", "Kaushal", "kaushal@email.com", "9191919191",
                new Location(12.9581, 77.71108, LocationType.CUSTOMER), restaurants.get(0));

        Customer customer2 = new Customer("C2", "Marandi", "marandi@email.com", "9090909090",
                new Location(12.9081, 77.6476, LocationType.CUSTOMER), restaurants.get(1));

        customers.add(customer1);
        customers.add(customer2);

        deliveryPersonList.forEach(deliveryPerson1 -> locationList.add(deliveryPerson.getDeliveryPersonLocation()));
        restaurants.forEach(restaurant -> locationList.add(restaurant.getRestaurantLocation()));
        customers.forEach(customer -> locationList.add(customer.getCustomerLocation()));

        assertTrue(locationList.size() > 0);

        Map<Location, Customer> customerMap = optimizedPathFinder.getCustomerLocationMap(customers);
        Map<Location, Restaurant> restaurantMap = optimizedPathFinder.getRestaurantLocationMap(restaurants);


    }

}
