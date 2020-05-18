package com.adtomic.domain.providers;

import com.adtomic.domain.providers.enums.ProviderNames;
import com.adtomic.model.autoParts.AutoPart;
import com.adtomic.model.autoParts.FrontBumper;
import com.adtomic.model.autoParts.FrontGrill;
import com.adtomic.model.autoParts.Optic;
import com.adtomic.model.autoParts.enums.AutoPartName;
import com.adtomic.model.purchase.PurchaseOption;
import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.time.LocalDate;

import static com.adtomic.model.payment.PaymentMethod.ANY;

@Slf4j
public abstract class Provider {
  protected List<AutoPart> autoParts = List.of(new FrontBumper(), new FrontGrill(), new Optic());

  protected abstract ProviderNames name();

  protected abstract Boolean isSaleDay(LocalDate date);

  protected abstract HashMap<AutoPartName, PurchaseOption> getSales(LocalDate date);

  //TODO: compras en el pasado?
  public HashMap<AutoPartName, PurchaseOption> bestOptions(LocalDate date) {
    if (this.isSaleDay(date)) {
      log.info("Is sale date {} for {}", date.toString(), this.name());
      val bests = this.getSales(date);
      log.debug("{} my bests are {}", this.name(), bests.mkString(","));
      return bests;

    }
    HashMap<AutoPartName, PurchaseOption> bests = autoParts.foldLeft(HashMap.empty(), (collection, ap) ->
        collection.put(ap.name(), PurchaseOption.builder()
            .provider(this.name())
            .paymentOption(ANY)
            .price(ap.price(date))
            .build()
        ));
    log.debug("{} my bests are {}", this.name(), bests.mkString(","));
    return bests;
  }

}
