package com.adtomic.model.purchase;


import com.adtomic.domain.providers.ProviderNames;
import com.adtomic.model.autoParts.AutoPartType;
import com.adtomic.model.payment.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(indexes = {@Index(name = "DATE_INDEX", columnList = "date")})
public class Purchase {
  @Column(nullable = false)
  private @Id
  @GeneratedValue
  Long id;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  @NonNull
  private ProviderNames provider;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  @NonNull
  private AutoPartType autoPart;

  @Column(nullable = false)
  @JsonFormat(pattern = "dd-MM-yyyy")
  @NonNull
  private LocalDate date;

  @Column(nullable = false)
  @NonNull
  private Double price;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  @NonNull
  private PaymentMethod paymentMethod;
}
