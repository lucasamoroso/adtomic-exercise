package com.adtomic.model.autoParts;

import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.adtomic.model.autoParts.AutoPartType.OPTIC;

/**
 * BsAs cars 01-01-2019 = 5429
 * BsAs cars 02-01-2019 = 5429
 * BsAs cars 31-01-2019 = 5429
 * BsAs cars 31-08-2019 = 6786,25
 * BsAs cars 01-01-2020 = 8482,8125
 */

@NoArgsConstructor
@Getter
public class Optic extends AutoPart {
  protected final Double marketPrice = 6100d;

  public AutoPartType type() {
    return OPTIC;
  }

  @Override
  public AutoPartName name() {
    return AutoPartName.OPTIC;
  }
}
