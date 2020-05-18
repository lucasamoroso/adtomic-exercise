package com.adtomic.services;

import com.adtomic.domain.providers.AutosAR;
import com.adtomic.domain.providers.BsAsCars;
import com.adtomic.domain.providers.GoodRepair;
import com.adtomic.domain.providers.Provider;
import com.adtomic.model.autoParts.AutoPartName;
import com.adtomic.model.purchase.Purchase;
import com.adtomic.model.purchase.PurchaseOption;
import com.adtomic.repositories.PurchaseRepository;
import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Comparator;

@Service
@RequiredArgsConstructor
@Slf4j
public class PurchaseService {
  @NonNull
  final PurchaseRepository repository;
  @NonNull
  final List<Provider> providers = List.of(new AutosAR(), new BsAsCars(), new GoodRepair());

  //TODO: check stock for vehicle?
  public HashMap<AutoPartName, PurchaseOption> lookForBest(String vehicle, LocalDate date) {
    return providers
        .flatMap(p -> p.bestOptions(date))
        .foldLeft(
            HashMap.empty(), (options, other) ->
                options.get(other._1).map(co -> options.put(other._1, co.lowerPriceOption(other._2)))
                    .getOrElse(() -> options.put(other._1, other._2))
        );

  }


  public Option<HashMap<Integer, java.util.HashMap<AutoPartName, PurchaseOption>>> lookForBest(String vehicle, Integer year, Integer month) {
    val daysOfMonth = YearMonth.of(year, month).lengthOfMonth();
    log.info("Days of month {}", daysOfMonth);
    return List
        .rangeClosed(1, daysOfMonth)
        .map(day -> HashMap.of(day, lookForBest(vehicle, LocalDate.of(year, month, day))))
        .minBy(Comparator.comparingDouble(bestOptionForDay ->
            bestOptionForDay.map(a -> a._2.foldLeft(0d, (seed, cur) -> seed + cur._2.getPrice())).sum().doubleValue()
        ))
        .map(bestDayOfMonthOption ->
            bestDayOfMonthOption.foldLeft(HashMap.empty(),
                (seed, bo) -> seed.put(bo._1, bo._2.toJavaMap()))
        );
  }


  public List<Purchase> allPurchases() {
    return List.ofAll(repository.findAll());
  }

  public Try<Purchase> save(Purchase purchase) {
    return Try.of(() -> repository.save(purchase))
        .onFailure(reason -> log.error("Unexpected error saving purchase", reason));

  }
}
