package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingBoy;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyFacts {
    @Test
    void should_park_car_in_parking_lot_by_parking_boy(){
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();

        assertNotNull(parkingLot.park(car));
    }

    @Test
    void should_fetch_car_by_parking_ticket_from_parking_lot() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        ParkingTicket park = parkingBoy.park(car);

        assertEquals(parkingBoy.fetch(park), car);
    }

    @Test
    void should_parking_boy_park_multiple_cars() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car myCar = new Car();
        Car yourCar = new Car();

//        when
        ParkingTicket ticket = parkingBoy.park(myCar);
        parkingBoy.park(yourCar);

        Car fetchedCar = parkingBoy.fetch(ticket);

        assertSame(myCar, fetchedCar);
        assertNotSame(yourCar, fetchedCar);
    }

    @Test
    void should_parking_boy_return_no_cars_with_wrong_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car myCar = new Car();
        Car yourCar = new Car();

        parkingBoy.park(yourCar);

        Car fetchedCar = parkingBoy.fetch(new ParkingTicket());

        assertNotSame(myCar, fetchedCar);
    }

    @Test
    void should_parking_boy_return_null_with_no_capacity() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car myCar = new Car();

        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        ParkingTicket parked = parkingBoy.park(myCar);

        assertNull(parked);
    }

    @Test
    void should_parking_boy_removed_fetched_car() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car myCar = new Car();

        parkingBoy.park(myCar);
        ParkingTicket ticket = parkingBoy.park(myCar);

        Car fetchedCar = parkingBoy.fetch(ticket);

        assertNotNull(fetchedCar);
        Car fetchedCarAgain = parkingBoy.fetch(ticket);
        assertNull(fetchedCarAgain);
    }
}
