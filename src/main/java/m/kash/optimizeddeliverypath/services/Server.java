package m.kash.optimizeddeliverypath.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
public class Server {
    private int resources;
    private String name;

    public void serverCall(){
        log.info("hello from "+ name);
    }
}
