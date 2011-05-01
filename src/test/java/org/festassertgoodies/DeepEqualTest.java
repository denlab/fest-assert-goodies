/*
 * Copyright 2011 fest-assert-goodies Committers 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.festassertgoodies;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.ComparisonFailure;
import org.junit.Test;

public class DeepEqualTest {

	@SuppressWarnings("unused")
	private static class InnerClass {
		private final String innerField;

		public InnerClass(String innerField) {
			this.innerField = innerField;
		}
	}

	@SuppressWarnings("unused")
	private static class OuterClass {
		private final InnerClass innerClass;
		private final String outerField;

		public OuterClass(InnerClass innerClass, String outerField) {
			this.innerClass = innerClass;
			this.outerField = outerField;
		}
	}

	@Test
	public void testIsDeepEqual_equal() throws Exception {
		OuterClass actual = new OuterClass(new InnerClass("inner"), "outer");
		OuterClass expected = new OuterClass(new InnerClass("inner"), "outer");

		assertThat(actual).isNotSameAs(expected);
		assertThat(new DeepEqual(actual)).isDeepEqual(expected);
	}

	@Test(expected = ComparisonFailure.class)
	public void testIsDeepEqual() throws Exception {
		OuterClass actual = new OuterClass(new InnerClass("inner_"), "outer");
		OuterClass expected = new OuterClass(new InnerClass("inner"), "outer");

		assertThat(new DeepEqual(actual)).isDeepEqual(expected);
	}
}
