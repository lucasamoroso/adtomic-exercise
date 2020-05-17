package com.adtomic.domain.providers;

import com.adtomic.model.autoParts.AutoPart;
import com.adtomic.model.autoParts.FrontGrill;
import com.adtomic.model.purchase.PurchaseOption;
import io.vavr.collection.List;

import java.time.LocalDate;

public abstract class Provider {
  protected List<AutoPart> autoParts = List.of(new FrontGrill());

  public abstract List<PurchaseOption> bestOptionFor(LocalDate date);
}
