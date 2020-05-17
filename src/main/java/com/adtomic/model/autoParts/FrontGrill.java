package com.adtomic.model.autoParts;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static com.adtomic.model.autoParts.AutoPartType.BODY_WORK;

/**
 * el 3er sabado delprimer semeste de 2019 = 4420
 * el 3er sabado del 2 semeste de 2019 = 5525
 * el 3er sabado del 2 semeste de 2020 = 8632.8125
 */
@NoArgsConstructor
@Getter
public class FrontGrill extends AutoPart {
  protected final Double marketPrice = 5200d;

  public AutoPartType type() {
    return BODY_WORK;
  }

  public Double price(LocalDate date) {
    return super.price(date);
  }
}

