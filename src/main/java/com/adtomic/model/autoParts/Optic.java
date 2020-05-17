package com.adtomic.model.autoParts;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static com.adtomic.model.autoParts.AutoPartType.OPTIC;

@NoArgsConstructor
@Getter
public class Optic extends AutoPart {
  protected final Double marketPrice = 6100d;

  public AutoPartType type() {
    return OPTIC;
  }

  public Double price(LocalDate date) {
    return super.price(date);
  }
}
