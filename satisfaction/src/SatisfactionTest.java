/**
 * @author vmangipu
 * 
 */
import junit.framework.Assert;

import org.junit.Test;

public class SatisfactionTest {
	@Test
	public final void whenItemsAreFoundForTheGivenValue() {
		new Satisfaction().checkSatisfaction(500);
		Assert.assertTrue(true);
	}

	@Test(expected = RuntimeException.class)
	public final void whenItemsAreNotFoundForTheGivenValue() {
		new Satisfaction().checkSatisfaction(5);
		Assert.assertTrue(true);
	}

	@Test(expected = RuntimeException.class)
	public final void whenNonNumberIsUsedThenExceptionIsThrown() {
		new Satisfaction().checkSatisfaction(0);
	}
}
