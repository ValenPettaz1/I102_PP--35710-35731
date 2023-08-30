package garage0;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class GarageTest {

  @Test public void testNewParkingIsEmpty() {
    assertTrue( new Garage( 3 ).isEmpty() );
  }

  @Test public void testNewParkingHasNoMoney() {
    assertEquals( 0, new Garage( 3 ).totalFees() );
  }

  @Test public void testParkOneCar() {
    Garage garage = new Garage( 3 );

    Car car = new Car( "ABC123" );
    garage.parkCar( car );
    assertFalse( garage.isEmpty() );
  }

  @Test public void testOneCarFee() {
    Garage garage = new Garage( 3 );

    Car car = new Car( "ABC123" );
    garage.parkCar( car );
    assertEquals( 10, garage.totalFees() );
  }

  @Test public void testManyCarsFees() {
    Garage garage = new Garage( 3 );

    Car car = new Car( "ABC123" );
    Car car2 = new Car( "DEF456" );
    garage.parkCar( car );
    garage.parkCar( car2 );
    assertEquals( 20, garage.totalFees() );
  }

  @Test public void testInOutOneCar() {
    Garage garage = new Garage( 3 );

    Car car = new Car( "ABC123" );
    garage.parkCar( car );
    assertFalse( garage.isEmpty() );
    garage.unparkCar( car );
    
    assertTrue( garage.isEmpty() );
  }

  @Test public void testInOutOneCarFees() {
    Garage garage = new Garage( 3 );

    Car car = new Car( "ABC123" );
    garage.parkCar( car );
    assertFalse( garage.isEmpty() );
    garage.unparkCar( car );

    assertEquals( 10, garage.totalFees() );
  }

  @Test public void testUnparkAnEmptyLot() {
    try {
      new Garage( 3 ).unparkCar( new Car( "ABC123") );
    } catch ( Exception e ) {
      assertEquals(  "Missing car!", e.getMessage() );
    }
  }

  @Test public void testFullLot() {
    Garage garage = new Garage( 1 );
    Car car = new Car( "ABC123" );
    garage.parkCar( car );
 
    assertTrue( garage.cars.size() == garage.capacity );
  }

  @Test public void testOverFillLot() {
    Garage garage = new Garage( 1 );
    Car car = new Car( "ABC123" );
    garage.parkCar( car );
 
    Car car2 = new Car( "DEF456" );
    try {
      garage.parkCar( car2 );
    } catch ( Exception e ) {
      assertEquals( "No space available" , e.getMessage() );
    }

    assertEquals( 1, garage.getNumCars() );
  }
  
  @Test public void testRotateLot() {
    Garage garage = new Garage( 1 );
    Car car = new Car( "ABC123" );
    garage.parkCar( car );
    garage.unparkCar( car );
 
    Car car2 = new Car( "DEF456" );
    garage.parkCar( new Car( "ABC123") );
    assertEquals( 1, garage.getNumCars() );

    assertEquals( 20, garage.totalFees() );
    
  }
  
  @Test public void testParkOneCarTwice() {
    Garage garage = new Garage( 3 );

    Car car = new Car( "ABC123" );
    garage.parkCar( car );
    try {
      garage.parkCar( car );
    } catch ( Exception e ) {
      assertEquals( "Twin Cars!", e.getMessage() );
    }

    assertEquals( 1, garage.getNumCars() );
  }
  @Test public void testManyCarsFeesOnMembers() {
    Garage garage = new Garage( 3 );

    Car car = new Car( "ABC123" );
    Car car2 = new Car( "DEF456" );
    garage.associate( car );
    garage.parkCar( car );
    garage.parkCar( car2 );
    assertEquals( 15, garage.totalFees() );
  }
  @Test public void testOneMemberCarFee() {
    Garage garage = new Garage( 3 );

    Car car = new Car( "ABC123" );
    garage.associate( car );
    garage.parkCar( car );
    assertEquals( 5, garage.totalFees() );
  }

}
