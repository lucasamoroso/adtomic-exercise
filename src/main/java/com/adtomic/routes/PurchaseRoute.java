package com.adtomic.routes;

import com.adtomic.model.purchase.Purchase;
import com.adtomic.services.PurchaseService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PurchaseRoute {
  @NonNull
  final PurchaseService purchaseService;

  @GetMapping("/purchases")
  CollectionModel<EntityModel<Purchase>> all() {
    return CollectionModel.of(purchaseService.allPurchases().map(EntityModel::of),
        linkTo(methodOn(PurchaseRoute.class).all()).withSelfRel());
  }

  @PostMapping("/purchases")
  EntityModel<Purchase> save(@RequestBody Purchase purchase) {
    return purchaseService
        .save(purchase)
        .map(EntityModel::of)
        .getOrElseThrow((Function<Throwable, RuntimeException>) RuntimeException::new);

  }
}
