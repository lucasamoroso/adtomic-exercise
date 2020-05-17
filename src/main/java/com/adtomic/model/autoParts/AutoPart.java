package com.adtomic.model.autoParts;

import io.vavr.collection.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@NoArgsConstructor
@Getter
public abstract class AutoPart {
  private static final Double correctionFactor = 1.25;
  protected Double marketPrice;

  public abstract AutoPartType type();

  public Double price(LocalDate date) {
    //1d es lo que subio por inflacion
    long timesToApplyCorrection = (calcMonthsBetween(date) / 6);
    return List.range(0, timesToApplyCorrection)
        .foldLeft(getMarketPrice(), (current, index) -> (current * correctionFactor));
  }

  private long calcMonthsBetween(LocalDate date) {
    return ChronoUnit.MONTHS.between(
        LocalDate.parse("2019-01-01").withDayOfMonth(1),
        date.withDayOfMonth(1));
  }


}
