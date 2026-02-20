package org.zerock.springex.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 해당 클래스가 빈에 대한 설정을 하는 클래스임을 명시
public class ModelMapperConfig {

    @Bean // getMapper 매서드가 반환하는 modelMapper를 빈으로 생성
    public ModelMapper getMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration() // 매퍼가 객체 간의 데이터를 어떻게 분석, 복사할 것인지 설정하는 매서드
                .setFieldMatchingEnabled(true) // 필드간의 직접 매칭 활성화, 변수 이름이 같으면 데이터를 복사
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                // private인 필드에도 접근할 수 있게 허용
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        		// 이름이 완벽하지 않아도 연결
        return modelMapper;
    }

}