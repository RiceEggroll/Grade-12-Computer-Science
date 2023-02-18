package unit3;

public class ElevTest {
	public static void main(String[]args) {

		Elevator south = new Elevator();
		Elevator east = new Elevator();
		east.floor = 5;
		
//		have 10 people get on south at the ground floor
		south.openDoors();
		south.addPeople(10);
		south.closeDoors();
		south.displayInfo();

//		3 get out on floor 4 
		south.goToFloor(4);
		south.openDoors();
		south.removePeople(3);
		south.closeDoors();
		south.displayInfo();
		
//		the rest get out on the top floor
		south.goToFloor(17);
		south.openDoors();
		south.removePeople(7);
		south.closeDoors();
		south.displayInfo();
		
		System.out.println("East elevator");
		
//		have 8 get into east on the 5th floor.
		east.openDoors();
		east.addPeople(8);
		east.closeDoors();
		east.displayInfo();
		
//		try and add 30 more on the 8th floor
		east.goToFloor(8);
		east.openDoors();
		east.addPeople(30);
		east.closeDoors();
		east.displayInfo();
		
//		try to go above the top floor; try to go to a negative floor
		east.goToFloor(100);
		east.goToFloor(-100);
		
//		move both elevators to the second floor
//		open the doors on east
//		have a power failure
		south.goToFloor(2);
		east.goToFloor(2);
		east.openDoors();
		Elevator.setPowerOn(false);
		
//		then test to make sure that your elevators can't move or close or open doors
		south.up();
		south.down();
		south.closeDoors();
		east.up();
		east.down();
		east.closeDoors();
		
//		restore the power (you should see a message)
//		restore the power again (no message, the power is already on)
		Elevator.setPowerOn(true);
		Elevator.setPowerOn(true);
		
//		make elevator south move up one floor at a time from the ground floor to floor 10, stopping on each floor and opening and closing doors
		south.down();
		south.displayInfo();
		
		for (int i = 0; i < 9; i++) {
			south.up();
			south.openDoors();
			south.closeDoors();
		}
		south.displayInfo();
	}
}
