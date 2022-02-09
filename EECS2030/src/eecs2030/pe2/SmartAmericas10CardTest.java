package eecs2030.pe2;

import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

class SmartAmericas10CardTest {


	final double tol = 0.0001;
	
	@Test
	public void testConstructor() {
		SmartAmericas10Card c1 = new SmartAmericas10Card(1,111);
		assertTrue("argumented constructor sets balance wrong", Math.abs(c1.getBalance() - 10.0) < tol);
	}
	
	@Test
	public void testCopyConstructor() {
		SmartAmericas10Card c1 = new SmartAmericas10Card(1,111);
		SmartAmericas10Card c2 = new SmartAmericas10Card(c1);
		assertTrue("copy constructor fails (assuming equals works)", c1.equals(c2));
	}

	@Test
	public void testAllowedZones(){
		SmartAmericas10Card c1 = new SmartAmericas10Card(1,111);
		assertTrue("allowedZones returns the wrong value", 
				c1.allowedZones().size() == 3);
	}

	@Test
	public void testIsAllowed(){
		SmartAmericas10Card c1 = new SmartAmericas10Card(1,111);
		assertTrue("isAllowed returned the wrong value", c1.isAllowed(CallZone.CANADA));
	}

	@Test
	public void testGetCallHistory1() {
		SmartAmericas10Card c1 = new SmartAmericas10Card(1,111);
		assertTrue("getCallHistory returned the wrong value for new card", c1.getCallHistory().size() == 0);
	}
	
	@Test
	public void testGetCallHistory2() {
		SmartAmericas10Card c1 = new SmartAmericas10Card(1,111);
		Call call1 = new Call("+14167362100",CallZone.CANADA,2);
		c1.charge(call1);
		assertTrue("getCallHistory returned a list of the wrong size after one call", 
				c1.getCallHistory().size() == 1);	
	}

	@Test
	public void testCostPerMinutes(){
		SmartAmericas10Card c1 = new SmartAmericas10Card(1,111);
		assertTrue("costPerMin returned the wrong value for Canada", 
				Math.abs(c1.costPerMin(CallZone.CANADA) - 0.03) < tol);
	}

	@Test
	public void testDeductWeeklyFee() {
		SmartAmericas10Card c1 = new SmartAmericas10Card(1, 111);
		double c1Bal = c1.getBalance();
		c1.deductWeeklyFee();
		assertTrue("deductWeeklyFee does not update balance correctly",
				Math.abs(c1Bal - (c1.getBalance() + 0.3)) < tol);
	}

	@Test
	public void testCharge1(){
		SmartAmericas10Card c1 = new SmartAmericas10Card(1,111);
		Call call1 = new Call("+14167362100",CallZone.CANADA,2);
		assertTrue("charge returned the wrong value", c1.charge(call1));
	}
	
	@Test
	public void testCharge2(){
		SmartAmericas10Card c1 = new SmartAmericas10Card(1,111);
		Call call1 = new Call("+14167362100",CallZone.CANADA,2);
		c1.charge(call1);
		assertTrue("charge did not update balance correctly after 2min call to Canada on new card",
				Math.abs(SmartAmericas10Card.INITIAL_BALANCE - (c1.getBalance() + 2 * 0.03)) < tol);
	}

	@Test
	public void testEquals(){
		SmartAmericas10Card c1 = new SmartAmericas10Card(1,111);
		SmartAmericas10Card c2 = new SmartAmericas10Card(1,111);
		assertTrue("equals returned the wrong value for new identical cards", c1.equals(c2));
	}
}
