package com.adtomic.model.autoParts;

import com.adtomic.model.autoParts.enums.AutoPartName;
import com.adtomic.model.autoParts.enums.AutoPartType;
import io.vavr.collection.List;
import lombok.Getter;
import lombok.val;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Getter
public abstract class AutoPart {
  private final Double correctionFactor = 1.25;
  private final LocalDate startDate = LocalDate.parse("2019-01-01");
  protected Double marketPrice;

  public abstract AutoPartType type();

  public abstract AutoPartName name();

  public Double price(LocalDate date) {
    val timesToApplyCorrection = (calcMonthsBetween(date) / 6);
    return List.range(0, timesToApplyCorrection)
        .foldLeft(getMarketPrice(), (current, index) -> (current * correctionFactor));
  }

  private long calcMonthsBetween(LocalDate date) {
    return ChronoUnit.MONTHS.between(
        startDate.withDayOfMonth(1),
        date.withDayOfMonth(1));
  }


}
