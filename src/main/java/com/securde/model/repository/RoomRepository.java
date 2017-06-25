package com.securde.model.repository;

import com.securde.model.reservable.Room;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by kevin on 6/25/2017.
 */
public interface RoomRepository extends CrudRepository<Room, Integer> {
}
