package ch.gbssg.app.util;

/**
 * represent the user roll state
 * @author Pedrett Sandro
 * @version 1.0
 */
public enum UserRoll {
	// user states
	ADMIN(3), KV(2), DOCTOR(1);

    private final int value;
    
    /**
     * set the int value
     * @param value
     */
    private UserRoll(int value) {
        this.value = value;
    }

    /**
     * return the int value
     * @return
     */
    public int getValue() {
        return value;
    }
    
    /**
     * convert a int value to enum state
     * @param id to convert
     * @return the convertet state; otherwise null
     */
    public static UserRoll convert(int id) {
    	switch (id) {			
		case 1:
			return UserRoll.DOCTOR;
		case 2:
			return UserRoll.KV;
		case 3:
			return UserRoll.ADMIN;
		default:
			return null;
		}
    }
}
