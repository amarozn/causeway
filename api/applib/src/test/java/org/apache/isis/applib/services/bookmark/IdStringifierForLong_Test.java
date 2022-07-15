package org.apache.isis.applib.services.bookmark;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.val;

class IdStringifierForLong_Test {


    public static Stream<Arguments> roundtrip() {
        return Stream.of(
                Arguments.of(Long.MAX_VALUE),
                Arguments.of(Long.MIN_VALUE),
                Arguments.of(0L),
                Arguments.of(12345L),
                Arguments.of(-12345L)
        );
    }

    @ParameterizedTest
    @MethodSource()
    void roundtrip(Long value) {

        val stringifier = new IdStringifierForLong();

        String stringified = stringifier.enstring(value);
        Long parse = stringifier.destring(stringified, null);

        assertThat(parse).isEqualTo(value);
    }

}
