package com.adtomic.model.autoParts;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static com.adtomic.model.autoParts.AutoPartType.BODY_WORK;

@NoArgsConstructor
@Getter
public class FrontBumper extends AutoPart {
  protected final Double marketPrice = 7600d;

  public AutoPartType type() {
    return BODY_WORK;
  }

  public Double price(LocalDate date) {
    return super.price(date);
  }
}
