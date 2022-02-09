package eecs2030.pe2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* PLEASE DO NOT MODIFY A SINGLE STATEMENT IN THE TEXT BELOW.
READ THE FOLLOWING CAREFULLY AND FILL IN THE GAPS

I hereby declare that all the work that was required to 
solve the following problem including designing the algorithms
and writing the code below, is solely my own and that I received
no help in creating this solution and I have not discussed my solution 
with anybody. I affirm that I have read and understood
the Senate Policy on Academic honesty at 
https://secretariat-policies.info.yorku.ca/policies/academic-honesty-senate-policy-on/
and I am well aware of the seriousness of the matter and the penalties that I will face as a 
result of committing plagiarism in this assignment.

BY FILLING THE GAPS,YOU ARE SIGNING THE ABOVE STATEMENTS.

Full Name:Miguel Graham
Student Number:216595449
Course Section:Z
*/
/**
 * A class that represents a smart phone card for the Americas. ForSmartAmericas10 phone cards,
 * only calls to Canada, the USA, and Latin America are allowed.
 * For calls to Canada the cost per minute is $0.03, for calls to the USA the cost per minute is $0.05,
 * and for calls to Latin America the cost-per minute is $0.10. The initial balance on the card is $10.00.
 * The weekly fees are $0.30. 
 * 
 * <p>
 * Each card also keeps track of the history of calls charged to the card.The call history is List<Call>.
 * For example, a brand new card would have as its call history the empty list [].
 * If the card was then used to call+14167362100 in Canada for 2 minutes,
 * its call history would then become[call to number +14167362100 in zone CANADA for 2 minutes].
 * If the card was then used to call +14152121000 in the USA for 2 minutes,
 * its call history would then become [call to number +14167362100 in zone CANADA for 2 minutes,
 * call to number +14152121000 in zone USA for 2 minutes].Every time a call is successfully charged to the card,
 *it is added to the call history.The relation between SmartAmericas10Card and its call history is composition

 * @author migue
 *
 */
public class SmartAmericas10Card extends PhoneCard {

	public static final double COST_PER_MIN_TO_CANADA = 0.03;
	public static final double COST_PER_MIN_TO_LATINAM = 0.1;
	public static final double COST_PER_MIN_TO_USA = 0.05;
	public static final double INITIAL_BALANCE = 10.0;
	public static final double WEEKLY_FEES = 0.3;
	private List<Call> callHistory;
	
	
	/* class invariant: 0 < number && 0 < password */
	
	/**
	 * Create a phone card with the given number and password.
	 * Sets card's balance to INITIAL_BALANCE and its call history to the empty list.
	 * 
	 * @param number the phone card's number.
	 * @param password the card's password.
	 * @pre. number and password are positive and.
	 */
	public SmartAmericas10Card(long number, int password) {
		/*call the super constructor and assign the parameters passed 
		 * to the inherited variables, and set the balance to the balance of a smart 
		 * americas10card.*/
		super(number, password, SmartAmericas10Card.INITIAL_BALANCE);
		/* callHistory is  initialized as an empty list */
		this.callHistory = new ArrayList<Call>();
	}
	
		/**
		 * Create a copy of the given SmartAmericas10Card.A deep copy is returned.
		 * 
		 * @param card is the card to be copied.
		 * @pre. card is not null.
		 */
	public SmartAmericas10Card (SmartAmericas10Card card) {
        /*All instance variables are copied to the current object from the object to be copied*/
		super(card.getNumber(), card.getPassword(), card.getBalance());
		/*A deep copy of the callHistory is created by copying each of the elements of the card 
		 * to a new card*/
		this.callHistory = new ArrayList<Call>();
		for (int i = 0; i < card.callHistory.size(); i++) {
			this.callHistory.add(new Call(card.callHistory.get(i)));
		}
	}

	/**
	 * Check whether a call to the argument zone is allowed for this phone card.
	 * For SmartAmericas10 phone cards, only calls to Canada, the USA, and Latin Americaare allowed.
	 * @param zone the call zone to check.
	 * @pre. zone is not null.
	 * @return true if the card supports the call zone; false otherwise.
	 */
	@Override
	public boolean isAllowed(CallZone zone) {
		/*Checks if the allowed zones contain the zones of the call
		 * returns true if it does and false if it doesn't*/
	    if(this.allowedZones().contains(zone))
	    	return true;
	    else
		return false;
	}

