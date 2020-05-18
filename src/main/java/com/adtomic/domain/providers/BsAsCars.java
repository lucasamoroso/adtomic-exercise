package com.adtomic.domain.providers;

import com.adtomic.domain.providers.enums.ProviderNames;
import com.adtomic.model.autoParts.enums.AutoPartName;
import com.adtomic.model.purchase.PurchaseOption;
import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Objects;

import static com.adtomic.domain.providers.enums.ProviderNames.BS_AS_CARS;
import static com.adtomic.model.autoParts.enums.AutoPartType.OPTIC;
import static com.adtomic.model.payment.PaymentMethod.CREDIT_CARD;

/**
 * A partir del 1er dia de cada mes tiene un 11% de descuento en opticas cada 5 dias (Ejemplo : dia 1, 6, 11 ...) pagando con tarjeta de credito
 */
@Slf4j
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
    val daysOfMonth = YearMonth.of(date.getYear(), date.getMonth()).lengthOfMonth();

    return List
        .rangeClosedBy(1, daysOfMonth, 5)
        .foldLeft(false, (seed, day) -> seed || date.getDayOfMonth() == day);

  }
}
