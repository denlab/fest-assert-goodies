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

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import nu.xom.Builder;
import nu.xom.ParsingException;
import nu.xom.Serializer;
import nu.xom.ValidityException;

import org.fest.assertions.AssertExtension;

/**
 * AssertExtension to compare two XML strings. Formatting is ignored.
 * 
 * @see XmlEqualTest for an example.
 */
public class XmlEqual implements AssertExtension {
	private final String actualXml;
	static final String UTF_8 = "UTF-8";

	public XmlEqual(String actualXml) {
		this.actualXml = actualXml;
	}

	public XmlEqual isXmlEqual(String expectedXml) {
		assertThat(prettyXml(actualXml)).isEqualTo(prettyXml(expectedXml));
		return this;
	}

	static final String prettyXml(String xml) {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			Serializer serializer = new Serializer(out);
			serializer.setIndent(4);
			serializer.setLineSeparator("\n");
			serializer.write(new Builder().build(xml, ""));
			return out.toString(XmlEqual.UTF_8);
		} catch (ValidityException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (ParsingException e) {
			throw new CannotCompareInvalidXmlException(xml, e);
		}
	}
}