	/**
	 * Get the set of call zones that can be called on this phone card.
	 * ForSmartAmericas10 phone cards, only calls to Canada, the USA, and Latin Americaare allowed.
	 * @return the set of allowed call zones on the card.
	 */
	@Override
	public Set<CallZone> allowedZones() {
		/*Creates a new set*/
	   Set<CallZone> callZones= new HashSet<CallZone>();
	   /*Adds all allowed zones to the set*/
       callZones.add(CallZone.USA);
       callZones.add(CallZone.CANADA);
       callZones.add(CallZone.LATINAM);
       /*Returns set with allowed zones*/
		return callZones;
	}

	/**
	 * Get the cost per minute of a call to the argument zone on this phone card.
	 * @param zone the call zone to find the cost for.
	 * @pre. zone is not null and a call to zone is allowed for this card.
	 * @return the cost per minute to call the given call zone.
	 */
	@Override
	public double costPerMin(CallZone zone) {
		/*check if zone is allowed*/
		assert this.isAllowed(zone);
		/*if zone is allowed then it returns the cost per min, of that zone*/
		if(zone == CallZone.CANADA)
			return COST_PER_MIN_TO_CANADA;
		if(zone == CallZone.USA)
			return COST_PER_MIN_TO_USA;
		else
			return COST_PER_MIN_TO_LATINAM;	
	}
	
	/**
	 * Deduct the appropriate weekly fees from the card's balance.
	 * If the balance is insufficient, the balance becomes 0.
	 */

	@Override
	public void deductWeeklyFee() {
		/*Sets the balance to the bigger of, 0, or the balance minus the weekly fees*/
		this.setBalance(Math.max(0, this.getBalance() - SmartAmericas10Card.WEEKLY_FEES));
		
	}
	
	/**
	 * Get the history of calls as a List.
	 * The client can modify the returned List without changing the state of the card.
	 * @return a List containing the call history of the card.
	 */
   public List<Call> getCallHistory(){
	 /*Creates a new list, then copies each item to the new list and returns the new list with the copied items*/
	List<Call> deep =  new ArrayList<Call>();
	for (int i= 0; i < callHistory.size(); i++) {
		deep.add(new Call(callHistory.get(i)));
	}
	return deep;   
   }
   
   /**
    * Charge the given call to this phone card.This method tries to charge the given call to the card.
    * If the balance is sufficient to cover it, the call is charged and added to the call history,
    * and the value true is returned.If the balance is insufficient,
    * the balance and call history are left unchanged and false is returned.
    * The client can later mutate the call without changing the state of the card and its call history.
    * @param call the call to charge.
    * @return true if the balance was sufficient to pay for the call, and false otherwise.
    * @pre.call is not null and its zone is allowed for this card.
    */
   @Override
   public boolean charge(Call call) {
	   /*check if zone is allowed*/ 
	   assert this.isAllowed(call.getZone());
	   /*Copies all of the instance variables to a new call*/
	   Call callcopy= new Call(call.getNumber(), call.getZone(), call.getMinutes());
	   /*returns false if the cost of the call is grater than the balance of the card*/
	    if(call.getMinutes() * this.costPerMin(call.getZone()) > this.getBalance())
	    {
		   return false;
	    }
	    /*If the balance is sufficient then the cost of the call is subtracted from the balance 
	     * and the call is added to the call history and returns true*/
	    else
	    {
		   this.setBalance(this.getBalance() - call.getMinutes() * this.costPerMin(call.getZone())); 
		   callHistory.add(callcopy);
	       return true;
	    }
	    
	/**
	 * Compares the card with another object for equality.
	 * Two cards are equal if and only if their PhoneCard sub-objects
	 * are equal and their call histories are equal.
	 * @param obj the object to compare with for equality.
	 * @returns true if the card and object are equal; false otherwise.
	 */
   }
   @Override
	public boolean equals(Object obj) {
	   /*Set a boolean variable to true if the sub-objects are equal*/
	   boolean equ = super.equals(obj);
	   /* if the boolean is true check if the objects call histories are equal
	    * return true if they are and false if they are not*/
	   if(equ) {
		   SmartAmericas10Card object = (SmartAmericas10Card) obj;
		   if(!this.getCallHistory().equals(object.getCallHistory()))
		   {
			   equ = false;
		   }
	   }
	   return equ;
   }
}
