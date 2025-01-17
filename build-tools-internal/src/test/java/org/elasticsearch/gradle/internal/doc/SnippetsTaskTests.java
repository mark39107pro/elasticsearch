/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License
 * 2.0 and the Server Side Public License, v 1; you may not use this file except
 * in compliance with, at your election, the Elastic License 2.0 or the Server
 * Side Public License, v 1.
 */
package org.elasticsearch.gradle.internal.doc;

import org.elasticsearch.gradle.internal.doc.SnippetsTask.Source;
import org.elasticsearch.gradle.internal.test.GradleUnitTestCase;

public class SnippetsTaskTests extends GradleUnitTestCase {

    public void testMatchSource() {
        SnippetsTask.Source source = (Source) SnippetsTask.matchSource("[source,console]");
        assertTrue(source.getMatches());
        assertEquals("console", source.getLanguage());
        assertNull(source.getName());

        source = SnippetsTask.matchSource("[source,console,id=snippet-name-1]");
        assertTrue(source.getMatches());
        assertEquals("console", source.getLanguage());
        assertEquals("snippet-name-1", source.getName());

        source = SnippetsTask.matchSource("[source, console, id=snippet-name-1]");
        assertTrue(source.getMatches());
        assertEquals("console", source.getLanguage());
        assertEquals("snippet-name-1", source.getName());

        source = SnippetsTask.matchSource("[source,console,attr=5,id=snippet-name-1,attr2=6]");
        assertTrue(source.getMatches());
        assertEquals("console", source.getLanguage());
        assertEquals("snippet-name-1", source.getName());

        source = SnippetsTask.matchSource("[source,console, attr=5, id=snippet-name-1, attr2=6]");
        assertTrue(source.getMatches());
        assertEquals("console", source.getLanguage());
        assertEquals("snippet-name-1", source.getName());

        source = SnippetsTask.matchSource("[\"source\",\"console\",id=\"snippet-name-1\"]");
        assertTrue(source.getMatches());
        assertEquals("console", source.getLanguage());
        assertEquals("snippet-name-1", source.getName());

        source = SnippetsTask.matchSource("[source,console,id=\"snippet-name-1\"]");
        assertTrue(source.getMatches());
        assertEquals("console", source.getLanguage());
        assertEquals("snippet-name-1", source.getName());
    }
}
