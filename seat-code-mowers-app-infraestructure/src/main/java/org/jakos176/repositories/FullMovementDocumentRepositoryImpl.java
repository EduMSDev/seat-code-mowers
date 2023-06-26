package org.jakos176.repositories;

import lombok.extern.slf4j.Slf4j;
import org.jakos176.FullMovement;
import org.springframework.stereotype.Repository;

/*
Here messages will be displayed on the screen, but the idea is to understand the purpose of the infrastructure layer,
communication with third parties totally decoupled from the other layers. The implementation of a non-relational database
remains as something pending to be implemented. But here we see the power of DDD, that you can modify the infrastructure
layer with whatever you want (mongodb, mysql, save to a file) without having to modify the rest of the layer
 */
@Repository
@Slf4j
public class FullMovementDocumentRepositoryImpl implements FullMovementDocumentRepository {

    @Override
    public void showFullMovementsInScreen(FullMovement movement) {

        //save
        log.info(movement.toString());
    }
}
