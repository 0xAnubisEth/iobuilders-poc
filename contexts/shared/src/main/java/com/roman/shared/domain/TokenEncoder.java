package com.roman.shared.domain;

import java.util.HashMap;

public interface TokenEncoder {
    String encode(String id);
    HashMap<String, Object> decode(String token);
}
