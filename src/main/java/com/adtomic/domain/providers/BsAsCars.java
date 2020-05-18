package com.adtomic.domain.providers;

import com.adtomic.model.autoParts.AutoPartName;
import com.adtomic.model.purchase.PurchaseOption;
import io.vavr.collection.HashMap;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Objects;

import static com.adtomic.domain.providers.ProviderNames.BS_AS_CARS;
import static com.adtomic.model.autoParts.AutoPartType.OPTIC;
import static com.adtomic.model.payment.PaymentMethod.CREDIT_CARD;

/**
 * A partir del 1er dia de cada mes tiene un 11% de descuento en opticas cada 5 dias (Ejemplo : dia 1, 6, 11 ...) pagando con tarjeta de credito
 */
public class BsAsCars extends Provider {
  protected HashMap<AutoPartName, PurchaseOption> getSales(LocalDate date) {
    return super.autoParts.filter(a -> Objects.equals(OPTIC, a.type()))
        .foldLeft(HashMap.empty(), (collection, ap) ->
            collection.put(ap.name(), PurchaseOption.builder()
                .provider(name())
                .paymentOption(CREDIT_CARD)
                .price(ap.price(date) * 0.89d)
                .build()
            ));
  }

  @Override
  protected ProviderNames name() {
    return BS_AS_CARS;
  }

  protected Boolean isSaleDay(LocalDate date) {
    for (int i = 1; i <= date.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth(); i = i + 5) {
      if (date.getDayOfMonth() == i) {
        return true;
      }
    }
    return false;

  }
}
