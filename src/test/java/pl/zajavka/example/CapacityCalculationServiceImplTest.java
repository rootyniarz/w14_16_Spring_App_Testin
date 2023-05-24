package pl.zajavka.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestBeanConfiguration.class})
//@ContextConfiguration(classes = {BeanConfiguration.class})
class CapacityCalculationServiceImplTest {

    @Autowired
    private CapacityCalculationService capacityCalculationService;
    @Autowired
    private WidthCalculationService widthCalculationService;
    @Autowired
    private HeightCalculationService heightCalculationService;
    @Autowired
    private DepthCalculationService depthCalculationService;

    @BeforeEach
    public void setUp(){
        Assertions.assertNotNull(capacityCalculationService);
        Assertions.assertNotNull(widthCalculationService);
        Assertions.assertNotNull(heightCalculationService);
        Assertions.assertNotNull(depthCalculationService);
    }


    @Test
    void someCalculation() {
        //given
        final InputData inputData = someInputData();
        Mockito.when(widthCalculationService.calculate(any(InputData.class))).thenReturn(BigDecimal.TEN);
        Mockito.when(depthCalculationService.calculate(any(InputData.class))).thenReturn(new BigDecimal("20"));

        //when
        final var result = capacityCalculationService.someCalculation(inputData);

        //then
        Assertions.assertEquals(new BigDecimal("600"),result);

    }

    private InputData someInputData(){
        return InputData.builder()
                .depth("1")
                .width("2")
                .height("3")
                .build();
    }
}