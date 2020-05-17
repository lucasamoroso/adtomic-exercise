package com.adtomic.domain.providers;

import com.adtomic.model.purchase.PurchaseOption;
import io.vavr.collection.List;

import java.time.LocalDate;

/**
 * De lunes miercoles de cada mes tiene un 20% de descuento en opticas abonando con tarjeta de credito.
 * Ademas, los dias jueves y viernes de cada mes, ofrece un 6% de descuento en carrocerias pagando en efectivo.
 */
public class GoodRepair extends Provider {
  public List<PurchaseOption> bestOptionFor(LocalDate date) {
    return null;
  }
}
