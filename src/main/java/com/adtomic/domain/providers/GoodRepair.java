package com.adtomic.domain.providers;

import com.adtomic.model.autoParts.AutoPartName;
import com.adtomic.model.purchase.PurchaseOption;
import io.vavr.collection.HashMap;
import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import lombok.val;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Objects;

import static com.adtomic.domain.providers.ProviderNames.GOOD_REPAIR;
import static com.adtomic.model.autoParts.AutoPartType.BODY_WORK;
import static com.adtomic.model.autoParts.AutoPartType.OPTIC;
import static com.adtomic.model.payment.PaymentMethod.CASH;
import static com.adtomic.model.payment.PaymentMethod.CREDIT_CARD;
import static java.time.DayOfWeek.*;

/**
 * De lunes miercoles de cada mes tiene un 20% de descuento en opticas abonando con tarjeta de credito.
 * Ademas, los dias jueves y viernes de cada mes, ofrece un 6% de descuento en carrocerias pagando en efectivo.
 */
public class GoodRepair extends Provider {
  private final Set<DayOfWeek> monToWen = HashSet.of(MONDAY, TUESDAY, WEDNESDAY);

  protected HashMap<AutoPartName, PurchaseOption> getSales(LocalDate date) {
    if (monToWen.contains(date.getDayOfWeek())) {
      return super.autoParts.filter(a -> Objects.equals(OPTIC, a.type()))
          .foldLeft(HashMap.empty(), (collection, ap) ->
              collection.put(ap.name(), PurchaseOption.builder()
                  .provider(name())
                  .paymentOption(CREDIT_CARD)
                  .price(ap.price(date) * 0.8d)
                  .build()
              ));
    }
    return super.autoParts.filter(a -> Objects.equals(BODY_WORK, a.type()))
        .foldLeft(HashMap.empty(), (collection, ap) ->
            collection.put(ap.name(), PurchaseOption.builder()
                .provider(GOOD_REPAIR)
                .paymentOption(CASH)
                .price(ap.price(date) * 0.94d)
                .build()
            ));
  }

  @Override
  protected ProviderNames name() {
    return GOOD_REPAIR;
  }

  protected Boolean isSaleDay(LocalDate date) {
    val dayOfWeek = date.getDayOfWeek();
    return SATURDAY.equals(dayOfWeek) || SUNDAY.equals(dayOfWeek);
  }
}
