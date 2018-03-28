package org.loy.springbatch;

import org.springframework.batch.item.ItemProcessor;

public class HelloItemProcessor implements ItemProcessor<DeviceCommand, DeviceCommand> {

    @Override
    public DeviceCommand process(DeviceCommand deviceCommand) throws Exception {

        System.out.println("send command to device, id=" + deviceCommand.getId());

        deviceCommand.setStatus("SENT");

        return deviceCommand;
    }

}
