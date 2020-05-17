package com.adtomic.domain.providers;

import com.adtomic.model.purchase.PurchaseOption;
import io.vavr.collection.List;
import lombok.val;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Objects;

import static com.adtomic.domain.providers.ProvidersEnum.AUTOS_AR;
import static com.adtomic.model.autoParts.AutoPartType.BODY_WORK;
import static com.adtomic.model.payment.PaymentMethod.CASH;

/**
 * El tercer sabado de cada mes ofrece un 15% de descuento en carroceria de automoviles pagando en efectivo
 */
public class AutosAR extends Provider {

  public List<PurchaseOption> bestOptionFor(LocalDate date) {
    if (isThirdSaturday(date)) {
      return autoParts.filter(a -> Objects.equals(BODY_WORK, a.type()))
          .map(ap ->
              PurchaseOption.builder()
                  .provider(AUTOS_AR)
                  .paymentOption(CASH)
                  .price(ap.price(date) * 0.85d)
                  .build()
          );
    }
    return List.empty();
  }

  private Boolean isThirdSaturday(LocalDate date) {
    val ordinal = 3;
    val weekday = DayOfWeek.SATURDAY;
    val adjusted = date.with(TemporalAdjusters.dayOfWeekInMonth(ordinal, weekday));
    return date.equals(adjusted);
  }
}
