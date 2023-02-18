package unit3;

public class Elevator {

	final static String manufacturer = "Otis";
	final static int topFloor = 19;
	final static int maxOccupants = 25;
	static boolean powerOn = true;

	int floor;
	int people;
	boolean doorOpen;

	//Constructors
	Elevator() {
		floor = 1;
		people = 0;
		doorOpen = false;
	}

	Elevator(int floor) {
		if (floor < 1 && floor > topFloor) {
			floor = 1;
		}
	}

	//checks if powerOn is equal to power, if not, changes powerOn and displays a
	//message depending on if it turned on or off
	static void setPowerOn(boolean power) {
		if (powerOn != power) {
			if (power == true) {
				System.out.println("Power has been restored.");
				powerOn = true;
			}
			if (power == false) {
				System.out.println("Power has been shut off");
				powerOn = false;
			}
		}
	}

	static void getPowerState() {
		System.out.println(powerOn);
	}

	//if and return statements if the conditions do not match up
	//if they do, it changes according what it is supposed to do
	public void up() {
		if (!powerOn) {
			System.out.println("There is no power.");
			return;
		}
		if (doorOpen) {
			System.out.println("The elevator can't move if the door is open.");
			return;
		}
		if (floor + 1 > topFloor) {
			System.out.printf("You can't go higher than %d.",topFloor);
			return;
		}
		floor++;
	}

	public void down() {
		if (!powerOn) {
			System.out.println("There is no power.");
			return;
		}
		if (doorOpen) {
			System.out.println("The elevator can't move if the door is open.");
			return;
		}
		if (floor - 1 < 1) {
			System.out.println("The elevator can't phase through the ground.");
			return;
		}
		floor--;
	}

	public void goToFloor(int n) {
		if (!powerOn) {
			System.out.println("There is no power.");
			return;
		}
		if (doorOpen) {
			System.out.println("The elevator can't move while the door is open.");
			return;
		}
		if (n > topFloor) {
			System.out.println("You can't go higher than "+topFloor);
			return;
		}
		if (n < 1) {
			System.out.println("The elevator can't phase through the ground. ");
			return;
		}
		floor = n;
	}

	public void openDoors() {
		if (!powerOn) {
			System.out.println("There is no power.");
			return;
		}
		if (doorOpen) {
			System.out.println("The door is already open.");
			return;
		}
		doorOpen = true;
	}
	
	public void closeDoors() {
		if (!powerOn) {
			System.out.println("There is no power.");
			return;
		}
		if (!doorOpen) {
			System.out.println("The door is already closed.");
			return;
		}
		doorOpen = false;
	}

	public void addPeople(int n) {
		if (!doorOpen) {
			System.out.println("People can't get in if the doors are closed.");
			return;
		}
		if (people + n > maxOccupants) {
			System.out.println("The elevator can't support that many people at once.");
			return;
		}
		if (n < 0) {
			System.out.println("You can't have a negative number of people");
			return;
		}
		people += n;
	}

	public void removePeople(int n) {
		if (!doorOpen) {
			System.out.println("People can't get out if the doors are closed.");
			return;
		}
		if (people - n < 0) {
			System.out.println("You can't have negative people in an elevator.");
			return;
		}
		if (n < 0) {
			System.out.println("You can't have a negative number of people.");
			return;
		}
		people -= n;
	}

	//printf to display information about the elevator
	public void displayInfo() {
		System.out.printf("%s, floor %d, %d people, doors open? %b%n",manufacturer,floor,people,doorOpen);
	}
}
