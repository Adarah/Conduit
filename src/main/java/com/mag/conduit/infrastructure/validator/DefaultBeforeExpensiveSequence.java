package com.mag.conduit.infrastructure.validator;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({Default.class, Expensive.class})
public interface DefaultBeforeExpensiveSequence {
}
