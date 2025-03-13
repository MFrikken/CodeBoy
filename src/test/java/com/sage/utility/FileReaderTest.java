package com.sage.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;

public class FileReaderTest {

    @Test
    public void shouldReturnInstanceOfFileReaderClass() {
        // given
        Class<?> expectedClass = FileReader.class;

        // when
        Class<?> result = FileReader.getInstance().getClass();

        // then
        assertEquals(expectedClass, result);
    }
}
