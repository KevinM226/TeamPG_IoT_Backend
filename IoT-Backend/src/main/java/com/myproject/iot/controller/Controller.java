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

    @PostMapping("/editDevice")
    public ResponseEntity<Device> editDevice(@RequestBody CreateDevicePayload payload) {
        Device device = deviceService.getDevice(payload.getId());
        device.setConName(payload.getConName());
        device.setMacAdd(payload.getMacAdd());
        device.setName(payload.getName());
        return ResponseEntity.ok(deviceService.save(device));
    }

    @GetMapping("/getDeviceById/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable Long id/*,
            @RequestBody CreateDevicePayload payload*/){
        Device device = deviceService.getDevice(id);

        return  ResponseEntity.ok(device);
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

}
