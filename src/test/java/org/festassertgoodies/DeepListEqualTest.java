package org.festassertgoodies;

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
import static java.util.Arrays.asList;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.ComparisonFailure;
import org.junit.Test;

/**
 * To test the implementation of the deep list equal object.
 */
public class DeepListEqualTest {

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

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setup() {
		initMocks(this);

	}

	@Test
	public void testIsDeepListEqual() {
		// given
		DeepListEqual actual = new DeepListEqual(asList(new OuterClass(
				new InnerClass("test0"), "outerField"), new OuterClass(
				new InnerClass("innerClass1"), "outerField1")));
		// when
		// then
		assertThat(actual.isDeepListEqual(asList(new OuterClass(new InnerClass(
				"test0"), "outerField"), new OuterClass(new InnerClass(
				"innerClass1"), "outerField1"))));
	}

	@Test(expected = ComparisonFailure.class)
	public void testIsDeepListEqual_koBadSize() {
		// given
		DeepListEqual actual = new DeepListEqual(asList(new OuterClass(
				new InnerClass("test0"), "outerField")));
		// when
		// then
		assertThat(actual.isDeepListEqual(asList(new OuterClass(new InnerClass(
				"test0"), "outerField"), new OuterClass(new InnerClass(
				"innerClass1"), "outerField1"))));
	}

	@Test(expected = ComparisonFailure.class)
	public void testIsDeepListEqual_koListOfCompletelyDifferentNature() {
		// given
		DeepListEqual actual = new DeepListEqual(asList(new OuterClass(
				new InnerClass("test0"), "outerField"), new OuterClass(
				new InnerClass("innerClass1"), "outerField150")));
		// when
		// then
		assertThat(actual.isDeepListEqual(asList("test", "test2")));
	}

	@Test(expected = ComparisonFailure.class)
	public void testIsDeepListEqual_koBadElement() {
		// given
		DeepListEqual actual = new DeepListEqual(asList(new OuterClass(
				new InnerClass("test0"), "outerField"), new OuterClass(
				new InnerClass("innerClass1"), "outerField150")));
		// when
		// then
		assertThat(actual.isDeepListEqual(asList(new OuterClass(new InnerClass(
				"test0"), "outerField"), new OuterClass(new InnerClass(
				"innerClass1"), "outerField1"))));
	}

}
