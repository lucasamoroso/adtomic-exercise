package com.adtomic.routes;

import com.adtomic.model.purchase.Purchase;
import com.adtomic.services.PurchaseService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Function;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PurchaseRoute {
  @NonNull
  final PurchaseService purchaseService;

  @GetMapping("/purchases")
  ResponseEntity<List<Purchase>> all() {
    log.info("Request received to list purchases");
    return new ResponseEntity<>(purchaseService.allPurchases(), OK);
  }

  @PostMapping("/purchases")
  ResponseEntity<Purchase> save(@RequestBody Purchase purchase) {
    log.info("Request received to save purchase {}", purchase.toString());

    val result = purchaseService
        .save(purchase)
        .getOrElseThrow((Function<Throwable, RuntimeException>) RuntimeException::new);

    return new ResponseEntity<>(result, OK);

  }
}
