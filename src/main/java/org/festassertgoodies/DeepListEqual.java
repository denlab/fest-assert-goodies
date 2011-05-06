package org.festassertgoodies;

import static org.fest.assertions.Assertions.assertThat;

import java.util.List;

import org.fest.assertions.AssertExtension;
import org.festassertgoodies.DeepEqual;

/**
 * To override the DeepEqual object.
 */
public class DeepListEqual implements AssertExtension {

	/** The actual. */
	private final List<?> actual;

	/**
	 * Instantiates a new deep list equal.
	 * 
	 * @param pActual
	 *            the actual
	 */
	public DeepListEqual(List<?> pActual) {
		super();
		actual = pActual;
	}

	/**
	 * Checks if is deep list equal.
	 * 
	 * @param expected
	 *            the expected
	 * @return the deep list equal
	 */
	public DeepListEqual isDeepListEqual(List<?> expected) {
		int nSize = actual.size();
		assertThat(nSize).isEqualTo(expected.size());

		for (int i = 0; i < nSize; i++) {
			assertThat(new DeepEqual(actual.get(i))
					.isDeepEqual(expected.get(i)));
		}
		return this;
	}
}