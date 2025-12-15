package com.beanPostProcessorCustom;

import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class MainService {
    @AutowiredRandomInt(min = 1, max = 2)
    private int randomInt;
}
