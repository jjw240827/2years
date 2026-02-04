package util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public enum MapperUtil {
    INSTANCE;

    private ModelMapper modelMapper;

    MapperUtil() {
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true) // 필드명이 일치하는 것끼리 자동으로 매칭

                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                // 클래스의 필드가 private로 선언되어 있어도 접근하여 복사할 수 있게 허용
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        		// 매칭 전략을 느슨하게 하여서 필드 이름이 완벽하게 매칭되지 않더라도 연결

    }

    public ModelMapper get() {
        return modelMapper;
    }
}