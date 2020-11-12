package com.myproject.iot.controller;
//comment
import com.myproject.iot.domain.Device;
import com.myproject.iot.dto.CreateDevicePayload;
import com.myproject.iot.dto.DeviceDto;
import com.myproject.iot.repository.DeviceRepository;
import com.myproject.iot.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/devices")
public class Controller {

    @Autowired
    private DeviceService deviceService;
    private DeviceRepository deviceRepository;

    @PostMapping("/")
    public ResponseEntity<Device> addDevice(@RequestBody CreateDevicePayload payload) {
        return new ResponseEntity<>(deviceService.addDevice(payload.getName(), payload.getMacAdd(),
                payload.getConName()), HttpStatus.CREATED);
    }

    @PutMapping("/editDevivce/")
    public ResponseEntity<Device> editDevice(@RequestBody CreateDevicePayload payload) {
        return new ResponseEntity<>(deviceService.editDevice(payload.getId(), payload.getName(),
                payload.getMacAdd(), payload.getConName()), HttpStatus.CREATED);
    }

    @GetMapping("/getDevices")
    public List<DeviceDto> getDevices() {
        return deviceService.getDevices()
                .stream()
                .map(device -> new DeviceDto(device.getId(), device.getName(), device.getMacAdd(),
                        device.getConName()))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/deleteDevices/{id}")
   public ResponseEntity<String> deleteDevice(@PathVariable Long id){
        deviceService.deleteDevice(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getDevice/{id}")
    public ResponseEntity<String> getDevice(@PathVariable Long id){
        deviceService.getDevice(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
