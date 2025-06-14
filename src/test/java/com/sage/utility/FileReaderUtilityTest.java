package com.sage.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;

public class FileReaderUtilityTest {

    @Test
    public void shouldReturnInstanceOfFileReaderClass() {
        // given
        Class<?> expectedClass = FileReaderUtility.class;

        // when
        Class<?> result = FileReaderUtility.getInstance().getClass();

        // then
        assertEquals(expectedClass, result);
    }
}
