package m.kash.optimizeddeliverypath.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import m.kash.optimizeddeliverypath.dtos.RequestDto;
import m.kash.optimizeddeliverypath.services.LoadBalancer;
import m.kash.optimizeddeliverypath.services.OptimizedPathFinder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/shortest-delivery-path")
@AllArgsConstructor
public class DeliverySystemController {

    private OptimizedPathFinder optimizedPathFinder;
    private LoadBalancer loadBalancer;

    @PostMapping("")
    public ResponseEntity<?> calculateShortestDeliveryPath(@RequestBody RequestDto requestDto){
        try{
            log.info("Request Body: {}", requestDto);
            double response = optimizedPathFinder.minimumTimeCalculator(requestDto);
            return new ResponseEntity<>(Map.of("minimun time", response), HttpStatus.OK);
        } catch (Exception e){
            log.error("Error occured in triggering api call: {}", e.getMessage(), e);
            throw e;
        }
    }
    @GetMapping("/server")
    public void callServer(){
        loadBalancer.loadBalancing();
    }
}
