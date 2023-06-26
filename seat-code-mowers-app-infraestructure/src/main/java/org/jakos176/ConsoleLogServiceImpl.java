package org.jakos176;

import lombok.RequiredArgsConstructor;
import org.jakos176.repositories.FullMovementDocumentRepositoryImpl;
import org.jakos176.services.ConsoleLogService;
import org.springframework.stereotype.Service;

/*
In this service layer we gather all the external services that we have to join and their logic. we only have one
"repository" for this case, but let's imagine we have to query a separate Redis api or cache. Well, each one of them
would have its implementation, just like our "mongodb repository" has, and in this class all the data would be gathered
 together with the logic.
 */
@Service
@RequiredArgsConstructor
public class ConsoleLogServiceImpl implements ConsoleLogService {

    private final FullMovementDocumentRepositoryImpl fullMovementDocumentRepository;

    @Override
    public void showFullMovementInConsole(FullMovement fullMovement) {
        this.fullMovementDocumentRepository.showFullMovementsInScreen(fullMovement);
    }
}
