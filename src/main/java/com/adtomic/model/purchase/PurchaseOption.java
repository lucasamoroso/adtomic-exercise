package com.adtomic.model.purchase;

import com.adtomic.domain.providers.ProvidersEnum;
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
  private ProvidersEnum provider;
  @NonNull
  private PaymentMethod paymentOption;
}
