package com.adtomic.routes;

import com.adtomic.model.autoParts.enums.AutoPartName;
import com.adtomic.model.purchase.PurchaseOption;
import com.adtomic.services.PurchaseService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;


@RestController
@RequiredArgsConstructor
@Slf4j
public class PurchaseOptionRoute {
  @NonNull
  final PurchaseService purchaseService;

  @GetMapping(value = "/vehicles/{vehicle}/parts", params = "date")
  ResponseEntity<HashMap<AutoPartName, PurchaseOption>> bestSaleOnDate(@PathVariable String vehicle,
                                                                       @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
    log.info("Request received to find best sale parts for vehicle {} on date {} ", vehicle, date);
    return new ResponseEntity<>(purchaseService.lookForBest(vehicle, date).toJavaMap(), HttpStatus.OK);
  }

  @GetMapping(value = "/vehicles/{vehicle}/parts", params = {"month", "year"})
  ResponseEntity<HashMap<Integer, HashMap<AutoPartName, PurchaseOption>>> bestBuyDayOnMonth(@PathVariable String vehicle,
                                                                                            @RequestParam Integer month,
                                                                                            @RequestParam Integer year) {
    log.info("Request received to find best buy day of month for vehicle {} parts on year {}, month {} ", vehicle, year, month);
    val bestDayOfMonth = purchaseService.lookForBest(vehicle, year, month).map(io.vavr.collection.HashMap::toJavaMap).getOrElse(new HashMap<>());
    return new ResponseEntity<>(bestDayOfMonth, HttpStatus.OK);
  }
}
