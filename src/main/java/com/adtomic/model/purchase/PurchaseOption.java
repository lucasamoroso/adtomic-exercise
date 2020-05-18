package com.adtomic.model.purchase;

import com.adtomic.domain.providers.enums.ProviderNames;
import com.adtomic.model.payment.PaymentMethod;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class PurchaseOption {
  @NonNull
  private Double price;
  @NonNull
  private ProviderNames provider;
  @NonNull
  private PaymentMethod paymentOption;

  public PurchaseOption lowerPriceOption(PurchaseOption other) {
    return this.price <= other.price ? this : other;
  }
}
