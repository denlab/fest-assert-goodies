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

import org.fest.assertions.AssertExtension;

import com.thoughtworks.xstream.XStream;

/**
 * AssertExtension to deeply compare two objects. Compared classes don't need to
 * override equals.
 * 
 * @see DeepEqualTest for an example.
 */
public class DeepEqual implements AssertExtension {
	private final Object actual;

	public DeepEqual(Object actual) {
		super();
		this.actual = actual;
	}

	public DeepEqual isDeepEqual(Object expected) {
		XStream xStream = new XStream();
		String actualXml = xStream.toXML(actual);
		String expectedXml = xStream.toXML(expected);

		assertThat(actualXml).isEqualTo(expectedXml);
		return this;
	}
}
