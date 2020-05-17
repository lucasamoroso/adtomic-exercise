package com.adtomic.services;

import com.adtomic.domain.providers.AutosAR;
import com.adtomic.domain.providers.Provider;
import com.adtomic.model.purchase.Purchase;
import com.adtomic.model.purchase.PurchaseOption;
import com.adtomic.repositories.PurchaseRepository;
import io.vavr.collection.List;
import io.vavr.control.Try;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class PurchaseService {
  @NonNull
  final PurchaseRepository repository;
  @NonNull
  final List<Provider> providers = List.of(new AutosAR());

  public List<PurchaseOption> lookForBest(String vehicle, LocalDate date) {
    return providers
        .flatMap(p -> p.bestOptionFor(date));
  }

  public List<Purchase> allPurchases() {
    return List.ofAll(repository.findAll());
  }

  public Try<Purchase> save(Purchase purchase) {
    return Try.of(() -> repository.save(purchase))
        .onFailure(reason -> log.error("Unexpected error saving purchase", reason));

  }
}
