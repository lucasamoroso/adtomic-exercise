package com.adtomic.domain.providers;

import com.adtomic.model.purchase.PurchaseOption;
import io.vavr.collection.List;

import java.time.LocalDate;

/**
 * A partir del 1er dia de cada mes tiene un 11% de descuento en opticas cada 5 dias (Ejemplo : dia 1, 6, 11 ...) pagando con tarjeta de credito
 */
public class BsAsCars extends Provider {
  public List<PurchaseOption> bestOptionFor(LocalDate date) {
    return null;
  }
}
