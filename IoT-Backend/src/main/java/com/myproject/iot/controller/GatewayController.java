package com.myproject.iot.controller;

//this imports eveything we need
import com.myproject.iot.domain.Device;
import com.myproject.iot.domain.Gateway;
import com.myproject.iot.dto.CreateDevicePayload;
import com.myproject.iot.dto.CreateGatewayPayload;
import com.myproject.iot.dto.DeviceDto;
import com.myproject.iot.dto.GatewayDto;
import com.myproject.iot.service.GatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;//this im

//this is navigation stuff I think - Duncan
@RestController
@RequestMapping("/gateways")

public class GatewayController {
    //order of mapping HTTP things doesn't matter as long as they are mapped before they are used

    //this enables us to implicitly inject object dependency
    @Autowired//this is a best practice thing, seperates buieness function from server functions
    private GatewayService gatewayService;

   /*@Autowired
    private DeviceRepository deviceRepository;
     */

    //maps HTTP POST requests onto specific handlers
    @PostMapping("/")
    public ResponseEntity<Gateway> addGateway(@RequestBody CreateGatewayPayload payload) {
        return new ResponseEntity<>(gatewayService.addGateway(payload.getGatewayName()), HttpStatus.CREATED);
    }

    //@GetMapping("")
    //public List<Router> getAll() {
    //    return routerRepository.findAll();
    //}

    //this is part of the HTTP mapping and puts the GET requests on specific handler methods
    @GetMapping("/")
    public List<GatewayDto> getGateways() {//this is getting the list of devices from Dto
        return gatewayService.getGateways()
                //these are methods contained within the object
                .stream()
                .map(gateway -> new GatewayDto(gateway.getGateMacAddress(),
                        gateway.getIPAddress(), gateway.getGatewayName()))
                //this is just the stuff it is returning
                .collect(Collectors.toList());
    }
}