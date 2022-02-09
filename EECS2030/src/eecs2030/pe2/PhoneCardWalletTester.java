package eecs2030.pe2;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class PhoneCardWalletTester {

	@Test
	public void testConstructor1() {
		PhoneCardWallet w = new PhoneCardWallet("XYZ");
		assertTrue("1 argument constructor sets name wrong", w.getName().equals("XYZ"));
	}
	
	@Test
	public void testConstructor2() {
		Set<PhoneCard> cset = new HashSet<PhoneCard>();
		cset.add(new Global25Card(456456,45678));
		PhoneCardWallet w = new PhoneCardWallet("XYZ",cset);
		assertTrue("2 argument constructor assigns card set wrong", w.getCardSet().equals(cset));
	}
	
	@Test
	public void testAdd() {
		Set<PhoneCard> cset = new HashSet<PhoneCard>();
		cset.add(new Global25Card(456456,45678));
		PhoneCardWallet w = new PhoneCardWallet("XYZ",cset);
		PhoneCard card = new Global25Card(567567,56789);
		cset.add(card);
		w.addCard(card);
		assertTrue("adding card fails", w.getCardSet().equals(cset));
		System.out.println(w.toString());
	}
	
	@Test
	public void testRemove() {
		Set<PhoneCard> cset = new HashSet<PhoneCard>();
		cset.add(new Global25Card(456456,45678));
		PhoneCardWallet w = new PhoneCardWallet("XYZ",cset);
		PhoneCard card = new Global25Card(567567,56789);
		w.addCard(card);
		w.removeCard(card);
		assertTrue("adding card fails", w.getCardSet().equals(cset));
	}
	
	@Test
	public void testFilter1() {
		Set<PhoneCard> cset = new HashSet<PhoneCard>();
		cset.add(new Global25Card(456456,45678));
		cset.add(new Global25Card(567567,56789));
		PhoneCardWallet w = new PhoneCardWallet("XYZ",cset);
		assertTrue("filter fails", w.filter(CallZone.CANADA, 15.0).equals(cset));
	}
	
	@Test
	public void testFilter2() {
		Set<PhoneCard> cset = new HashSet<PhoneCard>();
		cset.add(new Global25Card(456456,45678));
		cset.add(new Global25Card(567567,56789));
		PhoneCardWallet w = new PhoneCardWallet("XYZ",cset);
		assertFalse("filter fails", w.filter(CallZone.CANADA, 30.0).equals(cset));
	}
	
	@Test
	public void testHashCode() {
		PhoneCardWallet w1 = new PhoneCardWallet("XYZ");
		PhoneCardWallet w2 = new PhoneCardWallet("XYZ");
		PhoneCard card = new Global25Card(567567,56789);
		w1.addCard(card);
		w2.addCard(card);
		assertTrue("hashCode fails", w1.hashCode() == w2.hashCode());
	}
	@Test
	public void testEquals() {
		PhoneCardWallet w1 = new PhoneCardWallet("XYZ");
		PhoneCardWallet w2 = new PhoneCardWallet("XYZ");
		PhoneCard card = new Global25Card(567567,56789);
		w1.addCard(card);
		w2.addCard(card);
		assertTrue("equals fails", w1.equals(w2));
	}
}
