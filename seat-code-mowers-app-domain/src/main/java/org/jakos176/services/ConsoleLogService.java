package org.jakos176.services;

import org.jakos176.FullMovement;
import org.springframework.stereotype.Component;

@Component
public interface ConsoleLogService {

    void showFullMovementInConsole(FullMovement fullMovement);
}