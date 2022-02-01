package com.vertex.kafkapoc.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class AppUtil {
    public boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
}
