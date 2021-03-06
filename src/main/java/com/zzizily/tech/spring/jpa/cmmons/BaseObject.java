package com.zzizily.tech.spring.jpa.cmmons;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class BaseObject implements Serializable {
  private static final long serialVersionUID = -2143999891962437697L;

  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
  }

  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o);
  }

  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }
}
