package com.adtomic.domain.providers;

import com.adtomic.domain.providers.enums.ProviderNames;
import com.adtomic.model.autoParts.enums.AutoPartName;
import com.adtomic.model.purchase.PurchaseOption;
import io.vavr.collection.HashMap;
import lombok.val;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Objects;

import static com.adtomic.domain.providers.enums.ProviderNames.AUTOS_AR;
import static com.adtomic.model.autoParts.enums.AutoPartType.BODY_WORK;
import static com.adtomic.model.payment.PaymentMethod.CASH;

/**
 * El tercer sabado de cada mes ofrece un 15% de descuento en carroceria de automoviles pagando en efectivo
 */
public class AutosAR extends Provider {

  protected HashMap<AutoPartName, PurchaseOption> getSales(LocalDate date) {
    return super.autoParts.filter(a -> Objects.equals(BODY_WORK, a.type()))
        .foldLeft(HashMap.empty(), (collection, ap) ->
            collection.put(ap.name(), PurchaseOption.builder()
                .provider(name())
                .paymentOption(CASH)
                .price(ap.price(date) * 0.85d)
                .build())
        );

  }

  @Override
  protected ProviderNames name() {
    return AUTOS_AR;
  }

  protected Boolean isSaleDay(LocalDate date) {
    val ordinal = 3;
    val weekday = DayOfWeek.SATURDAY;
    val adjusted = date.with(TemporalAdjusters.dayOfWeekInMonth(ordinal, weekday));
    return date.equals(adjusted);
  }
}
