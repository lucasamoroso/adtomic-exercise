package com.adtomic.model.autoParts;

import com.adtomic.model.autoParts.enums.AutoPartName;
import com.adtomic.model.autoParts.enums.AutoPartType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.adtomic.model.autoParts.enums.AutoPartName.FRONT_BUMPER;
import static com.adtomic.model.autoParts.enums.AutoPartType.BODY_WORK;

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
