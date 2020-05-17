package com.adtomic.routes;

import com.adtomic.model.purchase.PurchaseOption;
import com.adtomic.services.PurchaseService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PurchaseOptionRoute {
  @NonNull
  final PurchaseService purchaseService;

  @GetMapping("/vehicles/{vehicle}/parts")
  CollectionModel<EntityModel<PurchaseOption>> best(@PathVariable String vehicle,
                                                    @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
    log.info("Requesting best parts for vehicle {} on date {} ", vehicle, date);
    return CollectionModel.of(purchaseService.lookForBest(vehicle, date).map(EntityModel::of),
        linkTo(methodOn(PurchaseOptionRoute.class).best(vehicle, date)).withSelfRel());
  }
}
