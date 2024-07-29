package com.devansh.splitKro.factory;

import com.devansh.splitKro.enums.SplitTypeEnum;
import com.devansh.splitKro.service.split.SplitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class SplitTypeFactory {
    private final Map<SplitTypeEnum, SplitService> splitServiceMap;

    @Autowired
    private SplitTypeFactory(List<SplitService> splitServiceList){
        this.splitServiceMap = splitServiceList.stream().collect(Collectors.toUnmodifiableMap(SplitService::getType, Function.identity()));
    }

    public SplitService getSplitService(SplitTypeEnum splitTypeEnum){
        return Optional.ofNullable(splitServiceMap.get(splitTypeEnum)).orElseThrow(IllegalArgumentException::new);
    }
}
