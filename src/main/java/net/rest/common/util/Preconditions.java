package net.rest.common.util;

import static com.google.common.base.Preconditions.checkArgument;
import com.google.common.base.Strings;

public final class Preconditions {

  public static void checkNotNullOrEmpty(String value) {
    checkArgument(!Strings.isNullOrEmpty(value), CommonConstants.ERROR_REQUIRED_VALUE_EMPTY_OR_NULL);
  }
  
}
