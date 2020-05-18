package com.adtomic.model.autoParts;

import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.adtomic.model.autoParts.AutoPartName.FRONT_BUMPER;
import static com.adtomic.model.autoParts.AutoPartType.BODY_WORK;

@NoArgsConstructor
@Getter
public class FrontBumper extends AutoPart {
  protected final Double marketPrice = 7600d;

  public AutoPartType type() {
    return BODY_WORK;
  }

  @Override
  public AutoPartName name() {
    return FRONT_BUMPER;
  }
}
