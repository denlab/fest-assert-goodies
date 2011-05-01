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

public class XmlEqualTest {

	@Test
	public void testIsXmlEqual() {
		assertThat(new XmlEqual("  <a></a>  ")).isXmlEqual("<a></a>");
	}

	@Test(expected = ComparisonFailure.class)
	public void testIsXmlEqual_notEqual() {
		assertThat(new XmlEqual("<b></b>")).isXmlEqual("<a></a>");
	}

	@Test(expected = CannotCompareInvalidXmlException.class)
	public void testIsXmlEqual_invalidXml() {
		assertThat(new XmlEqual("<a>")).isXmlEqual("<a>");
	}

	@Test
	public void testPrettyXml() {
		String rawXml = "<a><b/></a>";
		String actual = XmlEqual.prettyXml(rawXml);
		String expected = "" + //
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + //
				"<a>\n" + //
				"    <b/>\n" + //
				"</a>\n";

		assertThat(actual).isEqualTo(expected);
	}

	@Test(expected = CannotCompareInvalidXmlException.class)
	public void testPrettyXml_invalid() {
		XmlEqual.prettyXml("<invalid>");
	}
}
